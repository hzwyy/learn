package com.example.demo.OCR;

import com.baidu.aip.ocr.AipOcr;
import com.baidu.aip.util.Util;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.ObjectUtils;
import org.json.JSONObject;

/**
 * @author linsong
 * @date 2019/6/12
 */
public class OCRUtil {

  /**
   * 设置APPID/AK/SK
   */
  private static final String APP_ID = "16895381";
  private static final String API_KEY = "YOS8NeW4xRutBPjcaYrBB6Zw";
  private static final String SECRET_KEY = "ZnAt76822VBEzoeFWgN3wN0vTdrhFMiG";
  public static final String IDCARD_SIDE_FRONT = "front";
  public static final String IDCARD_SIDE_BACK = "back";


  public static Map<String, Object> getIdCardResult(byte[] image, String idCardSide) {
    Map<String, Object> resultMap = new HashMap<>(4);
    if (image == null) {
      resultMap.put("error_msg", "图片不存在");
      return resultMap;
    }
    if (image.length == 0) {
      resultMap.put("error_msg", "图片解析失败");
      return resultMap;
    }
    AipOcr client = new AipOcr(APP_ID, API_KEY, SECRET_KEY);
    HashMap<String, String> options = new HashMap<>();
    options.put("detect_direction", "true");
    options.put("accuracy", "high");

    JSONObject res = client.idcard(image, idCardSide, options);

    if (res.has("error_code")) {
      resultMap = res.toMap();
      return resultMap;
    }

    if (res.get("words_result_num") != null) {
      Integer wordsResultNum = (Integer) res.get("words_result_num");
      //识别结果数,表示words_result的元素个数
      if (wordsResultNum == null || wordsResultNum < 3) {
//            resultMap.put("error_code", "");
        resultMap.put("error_msg", "请提供正确的身份证照片");
        return resultMap;
      }
    } else {
      resultMap.put("error_msg", "请提供正确的身份证照片");
      return resultMap;
    }

    JSONObject wordsResult = res.getJSONObject("words_result");
    for (String key : wordsResult.keySet()) {
      JSONObject result = wordsResult.getJSONObject(key);
      String info = result.getString("words");
      switch (key) {
        case "姓名":
          resultMap.put("name", info);
          break;
        case "性别":
          resultMap.put("sex", info);
          break;
        case "民族":
          resultMap.put("nation", info);
          break;
        case "出生":
          resultMap.put("birthday", info);
          break;
        case "住址":
          resultMap.put("address", info);
          break;
        case "公民身份号码":
          resultMap.put("idNumber", info);
          break;
        case "签发机关":
          resultMap.put("issuedOrganization", info);
          break;
        case "签发日期":
          resultMap.put("issuedAt", info);
          break;
        case "失效日期":
          resultMap.put("expiredAt", info);
          break;
        default:
      }
    }
    return resultMap;
  }


//  public static void main(String[] args) {
//
//  }

  public static void main(String[] args) throws IOException {
    HashMap front = new HashMap();

    HashMap back = new HashMap();
    String url = "G:\\image";
    File dir = new File(url);
    if (!dir.isFile()) {
      File[] files = dir.listFiles();
      if (files != null) {
        for (File f : files) {
          if (f.isFile()) {
            String name = f.getName();
            if(name.contains("反面")){
              String backName = name.substring(0,name.indexOf("反面"));
//              if(name.endsWith("jpg") || name.endsWith("JPG")){
//                backName +=".jpg";
//              }
//              if(name.endsWith("png") || name.endsWith("PNG")){
//                backName +=".png";
//              }
              back.put(name,backName);
            }else {
              Map<String, Object> map =  getIdCardResult(convertFileToByteArray(f),IDCARD_SIDE_FRONT);
              if(ObjectUtils.isNotEmpty(map.get("name")) && ObjectUtils.isNotEmpty(map.get("idNumber")) ){
                String fileName = map.get("name")+"_"+map.get("idNumber")+"_front.jpg";
                String fontName = name.substring(0,name.indexOf(".jpg"));
                front.put(fontName,fileName);
                String newPath = url +"\\"+fileName;
                File newFile = new File(newPath);
                boolean status = f.renameTo(newFile);
                System.out.println("正面重命名状态:"+ status);
              }else {

                System.out.println(name+"识别失败!!!!!!!!");
              }
              System.out.println(map.get("name"));
              System.out.println(map.get("idNumber"));


            }
          }
        }
      }
    }
    //修改背面文件
    for (Object backName : back.keySet()){
      System.out.println("反面存储的值:"+back.get(backName));
      Object frontName = front.get(back.get(backName));
      if(ObjectUtils.isEmpty(frontName)){
        System.out.println(backName+"找不到这个人");
        continue;
      }
      String newBackName =  frontName.toString().substring(0,frontName.toString().length() - 9)+"back.jpg";

      String oldPath = url +"\\"+backName;
      File f = new File(oldPath);
      String newPath = url +"\\"+newBackName;
      File newFile = new File(newPath);
      boolean status = f.renameTo(newFile);
      System.out.println("背面重命名状态:"+ status);
    }


  }


  public static byte[] convertFileToByteArray(File file) {
    try {
      FileInputStream fis = new FileInputStream(file);
      byte[] byteArray = new byte[(int) file.length()];
      fis.read(byteArray);
      fis.close();
      return byteArray;
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }


}
