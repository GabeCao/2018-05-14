package com.lv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class MainMergeHotSpot {

    public static void main(String[] args) throws Exception {
        File inFile = new File("C:\\E\\dataSet\\2018-05-14\\selectedHotSpot(200)+sensor.txt");

        FileReader fileReader = new FileReader(inFile);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;


        HotSpot[][] newHotSpots = HotSpots.getNewHotSpots();
        ArrayList<HotSpot> newHotSpotArrayList = HotSpots.twoDimesionToArrayList(newHotSpots);

        ArrayList<MergeNewHotSpot> mergeNewHotSpotArrayList = new ArrayList<>();
        for (HotSpot hotSpot : newHotSpotArrayList) {
            MergeNewHotSpot mergeNewHotSpot = new MergeNewHotSpot(hotSpot.getX(), hotSpot.getY(), hotSpot.getM(), hotSpot.getN());
            mergeNewHotSpotArrayList.add(mergeNewHotSpot);
        }
        while ((line = bufferedReader.readLine()) != null) {
            String[] data = line.split(",");
            double x = Double.parseDouble(data[0]);
            double y = Double.parseDouble(data[1]);
            int m = Integer.parseInt(data[2]);
            int n = Integer.parseInt(data[3]);
            int sensor = Integer.parseInt(data[4]);
            MergeOldHotSpot oldHotSpot = new MergeOldHotSpot(x, y, m, n, sensor);

            for (MergeNewHotSpot mergeNewHotSpot : mergeNewHotSpotArrayList) {
                if (Points.getDistanceBetweenAndMergeOldHotSpotAndMergeNewHotSpot(oldHotSpot,mergeNewHotSpot) < 30) {
                    mergeNewHotSpot.getMergeOldHotSpotArrayList().add(oldHotSpot);
                }
            }
        }

        ArrayList<MergeNewHotSpot> selectedMergeNewHotSpot = new ArrayList<>();
        for (MergeNewHotSpot mergeNewHotSpot : mergeNewHotSpotArrayList) {
            if (mergeNewHotSpot.getMergeOldHotSpotArrayList().size() > 0) {
                selectedMergeNewHotSpot.add(mergeNewHotSpot);
            }
        }
        System.out.println(selectedMergeNewHotSpot.size());

    }
}