package com.example.demo.sign;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @date: 2021/6/15 9:51
 * @description: 签名小示例
 */
public class SignTest {

  /*
    {
        "event" : "payment.refund",
        "vendor" : "6304b13a6dc9894e598b4324",
        "merchant" : "624e8be4958487384efb09c5",
        "agency" : "63119f57f7715f6f0f0cf23c",
        "order" : "F7238480P",
        "reason" : "II,III类账户超过日限额：中国银行",
        "nonce" : "7CbUKi0qgIKBsbkJSQKr",
        "timestamp" : 1665744331257.0,
        "sign" : "3EWdphhTADEVojUgxDs5xAghpEyaIo/PlKZx3wGktb2vYQNMVVFqbGlT0G8sdC/f9QME4Nhi3vL0w5Vrp+jTm1Y2TgQPAaoEeg+d0QT0bt0rNg64bD6WWrPl48IgcW4mOrSAGhAeOz58SmopGXGzWZw+ZZXPOBb3sEFX3bsFHen4TMpxGCwMGIjCTsPQiOD07/S5ZWCxZp7RqLTdX3pczTZiKCo6RpCxWqNcJMojc4EWF755ApPG3N6T8dC2bpwtNVi3ZBIcZjwfpfOTo+sYaPbbQKldyQqDlKu098vCEc5bFSX74g2slrAFxZV3/VqawoHK97aF/lhmoYBrBoWv0w=="
    }
   **/

  public static void main(String[] args) {

    //API对接配置 --商户签名密钥
    String signGenerationKey = "cb8377b02eff4ca39a08dcd6c13c4eaa";

    /**
     * @Description 具体参数封装 这里以增加收款者为例
     **/
    Map<String, Object> retData = new HashMap();
    retData.put("cooperatorId", "pI92yBzo15gZn693480i");
    retData.put("orderNo", "122345676543123126");
    retData.put("cooperatorName", "合作者名称");
    retData.put("payerName", "河南宝岭信息科技有限公司");
    retData.put("payerAccount", "15162100380036");
    retData.put("payerRemark", "备注");
    retData.put("paymentAmount", "123.12");
    retData.put("payeeName", "河南宝岭信息科技有限公司");
    retData.put("payeeAccount", "30007317000000");
    retData.put("completeTime", "20231102145625");
    retData.put("mainAccount", "371909041110601");
    retData.put("mainAccountName", "河南宝岭信息科技有限公司");
    retData.put("rechargeStatus", "1");
      String retSign = generateSign(retData, signGenerationKey);

    System.out.println("生成的签名为:" + retSign);
    //放入签名后 进行接口请求即可
    retData.put("sign", retSign);
  }

  public static String generateSign(Map<String, Object> mapData,
      String signGenerationKey) {
    try {
      return generateSign(getSortKVStr(mapData), signGenerationKey);
    } catch (Exception e) {
      System.out.println(e);
      return null;
    }
  }


  public static String generateSign(String sortKVStr, String signGenerationKey) throws Exception {
    StringBuilder sb = new StringBuilder(sortKVStr);
    sb.append("&key=").append(signGenerationKey);
    MessageDigest digest = MessageDigest.getInstance("SHA-256");
    byte[] signBytes = digest.digest(sb.toString().getBytes(Charset.forName("utf-8")));
    return Base64.getEncoder().encodeToString(signBytes);
  }

  private static String getSortKVStr(Map<String, Object> values) {

    List<String> keys = new ArrayList<String>();
    keys.addAll(values.keySet());

    Collections.sort(keys);

    StringBuffer sb = new StringBuffer();
    int size = keys.size();
    for (int i = 0; i < size; i++) {
      String key = keys.get(i);
      Object value = values.get(key);

      if (value == null) {
        continue;
      }

      if (value instanceof String && "".equals(value)) {
        continue;
      }

      if (sb.length() > 0) {
        sb.append('&');
      }
      sb.append(key).append("=").append(value);
    }

    String sortKVStr = sb.toString();
    System.out.println("参数排序结果为:" + sortKVStr);
    return sortKVStr;
  }
}
