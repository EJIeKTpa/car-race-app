package ru.kk.car_race_app.service;

public class OddNumberPrinter extends Thread {
    public void run() {
        for (int i = 1; i <= 10; i += 2) {
            System.out.println("Поток 2: " + i);
        }
    }
}
