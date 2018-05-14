package com.lv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main {

    public static void main(String[] args) throws Exception{
        String fileFolderPath = "C:\\E\\dataSet\\2018-05-14\\2009-03-09(最后数据)";
        File fileFolder = new File(fileFolderPath);
        File[] files = fileFolder.listFiles();

        //获得HotSpots
        HotSpot[][] oldHotSpots = HotSpots.getOldHotSpots();
        HotSpot[][] newHotSpots = HotSpots.getNewHotSpots();

        ArrayList<HotSpot> oldHotSpotArrayList = HotSpots.twoDimesionToArrayList(oldHotSpots);
        ArrayList<HotSpot> newHotSpotArrayList = HotSpots.twoDimesionToArrayList(newHotSpots);

        for (File file : files) {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            System.out.println("开始准备数据写入.................");
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                double x = Double.parseDouble(data[0]);
                double y = Double.parseDouble(data[1]);
                Date date = simpleDateFormat.parse(data[2] + " " + data[3]);
                Point point = new Point(x, y, date);

                for (HotSpot hotSpot : oldHotSpotArrayList) {
                    if (Points.getDistanceBetweenAndPontAndHotSpot(point,hotSpot) < 10) {
                        hotSpot.getPointArrayList().add(point);
                    }
                }
            }
            ArrayList<HotSpot> selectedHotSpots = new ArrayList<>();
            for (HotSpot hotSpot : oldHotSpotArrayList) {
                if (hotSpot.getPointArrayList().size() > 500) {
                    selectedHotSpots.add(hotSpot);
                }
                hotSpot.getPointArrayList().clear();
            }

            File outFile = new File("C:\\E\\dataSet\\2018-05-14\\选取的HotSpot\\" + file.getName());
            FileWriter fileWriter = new FileWriter(outFile,true);

            for (HotSpot hotSpot : selectedHotSpots) {
                String outString = hotSpot.getX() + "," + hotSpot.getY() + "," + hotSpot.getM() + "," + hotSpot.getN() + "\n";
                fileWriter.write(outString);
            }
            fileWriter.close();

            System.out.println("写入完成...............");
        }

        /*File file = files[0];*/
        /*FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(new Date() + "........................");
        while ((line = bufferedReader.readLine()) != null) {
            String[] data = line.split(",");
            double x = Double.parseDouble(data[0]);
            double y = Double.parseDouble(data[1]);
            Date date = simpleDateFormat.parse(data[2] + " " + data[3]);
            Point point = new Point(x, y, date);

            for (HotSpot hotSpot : oldHotSpotArrayList) {
                if (Points.getDistanceBetweenAndPontAndHotSpot(point,hotSpot) < 10) {
                    hotSpot.getPointArrayList().add(point);
                }
            }
        }
        System.out.println(new Date() + "........................");
        ArrayList<HotSpot> selectedHotSpot = new ArrayList<>();

        for (HotSpot hotSpot : oldHotSpotArrayList) {
            if (hotSpot.getPointArrayList().size() > 500) {
                selectedHotSpot.add(hotSpot);
            }
        }

        File outFile = new File("C:\\E\\dataSet\\2018-05-14\\选取的HotSpot\\" + file.getName());
        FileWriter fileWriter = new FileWriter(outFile,true);
        for (HotSpot hotSpot : selectedHotSpot) {
            fileWriter.write(hotSpot.toString());
        }
        fileWriter.close();*/


        /*for (File file : files) {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                double x = Double.parseDouble(data[0]);
                double y = Double.parseDouble(data[1]);
                Date date = simpleDateFormat.parse(data[2] + " " + data[3]);
                Point point = new Point(x, y, date);

                for (HotSpot hotSpot : oldHotSpotArrayList) {
                    if (Points.getDistanceBetweenAndPontAndHotSpot(point,hotSpot) < 10) {
                        hotSpot.getPointArrayList().add(point);
                    }
                }
            }
        }*/



    }
}
