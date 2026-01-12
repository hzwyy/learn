package com.example.demo.OCR;

import com.alibaba.fastjson.JSONObject;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

/**
 * @author: YJY
 * @date: 2023/12/1 14:45
 * @description:
 */
public class CheckTwoTest {

//  public static void main(String[] args) {
//
//
//    Map<String, String>  ha =  checkTwoElements("孟得程","130283200210075658");
//    System.out.println(ha);
//  }

//  public static void main(String[] args) {
//
//    Map<String, String> successUser = new HashMap<>();
//    Map<String, String> errUser = new HashMap<>();
//    try {
//      FileInputStream file = new FileInputStream("F:/众智诚附险投保名单2月.xls");
//      Workbook workbook = WorkbookFactory.create(file);
//      Sheet sheet = workbook.getSheetAt(0);
//
//      for (Row row : sheet) {
//        // 跳过第一行标题
//        if (row.getRowNum() == 0) {
//          continue;
//        }
//        // 输出第二列和第四列的内容
//        Cell cell2 = row.getCell(1); // 第二列的索引为1
//        Cell cell4 = row.getCell(3); // 第四列的索引为3
//        String user = cell2.toString() + "\t" + cell4.toString();
//        System.out.println(user + ":开始验证");
//        Map<String, String> ha = checkTwoElements(cell2.toString(), cell4.toString());
//        if (!ha.get("status").equals("success")) {
//          System.out.println(user + ":验证失败" + ha.get("msg").toString());
//          errUser.put(user,ha.get("msg"));
//        } else {
//          successUser.put(user,"success");
//          System.out.println(user + ":验证成功");
//        }
//      }
//
//      workbook.close();
//      file.close();
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//
//    System.out.println("成功人数:"+successUser.keySet().size());
//    System.out.println("失败人数:"+errUser.keySet().size());
//    System.out.println("请求结束!!!!!");
//  }

  public static void main(String[] args) {
    Map<String, String>  ha =  checkTwoElements("陈俊峰","142724197204220210");
    System.out.println(ha);
  }

  public static Map<String, String> checkTwoElements(String userName, String certId) {

    String host = "https://idcert.market.alicloudapi.com";
    String path = "/idcard";
    Map<String, String> headers = new HashMap<>(2);
    //注意中间是英文空格
    headers.put("Authorization", "APPCODE " + "967b8ae4f53a4ff0b44541f501eb095c");
    Map<String, String> query = new HashMap<>(4);
    query.put("idCard", certId);
    query.put("name", userName);
    Map<String, String> result = new HashMap<>();
    try {
      HttpResponse httpResponse = HttpGetUtil.doGet(host, path, headers, query);
      StatusLine statusLine = httpResponse.getStatusLine();
//          状态码: 200 正常；400 URL无效；401 appCode错误； 403 次数用完； 500 API网管错误
      int statusCode = statusLine.getStatusCode();
      if (HttpStatus.SC_OK == statusCode) {
        JSONObject jsonObject = HttpGetUtil.getJson(httpResponse);
        String status = jsonObject.get("status").toString();
        String msg = jsonObject.get("msg").toString();
        if ("01".equals(status)) {
          result.put("status", "success");
        } else if ("202".equals(status)) {
          result.put("status", "fail");
          result.put("msg", "二要素验证失败[库无]：" + msg);
        } else {
          result.put("status", "fail");
          result.put("msg", "二要素验证失败:" + msg);
        }
      } else {
        result.put("status", "fail");
        result.put("msg", "二要素验证接口响应:" + statusCode);
      }

    } catch (Exception e) {

      result.put("status", "fail");
      result.put("msg", "二要素验证接口异常");
    }

    return result;
  }
}
