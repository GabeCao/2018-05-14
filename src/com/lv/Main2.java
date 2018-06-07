package com.lv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class Main2 {
//    将sensor的数据进行改变，每两秒钟取一次
    public static void main(String[] args) throws Exception{
        File fileFolder = new File("C:\\E\\dataSet\\2018-05-29\\sensor数据");
        File[] files = fileFolder.listFiles();
        for (File file : files) {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            File outFile = new File("C:\\E\\dataSet\\2018-05-29\\sensor数据(两秒)\\" + file.getName());
            FileWriter fileWriter = new FileWriter(outFile, true);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileWriter.write(line + "\n");
                line = bufferedReader.readLine();
            }
            fileWriter.close();
        }
    }
}
