package com.example.demo.OCR;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author: YJY
 * @date: 2024/2/23 16:27
 * @description:
 */
public class ExcelText {

//  public static void main(String[] args) {
//    try {
//      File file = new File("F:\\安徽智行用户签约过的用户.xlsx"); // 指定Excel文件路径
//      FileInputStream fis = new FileInputStream(file);
//      Workbook workbook = WorkbookFactory.create(fis);
//
//      Sheet sheet = workbook.getSheetAt(0); // 获取第一个工作表
//      for (Row row : sheet) {
//        for (Cell cell : row) {
//          CellType cellType = cell.getCellType();
//          if (cellType == CellType.STRING) {
//            System.out.print(cell.getStringCellValue() + "\t");
//          } else if (cellType == CellType.NUMERIC) {
//            System.out.print(cell.getNumericCellValue() + "\t");
//          }
//        }
//        System.out.println();
//      }
//
//      workbook.close();
//      fis.close();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//  }


  public static void main(String[] args) {
    HashMap<String,String> U = new HashMap();
    File folder = new File("G:\\image"); // 指定目录路径
    File[] files = folder.listFiles(); // 获取目录下的所有文件

    if (files != null) {
      for (File file : files) {
        if (file.isFile() && file.getName().endsWith(".jpg")) {
          try {

        //  System.out.println(file.getName().split("_")[0]+":"+file.getName().split("_")[1]); // 输出以.jpg结尾的文件名
          if(!U.containsKey(file.getName().split("_")[1])){
            U.put(file.getName().split("_")[1],file.getName().split("_")[0]);
          }
          }catch (Exception e){}
        }
      }
    }
    System.out.println(U.size());


        try {
      File file = new File("F:\\安徽智行用户签约过的用户.xlsx"); // 指定Excel文件路径
      FileInputStream fis = new FileInputStream(file);
      Workbook workbook = WorkbookFactory.create(fis);

      Sheet sheet = workbook.getSheetAt(0); // 获取第一个工作表
      for (Row row : sheet) {
        Cell cell1 = row.getCell(0); // 获取第一列的单元格
        Cell cell2 = row.getCell(1); // 获取第二列的单元格
      //  System.out.println(cell1+":"+cell2);
        if(!U.containsKey(cell2)){
          U.put(cell2+"",cell1+"");
        }
      }
      workbook.close();
      fis.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println(U.size());

    exportToExcel(U,"F:\\步骤1的数据.xlsx");
  }

  public static void exportToExcel(HashMap<String, String> data, String filePath) {
    try (Workbook workbook = new XSSFWorkbook()) {
      Sheet sheet = workbook.createSheet("Sheet1");

      int rowNum = 0;
      for (Map.Entry<String, String> entry : data.entrySet()) {
        Row row = sheet.createRow(rowNum++);
        row.createCell(0).setCellValue(entry.getKey());
        row.createCell(1).setCellValue(entry.getValue());
      }

      try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
        workbook.write(fileOut);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}

