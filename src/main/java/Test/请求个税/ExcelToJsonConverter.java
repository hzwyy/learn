package Test.请求个税;

import java.io.File;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ExcelToJsonConverter {

  private static final Map<String, String> orderNoMap = new HashMap<>();
  private static final AtomicInteger counter = new AtomicInteger(1);
  private static final String FILE_PATH = "F:\\临时数据\\24年1月.xls";

  public static void main(String[] args) throws Exception {
    String data = getExcelData();
    System.out.println(data);
  }

  public static String getExcelData() throws Exception {

    // 1. 读取Excel文件（使用HSSF处理.xls格式）
    FileInputStream file = new FileInputStream(FILE_PATH);
    Workbook workbook = new HSSFWorkbook(file);
    Sheet sheet = workbook.getSheetAt(0);

    // 2. 准备JSON数据列表
    List<Map<String, Object>> jsonDataList = new ArrayList<>();

    // 3. 读取表头
    Row headerRow = sheet.getRow(0);
    List<String> headers = new ArrayList<>();
    for (Cell cell : headerRow) {
      headers.add(cell.getStringCellValue().trim());
    }

    // 4. 遍历数据行
    for (int i = 1; i <= sheet.getLastRowNum(); i++) {
      Row row = sheet.getRow(i);
      if (row == null) {
        continue;
      }

      Map<String, Object> rowData = new LinkedHashMap<>();
      StringBuilder rowKey = new StringBuilder();

      // 5. 处理每一列数据
      for (int j = 0; j < headers.size(); j++) {
        Cell cell = row.getCell(j);
        String value = cell == null ? "" : parseCellValue(cell);

        // 构建唯一键（证件号+月份+金额）
        rowKey.append(value).append("|");

        // 映射字段名
        String header = headers.get(j);
        switch (header) {
          case "证件号":
            rowData.put("cardId", value);
            break;
          case "收款人姓名":
            rowData.put("userName", value);
            break;
          case "交易金额":
            rowData.put("taxBeforeAmount", value);
            break;
          case "服务公司":
            rowData.put("taxFilingEntityName", value);
            break;
          case "交易时间":
            rowData.put("statisticMonth", formatDate(value));
            break;

        }
      }

      // 获取文件名，不要路径，避免路径变化导致ID变化
      String fileName = new File(FILE_PATH).getName(); // 24年1月.xls

      // 生成唯一Key：文件名 + 行数据
      String uniqueKey = md5(fileName + "_" + rowKey);

      // 订单号：取MD5前8位，或者映射成自增编号
      String orderNo = "A" + uniqueKey.substring(0, 8).toUpperCase();

      rowData.put("orderNo", orderNo);
      rowData.put("status",1);
      rowData.put("uniformity",2);
      rowData.put("calculatedAmount",1);

      // 7. 添加固定字段
      rowData.put("taxFilingEntityNumber", "910106322018070725");
      jsonDataList.add(rowData);
    }

    workbook.close();
    file.close();

    // 8. 转换为JSON字符串
    ObjectMapper mapper = new ObjectMapper();

    return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonDataList);

  }

  // 生成MD5
  public static String md5(String input) {
    try {
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] digest = md.digest(input.getBytes("UTF-8"));
      StringBuilder sb = new StringBuilder();
      for (byte b : digest) {
        sb.append(String.format("%02x", b));
      }
      return sb.toString();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private static String parseCellValue(Cell cell) {
    switch (cell.getCellType()) {
      case STRING:
        return cell.getStringCellValue().trim();
      case NUMERIC:
        if (DateUtil.isCellDateFormatted(cell)) {
          return new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue());
        } else {
          double num = cell.getNumericCellValue();
          return num % 1 == 0 ? String.valueOf((int) num) : String.valueOf(num);
        }
      case BOOLEAN:
        return String.valueOf(cell.getBooleanCellValue());
      default:
        return "";
    }
  }

  private static String formatDate(String dateStr) {
    try {
      // 如果已经是yyyy-MM格式，直接返回
      if (dateStr.matches("\\d{4}-\\d{2}")) {
        return dateStr;
      }
      // 否则尝试解析并格式化
      SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
      SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM");
      return outputFormat.format(inputFormat.parse(dateStr));
    } catch (Exception e) {
      return dateStr; // 解析失败时返回原值
    }
  }
}