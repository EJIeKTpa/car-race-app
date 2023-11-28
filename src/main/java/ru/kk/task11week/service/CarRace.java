package ru.kk.task11week.service;

import ru.kk.task11week._main._Main;

public class CarRace extends Thread {
    private final Car car;
    private final int distance;

    public CarRace(Car car, int distance) {
        if (car == null) {
            throw new RuntimeException("car is null");
        }
        this.car = car;
        this.distance = distance;
    }

    public void run() {
        synchronized (car) {
            int currentDistance = 0;
            while (true) {
                currentDistance = currentDistance + car.getSpeed() * 5;
                //System.out.println(car.getCarName() + " проехала " + currentDistance);
                if (currentDistance >= distance) {
                    _Main.endRacing(car);
                }
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    break;
                }
            }
        }
    }
}