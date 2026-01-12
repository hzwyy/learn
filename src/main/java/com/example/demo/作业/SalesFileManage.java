package com.example.demo.作业;

import static com.example.demo.作业.ConstantClass.sales;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author: YJY
 * @date: 2024/11/1 17:26
 * @description:
 */
public class SalesFileManage {

  // 写入出售记录到文件
  public static void saveSalesRecordsToFile(String filename) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
      for (SaleRecord record : sales) {
        writer.write(record.toString());
        writer.newLine();
      }
    }
  }

  // 从文件加载出售记录
  public static ArrayList<String> loadSalesRecordsFromFile(String filename) throws IOException {
    ArrayList<String> sales = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = reader.readLine()) != null) {
        sales.add(line);
      }
    }
    return sales;
  }

}
