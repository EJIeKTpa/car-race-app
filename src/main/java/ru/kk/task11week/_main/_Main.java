package ru.kk.task11week._main;

import ru.kk.task11week.service.Car;
import ru.kk.task11week.service.CarRace;

import java.util.ArrayList;
import java.util.List;

public class _Main {
    private static Object winnerLock = new Object();
    private static List<Car> cars = new ArrayList<>();
    private static List<CarRace> carRaceList = new ArrayList<>();

    public static void main(String[] args) {
        cars.add(new Car("first car"));
        cars.add(new Car("second car"));
        startRace(1000);
    }

    public static void startRace(int distance) {
        for (Car currentCar : cars) { //помещаем объекты машин в список
            carRaceList.add(new CarRace(currentCar, distance));
        }
        synchronized (cars.get(0)) {
            synchronized (cars.get(1)) {
                for (CarRace race : carRaceList) { //помещаем машины в объекты процессов езды, на основе которых будут созданы потоки
                    race.start();
                }
            }
        }
    }

    public static void endRacing(Car car) {
        synchronized (winnerLock) {   //поток прерывающий потоки гонки
            new Thread(() -> {
                System.out.println("winner is: " + car);
                for (CarRace race : carRaceList) {
                    race.interrupt();
                }
            }).start();
        }
    }
}