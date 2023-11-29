package ru.kk.task11week._main;

import ru.kk.task11week.service.*;

import java.util.ArrayList;
import java.util.List;

public class _Main {
    private static final Object winnerLock = new Object();
    private static List<Car> cars = new ArrayList<>();
    private static List<CarRace> carRaceList = new ArrayList<>();
    public static Object lock = new Object();
    public static boolean thread1Turn = true;

    public static void main(String[] args) {
//1
        System.out.println("1. Программа для создания двух потоков, выводящих четные и нечетные числа:");
        EvenNumberPrinter evenThread = new EvenNumberPrinter();
        OddNumberPrinter oddThread = new OddNumberPrinter();
        evenThread.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        oddThread.start();
//2
        NumberPrinter thread1 = new NumberPrinter(1);
        System.out.println("2. Программа для создания двух потоков, выводящих числа от 1 до 10 поочередно:");
        thread1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        NumberPrinter thread2 = new NumberPrinter(2);
        thread2.start();
//3
        int n = 10;
        SumOfSquaresCalculator calculator = new SumOfSquaresCalculator(n);
        calculator.start();
        try {
            Thread.sleep(100);
            calculator.join();
            System.out.println("3. Программа для подсчета суммы квадратов первых N натуральных чисел с использованием многопоточности:");
            System.out.println("Сумма квадратных первых " + n + " натуральных чисел: " + calculator.getResult());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//5
        NamePrinter thread_1 = new NamePrinter(1);
        System.out.println("5. Программа для создания двух потоков, выводящих свои имена в консоль с интервалом в 1 секунду:");
        thread_1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        NamePrinter thread_2 = new NamePrinter(2);
        thread_2.start();
//4
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
                System.out.println("4. Программа имитации гонок");
                System.out.println("Wins the race : " + car);
                for (CarRace race : carRaceList) {
                    race.interrupt();
                }
            }).start();
        }
    }
}