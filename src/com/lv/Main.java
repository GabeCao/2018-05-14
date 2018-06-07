package com.lv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
//获得第一次的备选点
    public static void main(String[] args) throws Exception{
        String fileFolderPath = "C:\\E\\dataSet\\2018-05-29\\四秒\\sensor数据四秒";
        File fileFolder = new File(fileFolderPath);
        File[] files = fileFolder.listFiles();

        //获得HotSpots
        HotSpot[][] oldHotSpots = HotSpots.getOldHotSpots();

        ArrayList<HotSpot> oldHotSpotArrayList = HotSpots.twoDimesionToArrayList(oldHotSpots);

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

                for (HotSpot hotSpot : oldHotSpotArrayList) {
                    //第一次半径

                    if (Points.getDistanceBetweenAndPontAndHotSpot(point,hotSpot) < 10/*旧备选点的半径*/) {
                        hotSpot.getPointArrayList().add(point);
                    }
                }
            }
            ArrayList<HotSpot> selectedHotSpots = new ArrayList<>();
            for (HotSpot hotSpot : oldHotSpotArrayList) {
                //阈值

                if (hotSpot.getPointArrayList().size() > 300) {
                    selectedHotSpots.add(hotSpot);

                }

            }

            File outFile = new File("C:\\E\\dataSet\\2018-05-29\\四秒\\阈值300\\selectedHotspot.txt");
            FileWriter fileWriter = new FileWriter(outFile,true);
            for (HotSpot hotSpot : selectedHotSpots) {
                String outString = hotSpot.getX() + "," + hotSpot.getY() + "," + hotSpot.getM() + "," + hotSpot.getN() +
                        "," + file.getName().substring(0,file.getName().indexOf("."))+"\n";
                fileWriter.write(outString);
            }
            fileWriter.close();

            System.out.println("写入完成...............");
        }
    }
}
