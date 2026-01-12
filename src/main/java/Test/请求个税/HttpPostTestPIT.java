//package Test.请求个税;
//
//import com.alibaba.fastjson.JSON;
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.io.UnsupportedEncodingException;
//import java.net.HttpURLConnection;
//import java.net.URI;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.nio.charset.StandardCharsets;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//import com.alibaba.fastjson2.JSON;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//public class HttpPostTestPIT {
//
//    public static void main(String[] args) {
//        // 目标接口地址
//
//        // 试算--单笔
//        String singleUrl="http://192.168.1.17:3780/pit/personal/income/tax/taxCalculationTry/single";
//
//        // 试算--多笔
//        String multipleUrl="http://192.168.1.17:3780/pit/personal/income/tax/taxCalculationTry/multiple";
//
//        // 个税实时计算
//        String realTimeUrl="http://192.168.1.17:3780/pit/personal/income/tax/taxCalculationTry/realTime";
//
//        // 支付结果
//        String resultUrl="http://192.168.1.17:3780/pit/personal/income/tax/taxCalculationTry/result";
//
//
//
//          String url=realTimeUrl;
//
//        // 要发送的 JSON 数据
//          String jsonInput= getRealTimeParams();
//        try {
//            // 创建 URL 对象
//            URL targetUrl = URI.create(url).toURL();
//            HttpURLConnection connection = (HttpURLConnection) targetUrl.openConnection();
//
//            // 设置请求方法
//            connection.setRequestMethod("POST");
//
//            // 设置请求头
//            connection.setRequestProperty("Content-Type", "application/json; utf-8");
//            connection.setRequestProperty("Accept", "application/json");
//            connection.setDoOutput(true); // 允许输出
//
//            // 发送请求体
//            try (OutputStream os = connection.getOutputStream()) {
//                byte[] input = jsonInput.getBytes(StandardCharsets.UTF_8);
//                os.write(input, 0, input.length);
//            }
//
//            // 读取响应
//            int responseCode = connection.getResponseCode();
//            System.out.println("响应状态码: " + responseCode);
//
//            StringBuilder response = new StringBuilder();
//            try (BufferedReader br = new BufferedReader(
//                    new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) {
//                String responseLine;
//                while ((responseLine = br.readLine()) != null) {
//                    response.append(responseLine).append("\n");
//                }
//            }
//
//            // 打印响应内容
//            System.out.println("响应内容:");
//            System.out.println(response.toString());
//
//            // 关闭连接
//            connection.disconnect();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public static String getSingleParams() {
//
//        // 参数集合
//        Map<String, Object> params = new HashMap<>();
//
//        params.put("cardId", "452723199005092813");
//        params.put("orderNo", "A100096");
//        params.put("statisticMonth", "2025-08");
//        params.put("taxBeforeAmount", "10000");
//        params.put("taxFilingEntityName", "28617测试服务公司001");
//        params.put("taxFilingEntityNumber", "910106322018070725");
//        params.put("userName", "吴福进");
//
//        String json = JSON.toJSONString(params);
//
//        System.out.println(" getSingleParams JSON: " + json);
//
//        return json;
//    }
//
//
//    public static String getMultipleParams() {
//
//        List<Map<String, Object>> dataList = new ArrayList<>();
//        // 参数集合
//        Map<String, Object> params = new HashMap<>();
//
//        params.put("cardId", "452723199005092813");
//        params.put("orderNo", "A100096");
//        params.put("statisticMonth", "2025-08");
//        params.put("taxBeforeAmount", "10000");
//        params.put("taxFilingEntityName", "28617测试服务公司001");
//        params.put("taxFilingEntityNumber", "910106322018070725");
//        params.put("userName", "吴福进");
//
//        Map<String, Object> params2 = new HashMap<>();
//
//        params2.put("cardId", "452723199005092813");
//        params2.put("orderNo", "A100096");
//        params2.put("statisticMonth", "2025-08");
//        params2.put("taxBeforeAmount", "10000");
//        params2.put("taxFilingEntityName", "28617测试服务公司001");
//        params2.put("taxFilingEntityNumber", "910106322018070725");
//        params2.put("userName", "吴福进");
//
//        dataList.add(params);
//        dataList.add(params2);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String json = "";
//        try {
//            json = objectMapper.writeValueAsString(dataList);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(" getMultipleParams JSON: " + json);
//
//        return json;
//    }
//
//
//    public static String getRealTimeParams() {
//
//        // 参数集合
//        Map<String, Object> params = new HashMap<>();
//
//        params.put("orderNo", "A100096");//订单号
//        params.put("statisticMonth", "2025-08");//统计月份
//        params.put("userName", "吴福进");//用户姓名
//        params.put("cardId", "452723199005092813"); //身份证号码
//        params.put("taxBeforeAmount", "10000");// 税前金额
//        params.put("taxFilingEntityNumber", "910106322018070725");// 报税主体编号
//        params.put("taxFilingEntityName", "28617测试服务公司001");// 报税主体名称
//        params.put("calculatedAmount", "90.00");// 试算金额
//        params.put("uniformity", "1");// 是否效验一致性 1:校验 不一致会失败 2:不校验
//
//        String json = JSON.toJSONString(params);
//
//        System.out.println(" getRealTimeParams JSON: " + json);
//
//        return json;
//    }
//
//
//    public static String getResultParams() {
//
//        // 参数集合
//        Map<String, Object> params = new HashMap<>();
//
//        params.put("orderNo", "A100096");//订单号
//        params.put("statisticMonth", "2025-08");//统计月份
//        params.put("userName", "吴福进");//用户姓名
//        params.put("cardId", "452723199005092813"); //身份证号码
//        params.put("taxBeforeAmount", "10000");// 税前金额
//        params.put("taxFilingEntityNumber", "910106322018070725");// 报税主体编号
//        params.put("status", "1");// 1:成功 2:失败
//
//        String json = JSON.toJSONString(params);
//
//        System.out.println(" getResultParams JSON: " + json);
//
//        return json;
//    }
//}