package ru.kk.car_race_app.service;

public class EvenNumberPrinter extends Thread {
    public void run() {
        for (int i = 2; i <= 10; i += 2) {
            System.out.println("Поток 1: " + i);
        }
    }
}
