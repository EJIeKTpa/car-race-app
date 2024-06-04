package ru.kk.car_race_app.service;

import static ru.kk.car_race_app._main._Main.lock;
import static ru.kk.car_race_app._main._Main.thread1Turn;

public class NamePrinter extends Thread {
    private final int name;

    public NamePrinter(int name) {
        this.name = name;
    }

    public void run() {
        for (int i = 0; i <= 10; i++) {
            synchronized (lock) {
                while (name == 1 && !thread1Turn || name == 2 && thread1Turn) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Поток № " + name);
                thread1Turn = !thread1Turn;
                lock.notify();
            }
        }
    }
}
