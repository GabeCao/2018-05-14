package com.lv;

public class MergeOldHotSpot {
    //HotSpot的坐标
    private double x;
    private double y;
    //HotSpot 在二维数组中的位置
    private int m;
    private int n;

    private String sensor;

    public MergeOldHotSpot(double x, double y, int m, int n, String sensor) {
        this.x = x;
        this.y = y;
        this.m = m;
        this.n = n;
        this.sensor = sensor;
    }

    @Override
    public String toString() {
        return "MergeOldHotSpot{" +
                "x=" + x +
                ", y=" + y +
                ", m=" + m +
                ", n=" + n +
                ", sensor=" + sensor +
                '}';
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getM() {
        return m;
    }

    public void setM(int m) {
        this.m = m;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public String getSensor() {
        return sensor;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor;
    }
}
