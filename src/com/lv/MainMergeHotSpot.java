package com.lv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class MainMergeHotSpot {

    public static void main(String[] args) throws Exception {
        File inFile = new File("C:\\E\\dataSet\\2018-05-27\\初始备选点半径20米，第二次的备选点，半径70米\\selectedHotSpot(300)+sensor.txt");

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
            String sensor = data[4];
            MergeOldHotSpot oldHotSpot = new MergeOldHotSpot(x, y, m, n, sensor);

            for (MergeNewHotSpot mergeNewHotSpot : mergeNewHotSpotArrayList) {
                if (Points.getDistanceBetweenAndMergeOldHotSpotAndMergeNewHotSpot(oldHotSpot,mergeNewHotSpot) < 70) {
                    mergeNewHotSpot.getMergeOldHotSpotArrayList().add(oldHotSpot);
                }
            }
        }

        ArrayList<MergeNewHotSpot> selectedMergeNewHotSpot = new ArrayList<>();
        int count = 1;
        for (MergeNewHotSpot mergeNewHotSpot : mergeNewHotSpotArrayList) {
            if (mergeNewHotSpot.getMergeOldHotSpotArrayList().size() > 0) {
                mergeNewHotSpot.setNumber(count++);
                selectedMergeNewHotSpot.add(mergeNewHotSpot);
            }
        }

        File outFile = new File("C:\\E\\dataSet\\2018-05-27\\初始备选点半径20米，第二次的备选点，半径70米\\result(300).txt");
        FileWriter fileWriter = new FileWriter(outFile,true);
        for (MergeNewHotSpot mergeNewHotSpot : selectedMergeNewHotSpot) {

            String outString = mergeNewHotSpot.getX() +","+ mergeNewHotSpot.getY() + "," + mergeNewHotSpot.getNumber() + "\n";
            fileWriter.write(outString);
        }
        fileWriter.close();

    }
}
