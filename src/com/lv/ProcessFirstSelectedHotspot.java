package com.lv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashSet;
import java.util.Set;

public class ProcessFirstSelectedHotspot {

    //处理selectedHotSpot+sensor.txt 中重复的数据
    public static void main(String[] args) throws Exception{
        File file = new File("C:\\E\\dataSet\\2018-05-29\\sensor数据(两秒)选择的hotspot\\阈值400\\selectedHotspot.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;

        Set<ProPoint> proPointSet = new HashSet<>();

        while ((line = bufferedReader.readLine()) != null) {
            String[] data = line.split(",");
            Double x = Double.parseDouble(data[0]);
            Double y = Double.parseDouble(data[1]);
            ProPoint proPoint = new ProPoint(x, y);
            proPointSet.add(proPoint);
        }

        File outFile = new File("C:\\E\\dataSet\\2018-05-29\\sensor数据(两秒)选择的hotspot\\阈值400\\firstSelectedHotspot.txt");
        FileWriter fileWriter = new FileWriter(outFile, true);
        for (ProPoint proPoint : proPointSet) {
            String s = proPoint.getX() + "," + proPoint.getY();
            fileWriter.write(s + "\n");

        }
        fileWriter.close();
    }
}
class ProPoint {
    private Double x;
    private Double y;

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public ProPoint(Double x, Double y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode() {
        return this.x.hashCode() + this.y.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof ProPoint) {
            return this.x.equals(((ProPoint) obj).x) && this.y.equals(((ProPoint) obj).y);
        } else {
            return false;
        }
    }
}