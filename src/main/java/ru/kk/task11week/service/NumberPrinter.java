package ru.kk.task11week.service;

import static ru.kk.task11week._main._Main.lock;
import static ru.kk.task11week._main._Main.thread1Turn;

public class NumberPrinter extends Thread {
    private final int threadNumber;

    public NumberPrinter(int threadNumber) {
        this.threadNumber = threadNumber;
    }

    public void run() {
        for (int i = 1; i <= 10; i++) {
            synchronized (lock) {
                while (threadNumber == 1 && !thread1Turn || threadNumber == 2 && thread1Turn) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Поток " + threadNumber + ": " + i);
                thread1Turn = !thread1Turn;
                lock.notify();
            }
        }
    }
}
