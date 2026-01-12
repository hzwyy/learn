package com.example.demo.OCR;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.OCR.bankcard.VerifyBankcardParams;
import com.example.demo.OCR.bankcard.VerifyBankcardResult;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * @author: YJY
 * @date: 2023/11/27 14:38 银行卡三/四要素
 * @description:
 */
public class UrlFor3CTest {

  public final static String BANKCARD3CALI_SUCCESS_CODE = "01";
  public final static int HTTP_CODE_200 = 200;
  public final static String urlFor3C = "/bank3CheckNew";
  public final static String urlFor4C = "/bankCheck4New";
  public static final String PROCESS = "process";
  public final static String appCode3C = "967b8ae4f53a4ff0b44541f501eb095c";
  public final static String bankCardURL = "https://bcard3and4.market.alicloudapi.com";

  public static VerifyBankcardResult getResult(VerifyBankcardParams actionParams, String url3Or4) {

    VerifyBankcardResult attachment = new VerifyBankcardResult();
    attachment.setCardStatus(false);
    attachment.setCardStatusMsg("未知错误,请联系管理员");
    try {
      //拼接请求链接
      String urlSend =
          bankCardURL + url3Or4 + "?idCard=" + actionParams.getCertificateNo() + "&name="
              + URLEncoder
              .encode(actionParams.getName(), "UTF-8") + "&accountNo=" + actionParams
              .getBankCardNo() + "&mobile=" + actionParams.getMobileNo();
      System.out.println("请求三/四要素验证url:" + urlSend);
      URL url = new URL(urlSend);
      HttpURLConnection httpURLCon = (HttpURLConnection) url.openConnection();
      // 格式Authorization:APPCODE (中间是英文空格)
      httpURLCon.setRequestProperty("Authorization", "APPCODE " + appCode3C);
      int httpCode = httpURLCon.getResponseCode();
      if (httpCode == HTTP_CODE_200) {
        String readContent = read(httpURLCon.getInputStream());
        System.out.println("用户银行卡三要素验证(阿里云):银行卡效验结果" + readContent);
        JSONObject json = JSONObject.parseObject(readContent);
        if (BANKCARD3CALI_SUCCESS_CODE.equals(json.getString("status"))) {
          attachment.setCardStatus(true);
          attachment.setCardStatusMsg("成功");
          attachment.setBank(json.getString("bank"));
        } else {
          attachment.setCardStatus(false);
          attachment.setCardStatusMsg(json.getString("msg"));
        }
      } else {
        Map<String, List<String>> map = httpURLCon.getHeaderFields();
        String error = map.get("X-Ca-Error-Message").get(0);
        System.out.println("请求阿里三/四要素失败:" + error);
        attachment.setCardStatus(false);
        attachment.setCardStatusMsg("网络异常,请稍后重试");
      }
    } catch (Exception e) {
      System.out.println("请求阿里三/四要素失败:" + e);
      attachment.setCardStatus(false);
      attachment.setCardStatusMsg("网络异常,请稍后重试");
    }

    return attachment;
  }

  /**
   * 读取返回结果
   */
  private static String read(InputStream is) throws IOException {
    StringBuffer sb = new StringBuffer();
    BufferedReader br = new BufferedReader(new InputStreamReader(is));
    String line = null;
    while ((line = br.readLine()) != null) {
      line = new String(line.getBytes(), "utf-8");
      sb.append(line);
    }
    br.close();
    return sb.toString();
  }

  public static void main(String[] args) {
    VerifyBankcardParams actionParams = new VerifyBankcardParams();
    actionParams.setCertificateNo("410224200311170379");
    actionParams.setBankCardNo("6213471031007682956");
    actionParams.setName("李硕");
//    actionParams.setMobileNo("15275720910");
    VerifyBankcardResult result = getResult(actionParams,urlFor3C);
    System.out.println(result);
  }
}
