package com.example.demo.OCR;

import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author: YJY
 * @date: 2024/2/28 17:35
 * @description:
 */
public class ExcelTest1 {
  public static void main(String[] args) {
    String[] headers = {"活跃状态", "期望职位"};
    String[] labels = {"数据标签", "数量", "占比"};
    double[] activeStatus = {100, 200, 0.5};
    double[] desiredPosition = {50, 100, 0.3};

    try (Workbook workbook = new XSSFWorkbook()) {
      Sheet sheet = workbook.createSheet("Data");

      // Create header row
      Row headerRow = sheet.createRow(0);
      for (int i = 0; i < headers.length; i++) {
        Cell cell = headerRow.createCell(i);
        cell.setCellValue(headers[i]);
        sheet.addMergedRegion(new CellRangeAddress(0, 0, i * 3, i * 3 + 2));

        for (int j = 1; j < 4; j++) {
          Row headerRow1 = sheet.createRow(1);
          Cell labelCell = headerRow1.createCell(i+j);
          labelCell.setCellValue(labels[j - 1]);

        }
      }

      // Create data rows
      Row dataRow1 = sheet.createRow(1);
//      for (int i = 0; i < activeStatus.length; i++) {
//        Cell cell = dataRow1.createCell(i);
//        cell.setCellValue(activeStatus[i]);
//      }

      Row dataRow2 = sheet.createRow(2);
//      for (int i = 0; i < desiredPosition.length; i++) {
//        Cell cell = dataRow2.createCell(i);
//        cell.setCellValue(desiredPosition[i]);
//      }

      // Write the output to a file
      try (FileOutputStream fileOut = new FileOutputStream("F:/output.xlsx")) {
        workbook.write(fileOut);
      }

      System.out.println("Excel file exported successfully!");

    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
