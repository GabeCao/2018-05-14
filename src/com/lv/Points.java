package com.lv;

import java.util.ArrayList;

public class Points {

    //判断 Point 和 HotSpot 点之间的距离
    public static double getDistanceBetweenAndPontAndHotSpot(Point p1, HotSpot p2) {
        return Math.sqrt(Math.abs((p1.getX() - p2.getX())* (p1.getX() - p2.getX())+(p1.getY() - p2.getY())* (p1.getY() - p2.getY())));
    }

    public static double getDistanceBetweenAndMergeOldHotSpotAndMergeNewHotSpot(MergeOldHotSpot p1, MergeNewHotSpot p2) {
        return Math.sqrt(Math.abs((p1.getX() - p2.getX())* (p1.getX() - p2.getX())+(p1.getY() - p2.getY())* (p1.getY() - p2.getY())));
    }

    //找到 HotSpot 周围 半径小于 10 米的 Point
    public  static void getNearPoints(HotSpot hotSpot, ArrayList<Point> points) {

        for (Point point : points) {
            if (getDistanceBetweenAndPontAndHotSpot(point,hotSpot) < 10) {
                hotSpot.getPointArrayList().add(point);
            }
        }
    }
}
