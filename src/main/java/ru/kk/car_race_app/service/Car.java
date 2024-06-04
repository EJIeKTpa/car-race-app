package ru.kk.car_race_app.service;

public class Car {

    private final String carName;
    private final int speed;

    public Car(String carName) {
        this.carName = carName;
        this.speed = (int) (Math.random() * 5) + 1;
    }

    public int getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return carName;
    }
}
