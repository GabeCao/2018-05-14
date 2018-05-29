package com.lv;

import java.util.ArrayList;
import java.util.Arrays;

public class HotSpots {

    private static Area area = new Area();

    public static ArrayList<HotSpot> twoDimesionToArrayList(HotSpot[][] hotSpots) {
        ArrayList<HotSpot> arrayList = new ArrayList<>();

        for (HotSpot[] hotSpotsArray : hotSpots) {
            arrayList.addAll(Arrays.asList(hotSpotsArray));
        }

        return arrayList;
    }
    public static HotSpot[][] getOldHotSpots() {
        double r = 10;
        double hotSpotDistance = Math.sqrt(3) * r;
        HotSpot[][] hotSpots = new HotSpot[74][95];
        int m = 0;
        for (double i = (hotSpotDistance / 2); i <= area.x; i += hotSpotDistance, m++) {
            int n = 0;
            for (double j = (hotSpotDistance / 2); j <= area.y; j +=hotSpotDistance, n++) {
                HotSpot hotSpot = new HotSpot(i, j, m, n);
                hotSpots[m][n] = hotSpot;
            }
//            System.out.println("n = " + n);
        }
//        System.out.println("m = " + m);
        return hotSpots;
    }

    public static HotSpot[][] getNewHotSpots() {
        double r = 50;
        double hotSpotDistance = Math.sqrt(3) * r;
        HotSpot[][] hotSpots = new HotSpot[15][19];
        int m = 0;
        for (double i = (hotSpotDistance / 2); i <= area.x; i += hotSpotDistance, m++) {
            int n = 0;
            for (double j = (hotSpotDistance / 2); j <= area.y; j +=hotSpotDistance, n++) {
                HotSpot hotSpot = new HotSpot(i, j, m, n);
                hotSpots[m][n] = hotSpot;
            }
            System.out.println("n = " + n);
        }
        System.out.println("m = " + m);
        return hotSpots;
    }
}
