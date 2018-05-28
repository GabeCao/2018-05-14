package com.lv;

import java.util.ArrayList;

public class MergeNewHotSpot {

    //HotSpot的坐标
    private double x;
    private double y;
    //HotSpot 在二维数组中的位置
    private int m;
    private int n;
    //给新的备选点编号
    private int number;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    //包括的 mergeOldHotSpot
    ArrayList<MergeOldHotSpot> mergeOldHotSpotArrayList;

    public MergeNewHotSpot(double x, double y, int m, int n) {
        this.x = x;
        this.y = y;
        this.m = m;
        this.n = n;
        this.mergeOldHotSpotArrayList = new ArrayList<>();
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

    public ArrayList<MergeOldHotSpot> getMergeOldHotSpotArrayList() {
        return mergeOldHotSpotArrayList;
    }

    public void setMergeOldHotSpotArrayList(ArrayList<MergeOldHotSpot> mergeOldHotSpotArrayList) {
        this.mergeOldHotSpotArrayList = mergeOldHotSpotArrayList;
    }
}
