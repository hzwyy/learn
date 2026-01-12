package Test.请求个税;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class HttpPostTestPITCalcul {

  // 试算--单笔
  public static final String singleUrl = "http://127.0.0.1:3780/pit/personal/income/tax/taxCalculationTry/single.do";


  public static final String REALTIMEURL = "http://192.168.1.17:3780/pit/personal/income/tax/taxCalculationTry/realTime.do";


  public static final String resultUrl = "http://127.0.0.1:3780/pit/personal/income/tax/taxCalculationTry/result.do";

  public static final String FILE_PATH = "F://临时数据//24年2月.xls";



  public static void main(String[] args) throws Exception {


    String jsonData = ExcelToJsonConverter.getExcelData();
    for (Object object : JSONArray.parseArray(jsonData)) {
      getRequest(resultUrl, object.toString());
    }


  }


  public static void getRequest(String url, String jsonInput) {

    try {
      // 创建 URL 对象
      URL targetUrl = URI.create(url).toURL();
      HttpURLConnection connection = (HttpURLConnection) targetUrl.openConnection();

      // 设置请求方法
      connection.setRequestMethod("POST");

      // 设置请求头
      connection.setRequestProperty("Content-Type", "application/json; utf-8");
      connection.setRequestProperty("Accept", "application/json");
      connection.setDoOutput(true); // 允许输出

      // 发送请求体
      try (OutputStream os = connection.getOutputStream()) {
        byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
        os.write(input, 0, input.length);
      }

      // 读取响应
      int responseCode = connection.getResponseCode();
      System.out.println("响应状态码: " + responseCode);

      StringBuilder response = new StringBuilder();
      try (BufferedReader br = new BufferedReader(
          new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
        String responseLine;
        while ((responseLine = br.readLine()) != null) {
          response.append(responseLine).append("\n");
        }
      }

      // 打印响应内容
      System.out.println("响应内容:");
      System.out.println(response.toString());

      // 关闭连接
      connection.disconnect();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static String getSingleParams() {

    // 参数集合
    Map<String, Object> params = new HashMap<>();

    params.put("cardId", "452723199005092813");
    params.put("orderNo", "A102");
    params.put("statisticMonth", "2025-08");
    params.put("taxBeforeAmount", "10000");
    params.put("taxFilingEntityName", "28617测试服务公司001");
    params.put("taxFilingEntityNumber", "910106322018070725");
    params.put("userName", "吴福进");

    String json = JSON.toJSONString(params);

    System.out.println(" getSingleParams JSON: " + json);

    return json;
  }


  public static String getMultipleParams() {

    List<Map<String, Object>> dataList = new ArrayList<>();
    // 参数集合
    Map<String, Object> params = new HashMap<>();

    params.put("cardId", "452723199005092813");
    params.put("orderNo", "A101");
    params.put("statisticMonth", "2025-08");
    params.put("taxBeforeAmount", "5000");
    params.put("taxFilingEntityName", "28617测试服务公司001");
    params.put("taxFilingEntityNumber", "910106322018070725");
    params.put("userName", "吴福进");

    Map<String, Object> params2 = new HashMap<>();

    params2.put("cardId", "452723199005092813");
    params2.put("orderNo", "A201");
    params2.put("statisticMonth", "2025-08");
    params2.put("taxBeforeAmount", "2000");
    params2.put("taxFilingEntityName", "28617测试服务公司001");
    params2.put("taxFilingEntityNumber", "910106322018070725");
    params2.put("userName", "吴福进");

    dataList.add(params);
    dataList.add(params2);

    ObjectMapper objectMapper = new ObjectMapper();
    String json = "";
    try {
      json = objectMapper.writeValueAsString(dataList);

    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println(" getMultipleParams JSON: " + json);

    return json;
  }


  public static String getMultipleParamsLFY() {

    List<Map<String, Object>> dataList = new ArrayList<>();
    // 参数集合
    Map<String, Object> params = new HashMap<>();

    params.put("cardId", "130629200012010814");
    params.put("orderNo", "C1001");
    params.put("statisticMonth", "2025-08");
    params.put("taxBeforeAmount", "5000");
    params.put("taxFilingEntityName", "28617测试服务公司001");
    params.put("taxFilingEntityNumber", "910106322018070725");
    params.put("userName", "刘峰源");

    Map<String, Object> params2 = new HashMap<>();

    params2.put("cardId", "130629200012010814");
    params2.put("orderNo", "C1002");
    params2.put("statisticMonth", "2025-08");
    params2.put("taxBeforeAmount", "5000");
    params2.put("taxFilingEntityName", "28617测试服务公司001");
    params2.put("taxFilingEntityNumber", "910106322018070725");
    params2.put("userName", "刘峰源");

    dataList.add(params);
    dataList.add(params2);

    ObjectMapper objectMapper = new ObjectMapper();
    String json = "";
    try {
      json = objectMapper.writeValueAsString(dataList);

    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println(" getMultipleParams JSON: " + json);

    return json;
  }


  public static String getMultipleParamsWHB() {

    List<Map<String, Object>> dataList = new ArrayList<>();
    // 参数集合
    Map<String, Object> params = new HashMap<>();

    params.put("cardId", "142625199807063015");
    params.put("orderNo", "W1001");
    params.put("statisticMonth", "2025-08");
    params.put("taxBeforeAmount", "1000");
    params.put("taxFilingEntityName", "28617测试服务公司001");
    params.put("taxFilingEntityNumber", "910106322018070725");
    params.put("userName", "王慧斌");

    Map<String, Object> params2 = new HashMap<>();

    params2.put("cardId", "142625199807063015");
    params2.put("orderNo", "W1002");
    params2.put("statisticMonth", "2025-08");
    params2.put("taxBeforeAmount", "1000");
    params2.put("taxFilingEntityName", "28617测试服务公司001");
    params2.put("taxFilingEntityNumber", "910106322018070725");
    params2.put("userName", "王慧斌");

    Map<String, Object> params3 = new HashMap<>();

    params3.put("cardId", "142625199807063015");
    params3.put("orderNo", "W1003");
    params3.put("statisticMonth", "2025-08");
    params3.put("taxBeforeAmount", "1000");
    params3.put("taxFilingEntityName", "28617测试服务公司001");
    params3.put("taxFilingEntityNumber", "910106322018070725");
    params3.put("userName", "王慧斌");

    Map<String, Object> params4 = new HashMap<>();

    params4.put("cardId", "142625199807063015");
    params4.put("orderNo", "W1004");
    params4.put("statisticMonth", "2025-08");
    params4.put("taxBeforeAmount", "1000");
    params4.put("taxFilingEntityName", "28617测试服务公司001");
    params4.put("taxFilingEntityNumber", "910106322018070725");
    params4.put("userName", "王慧斌");

    Map<String, Object> params5 = new HashMap<>();

    params5.put("cardId", "142625199807063015");
    params5.put("orderNo", "W1005");
    params5.put("statisticMonth", "2025-08");
    params5.put("taxBeforeAmount", "1000");
    params5.put("taxFilingEntityName", "28617测试服务公司001");
    params5.put("taxFilingEntityNumber", "910106322018070725");
    params5.put("userName", "王慧斌");

    Map<String, Object> params6 = new HashMap<>();

    params6.put("cardId", "142625199807063015");
    params6.put("orderNo", "W1006");
    params6.put("statisticMonth", "2025-08");
    params6.put("taxBeforeAmount", "1000");
    params6.put("taxFilingEntityName", "28617测试服务公司001");
    params6.put("taxFilingEntityNumber", "910106322018070725");
    params6.put("userName", "王慧斌");

    Map<String, Object> params7 = new HashMap<>();

    params7.put("cardId", "142625199807063015");
    params7.put("orderNo", "W1007");
    params7.put("statisticMonth", "2025-08");
    params7.put("taxBeforeAmount", "1000");
    params7.put("taxFilingEntityName", "28617测试服务公司001");
    params7.put("taxFilingEntityNumber", "910106322018070725");
    params7.put("userName", "王慧斌");

    Map<String, Object> params8 = new HashMap<>();

    params8.put("cardId", "142625199807063015");
    params8.put("orderNo", "W1008");
    params8.put("statisticMonth", "2025-08");
    params8.put("taxBeforeAmount", "1000");
    params8.put("taxFilingEntityName", "28617测试服务公司001");
    params8.put("taxFilingEntityNumber", "910106322018070725");
    params8.put("userName", "王慧斌");

    Map<String, Object> params9 = new HashMap<>();

    params9.put("cardId", "142625199807063015");
    params9.put("orderNo", "W1009");
    params9.put("statisticMonth", "2025-08");
    params9.put("taxBeforeAmount", "1000");
    params9.put("taxFilingEntityName", "28617测试服务公司001");
    params9.put("taxFilingEntityNumber", "910106322018070725");
    params9.put("userName", "王慧斌");

    Map<String, Object> params10 = new HashMap<>();

    params10.put("cardId", "142625199807063015");
    params10.put("orderNo", "W1010");
    params10.put("statisticMonth", "2025-08");
    params10.put("taxBeforeAmount", "1000");
    params10.put("taxFilingEntityName", "28617测试服务公司001");
    params10.put("taxFilingEntityNumber", "910106322018070725");
    params10.put("userName", "王慧斌");

    dataList.add(params);
    dataList.add(params2);
    dataList.add(params3);
    dataList.add(params4);
    dataList.add(params5);
    dataList.add(params6);
    dataList.add(params7);
    dataList.add(params8);
    dataList.add(params9);
    dataList.add(params10);

    ObjectMapper objectMapper = new ObjectMapper();
    String json = "";
    try {
      json = objectMapper.writeValueAsString(dataList);

    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println(" getMultipleParams JSON: " + json);

    return json;
  }


  public static String getExcelData(String path) throws Exception {

    // 读取Excel文件 - 使用HSSFWorkbook处理.xls文件
    FileInputStream file = new FileInputStream(path);
    Workbook workbook = new HSSFWorkbook(file);
    Sheet sheet = workbook.getSheetAt(0); // 获取第一个工作表

    List<Map<String, String>> jsonDataList = new ArrayList<>();

    // 获取表头
    Row headerRow = sheet.getRow(0);
    List<String> headers = new ArrayList<>();
    for (Cell cell : headerRow) {
      headers.add(cell.getStringCellValue().trim());
    }

    // 遍历数据行
    for (int i = 1; i <= sheet.getLastRowNum(); i++) {
      Row row = sheet.getRow(i);
      if (row == null) {
        continue;
      }

      Map<String, String> rowData = new LinkedHashMap<>();
      StringBuilder rowKeyBuilder = new StringBuilder();

      // 处理每一列数据
      for (int j = 0; j < headers.size(); j++) {
        Cell cell = row.getCell(j);
        String value = "";

        if (cell != null) {
          switch (cell.getCellType()) {
            case STRING:
              value = cell.getStringCellValue().trim();
              break;
            case NUMERIC:
              if (DateUtil.isCellDateFormatted(cell)) {
                // 处理日期格式
                Date date = cell.getDateCellValue();
                value = new SimpleDateFormat("yyyy-MM").format(date);
              } else {
                // 处理数字
                value = String.valueOf(cell.getNumericCellValue());
                // 如果是整数，去掉小数部分
                if (value.endsWith(".0")) {
                  value = value.substring(0, value.length() - 2);
                }
              }
              break;
            case BOOLEAN:
              value = String.valueOf(cell.getBooleanCellValue());
              break;
            default:
              value = "";
          }
        }

        // 构建行唯一键
        rowKeyBuilder.append(value).append("|");

        // 根据Excel列名映射到JSON字段名
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
            rowData.put("statisticMonth", value);
            break;
          // 其他字段可以保留
          default:
            rowData.put(header, value);
        }
      }

      // 生成唯一且稳定的订单号 - 基于整行数据的哈希值
      int rowHash = rowKeyBuilder.toString().hashCode();
      rowData.put("orderNo", rowHash + "");

      // 添加固定值
      rowData.put("taxFilingEntityNumber", "91360429MABWEPPH98");

      jsonDataList.add(rowData);
    }

    workbook.close();
    file.close();

    // 转换为JSON字符串
    ObjectMapper mapper = new ObjectMapper();
    return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonDataList);

  }
}