package com.lv;

import java.util.ArrayList;

public class HotSpot {

    //HotSpot的坐标
    private double x;
    private double y;
    //HotSpot 在二维数组中的位置
    private int m;
    private int n;
    //保存周围的Point
    private ArrayList<Point> pointArrayList;
    //hotspot保存周围的Point 的flag = true 的个数
    private int numberOfPoint;

    public int getNumberOfPoint() {
        return numberOfPoint;
    }

    public void setNumberOfPoint(int numberOfPoint) {
        this.numberOfPoint = numberOfPoint;
    }

    //给 HotSpot 编一个号
    private int number;

    public int getNumber() {
        return number;
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

    public void setNumber(int number) {
        this.number = number;
    }

    public HotSpot(double x, double y, int m, int n) {
        this.x = x;
        this.y = y;
        this.m = m;
        this.n = n;
        this.pointArrayList = new ArrayList<>();
        numberOfPoint = 0;
    }

    @Override
    public String toString() {
        return "HotSpot{" +
                "x=" + x +
                ", y=" + y +
                ", m=" + m +
                ", n=" + n +
                '}';
    }

    public ArrayList<Point> getPointArrayList() {
        return pointArrayList;
    }

    public void setPointArrayList(ArrayList<Point> pointArrayList) {
        this.pointArrayList = pointArrayList;
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
}
