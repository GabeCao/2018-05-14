package com.lv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception{
        String fileFolderPath = "C:\\E\\dataSet\\2018-05-27\\2018-05-27(去掉相同的时间点)";
        File fileFolder = new File(fileFolderPath);
        File[] files = fileFolder.listFiles();

        //获得HotSpots
        HotSpot[][] oldHotSpots = HotSpots.getOldHotSpots();
        //HotSpot[][] newHotSpots = HotSpots.getNewHotSpots();

        ArrayList<HotSpot> oldHotSpotArrayList = HotSpots.twoDimesionToArrayList(oldHotSpots);
        //ArrayList<HotSpot> newHotSpotArrayList = HotSpots.twoDimesionToArrayList(newHotSpots);

        for (File file : files) {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            System.out.println("开始准备数据写入.................");
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                double x = Double.parseDouble(data[0]);
                double y = Double.parseDouble(data[1]);
                Date date = simpleDateFormat.parse(data[2]);
                Point point = new Point(x, y, date);
                point.setFlag(false);

                for (HotSpot hotSpot : oldHotSpotArrayList) {
                    if (Points.getDistanceBetweenAndPontAndHotSpot(point,hotSpot) < 10) {
                        hotSpot.getPointArrayList().add(point);
                        hotSpot.setNumberOfPoint(hotSpot.getNumberOfPoint() + 1);
                    }
                }
            }
            ArrayList<HotSpot> selectedHotSpots = new ArrayList<>();
            for (HotSpot hotSpot : oldHotSpotArrayList) {
                if (hotSpot.getNumberOfPoint() > 100) {
                    selectedHotSpots.add(hotSpot);

                    for (Point point : hotSpot.getPointArrayList()) {
                        point.setFlag(true);
                    }

                    for (HotSpot hotSpot1 : oldHotSpotArrayList) {
                        hotSpot1.setNumberOfPoint(0);
                        for (Point point : hotSpot1.getPointArrayList()) {
                            if (point.isFlag() == false) {
                                hotSpot1.setNumberOfPoint(hotSpot1.getNumberOfPoint() + 1);
                            }
                        }
                    }

                }

            }

            Map<HotSpot, Set<Integer>> hotSpotSetMap = new HashMap<>();
            for (HotSpot hotSpot: selectedHotSpots) {
                Set<Integer> integerSet = new HashSet<>();
                for (Point point : hotSpot.getPointArrayList()) {
                    if (point.isFlag() == true) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(point.getDate());
                        integerSet.add(calendar.get(Calendar.HOUR_OF_DAY));
                    }
                }
                hotSpotSetMap.put(hotSpot, integerSet);
            }

            File outFile = new File("C:\\E\\dataSet\\2018-05-27\\selectedHotSpot(100)+sensor.txt");
            FileWriter fileWriter = new FileWriter(outFile,true);

            for (Map.Entry<HotSpot, Set<Integer>> entry : hotSpotSetMap.entrySet()) {
                String outString = entry.getKey().getX() + "," + entry.getKey().getY() + ","
                        + entry.getKey().getM() + "," + entry.getKey().getN() +
                        "," + file.getName().substring(0,file.getName().indexOf("."));
                Set<Integer> integerSet = entry.getValue();
                for (Integer integer : integerSet) {
                    outString += "," +integer;
                }
                fileWriter.write(outString + "\n");
            }
//            for (HotSpot hotSpot : selectedHotSpots) {
//                String outString = hotSpot.getX() + "," + hotSpot.getY() + "," + hotSpot.getM() + "," + hotSpot.getN() +
//                        "," + file.getName().substring(0,file.getName().indexOf("."))+"\n";
//                fileWriter.write(outString);
//            }
            fileWriter.close();

            System.out.println("写入完成...............");
        }
    }
}
