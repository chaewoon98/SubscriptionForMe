package com.example.subscriptionforme.csv;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class OpenCsv extends Thread {

    public OpenCsv() throws IOException {
        writeDataFromCsv("./sample.csv");
    }

    // 파일 쓰기

    public static void writeDataFromCsv(String filePath) throws IOException{
        CSVWriter writer = null;
        try {
            writer = new CSVWriter(new FileWriter(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] entries = "resUsedDate#resUsedCard#resMemberStoreName#resUsedAmount#resMemberStoreType"
                .split("#");  // 1
        writer.writeNext(entries);  // 2

        String[] entries1 = {"2020-08-01", "KB", "버거킹","8000","버거"};  // 3
        writer.writeNext(entries1);

        String[] entries2 = {"2020-08-01", "KB", "버거킹","8000","버거"};
        writer.writeNext(entries2);

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 파일 읽기
    public void readDataFromCsv(String filePath) throws IOException {
        CSVReader reader = new CSVReader(new FileReader(filePath)); // 1
        String [] nextLine;
        while ((nextLine = reader.readNext()) != null) {   // 2
            for (int i = 0; i < nextLine.length; i++) {
                System.out.println(i + " " + nextLine[i]);
            }
            System.out.println();
        }
    }


}
