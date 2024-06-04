package ru.kk.car_race_app.service;

public class SumOfSquaresCalculator extends Thread {
    private final int n;
    private int result;
    public SumOfSquaresCalculator(int n) {
        this.n = n;
        this.result = 0;
    }
    public void run() {
        for(int i = 1; i <= n; i++) {
            result += i * i;
        }
    }
    public int getResult() {
        return result;
    }
}
