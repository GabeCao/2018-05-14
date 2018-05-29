package com.lv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class MainMergeHotSpot {
    //获得第二次的备选点
    public static void main(String[] args) throws Exception {
        File inFile = new File("C:\\E\\dataSet\\2018-05-27\\阈值400\\10-50\\selectedHotspot.txt");

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
//            int m = Integer.parseInt(data[2]);
//            int n = Integer.parseInt(data[3]);
//            String sensor = data[4];
            MergeOldHotSpot oldHotSpot = new MergeOldHotSpot(x, y, 0, 0, 0+"");

            for (MergeNewHotSpot mergeNewHotSpot : mergeNewHotSpotArrayList) {
                //第二次备选点的半径
                if (Points.getDistanceBetweenAndMergeOldHotSpotAndMergeNewHotSpot(oldHotSpot,mergeNewHotSpot) < 50/*新备选点的半径*/) {
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

        File outFile = new File("C:\\E\\dataSet\\2018-05-27\\阈值400\\10-50\\result.txt");
        FileWriter fileWriter = new FileWriter(outFile,true);
        for (MergeNewHotSpot mergeNewHotSpot : selectedMergeNewHotSpot) {

            String outString = mergeNewHotSpot.getX() +","+ mergeNewHotSpot.getY() + "," + mergeNewHotSpot.getNumber() + "\n";
            fileWriter.write(outString);
        }
        fileWriter.close();

    }
}
