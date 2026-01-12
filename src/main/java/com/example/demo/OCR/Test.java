package com.example.demo.OCR;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author: YJY
 * @date: 2023/9/15 15:49
 * @description:
 */
public class Test {

  public static void main(String[] args) {
    String folderPath = "G:\\用户证件信息\\识别成功的用户";
    String outputPath = "G:\\用户证件信息\\识别成功的用户\\image_list.txt";

    File folder = new File(folderPath);
    File[] files = folder.listFiles();

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
      for (File file : files) {
        if (file.isFile() && file.getName().contains("front")) {
          String fileName = file.getName();
          int endIndex = fileName.lastIndexOf("_front");
          if (endIndex != -1) {
            String imageName = fileName.substring(0, endIndex);
            imageName = imageName.replace("_"," ");
            writer.write(imageName + "\n");
          }
        }
      }
      System.out.println("Image list generated successfully!");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
