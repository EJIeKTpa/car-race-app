package ru.kk.task11week.service;

public class Car {

    private String carName;
    private int speed;

    public Car(String carName) {
        this.carName = carName;
        this.speed = (int) (Math.random() * 5) + 1;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public String toString() {
        return carName;
    }
}
