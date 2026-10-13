package com.example.demo.OCR.face.baiduai;

import com.alibaba.fastjson.JSON;
import com.baidu.aip.util.Base64Util;
import org.eclipse.jetty.util.StringUtil;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BaiduApiUtil {

	private static Logger logger = LoggerFactory.getLogger(BaiduApiUtil.class);

	//设置APPID/AK/SK
	private static final String APP_ID = "16895381";
	private static final String API_KEY = "YOS8NeW4xRutBPjcaYrBB6Zw";
	private static final String SECRET_KEY = "ZnAt76822VBEzoeFWgN3wN0vTdrhFMiG";

	private static final String AIFACE_API_KEY = "SFJAnuLbD0pASf61zrlUEWLn";
	private static final String AIFACE_SECRET_KEY = "sbzr9kImmriMc4ysl4VZbjYbA0pgl8tZ";
	private static final String AIFACE_PLAN_ID = "25427";

//	private static final String AIFACE_API_KEY = "FtW9YILyxZ5kOsciXnmBKhbn";
//	private static final String AIFACE_SECRET_KEY = "RS8vUpkVfIr4wuRyQGqK6eRmhC5zFEqQ";
//	private static final String AIFACE_PLAN_ID = "24357";


	public static void main(String[] args) {
 	Map<String, String> verifyTokenMap = BaiduApiUtil.getFaceprintVerifyToken();
   String verifyToken = verifyTokenMap.get("verifyToken");
		System.out.println("tokentokentoken"+verifyToken);
   Map<String, String> result =	faceprintSubmitIdcard("宋海斌","130982199508280714",verifyToken);

	}

	/**
	 * 获取发票信息
	 * @return
	 */
	public static Map<String, String> getInvoice(byte[] imgData,String fileName) {
		Map<String, String> invoiceInfo = new HashMap<String, String>();
		String access_token = getAuth(API_KEY,SECRET_KEY);
		if(access_token!=null){
			try {
				String info = vatInvoiceByInputStream(access_token,imgData,fileName);
				JSONObject jsonObject = new JSONObject(info);
				if(jsonObject.isNull("error_code")){
					JSONObject invoiceResult = jsonObject.getJSONObject("words_result");
					invoiceInfo.put("code", "0000");
					invoiceInfo.put("invoiceCode", invoiceResult.getString("InvoiceCode"));
					invoiceInfo.put("invoiceNum", invoiceResult.getString("InvoiceNum"));
					invoiceInfo.put("invoiceAmount", invoiceResult.getString("AmountInFiguers"));
				}else{
					invoiceInfo.put("code", jsonObject.getString("error_code"));
					invoiceInfo.put("msg", jsonObject.getString("error_msg"));
				}
			} catch (JSONException e) {
				logger.error(e.getMessage(),e);
				invoiceInfo.put("code", "1002");
				invoiceInfo.put("msg", "获取发票信息异常");
			}
		}else{
			invoiceInfo.put("code", "1001");
			invoiceInfo.put("msg", "获取token信息失败");
		}
		return invoiceInfo;
	}

	public static String vatInvoice(String access_token,String filePath) {
		// 请求url
		String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/vat_invoice";
		try {
			// 本地文件路径
			byte[] imgData = FileUtil.readFileByBytes(filePath);
			String imgStr = Base64Util.encode(imgData);
			String imgParam = URLEncoder.encode(imgStr, "UTF-8");

			String param = "image=" + imgParam;

			// 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
			String accessToken = access_token;
			logger.info("获取发票信息请求地址:" + url);
			logger.info("获取发票信息请求参数:" + param);
			String result = HttpUtil.post(url, accessToken, param);
			logger.info("获取发票信息响应信息:" + result);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return null;
	}

	public static String vatInvoiceByInputStream(String access_token,byte[] imgData,String fileName) {
		// 请求url
		String url = "https://aip.baidubce.com/rest/2.0/ocr/v1/vat_invoice";
		try {
			// 本地文件路径
			String imgStr = Base64Util.encode(imgData);
			String imgParam = URLEncoder.encode(imgStr, "UTF-8");

			String param = "image=" + imgParam;
			if (fileName.contains(".pdf") || fileName.contains(".PDF")){
				param = "pdf_file=" + imgParam;
			}


			// 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
			String accessToken = access_token;
			logger.info("获取发票信息请求地址:" + url);
//			logger.info("获取发票信息请求参数:" + param);
			String result = HttpUtil.post(url, accessToken, param);
			logger.info("获取发票信息响应信息:" + result);
			return result;
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return null;
	}	

	public static String getAuth(String ak, String sk) {
		// 获取token地址
		String authHost = "https://aip.baidubce.com/oauth/2.0/token?";
		String getAccessTokenUrl = authHost
				// 1. grant_type为固定参数
				+ "grant_type=client_credentials"
				// 2. 官网获取的 API Key
				+ "&client_id=" + ak
				// 3. 官网获取的 Secret Key
				+ "&client_secret=" + sk;
		try {
			logger.info("获取token请求地址:" + getAccessTokenUrl);
			URL realUrl = new URL(getAccessTokenUrl);
			// 打开和URL之间的连接
			HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
			connection.setRequestMethod("GET");
			connection.connect();
			// 获取所有响应头字段
			Map<String, List<String>> map = connection.getHeaderFields();
			// 遍历所有的响应头字段
			for (String key : map.keySet()) {
				System.err.println(key + "--->" + map.get(key));
			}
			// 定义 BufferedReader输入流来读取URL的响应
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String result = "";
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
			/**
			 * 返回结果示例
			 */
			logger.info("result:" + result);
			JSONObject jsonObject = new JSONObject(result);
			String access_token = jsonObject.getString("access_token");
			return access_token;
		} catch (Exception e) {
			logger.info("获取token失败！");
		}
		return null;
	}

	public static Map<String, String> getFaceprintVerifyToken() {

		Map<String, String> resultMap = new HashMap<String, String>();

		// 请求url
		String url = "https://aip.baidubce.com/rpc/2.0/brain/solution/faceprint/verifyToken/generate";
		try {

			String access_token = getAuth(AIFACE_API_KEY, AIFACE_SECRET_KEY);
			if (access_token != null) {


				logger.info("获取verify_token请求地址:" + url);

				Map<String, String> reqMap = new HashMap<>();
				reqMap.put("plan_id", AIFACE_PLAN_ID);

				String param = JSON.toJSONString(reqMap);


				String result = HttpUtil.postJson(url, access_token, param);
				logger.info("获取verify_token响应信息:" + result);
				JSONObject jsonObject = new JSONObject(result);
				if (jsonObject.getBoolean("success")) {
					JSONObject invoiceResult = jsonObject.getJSONObject("result");
					resultMap.put("verifyToken", invoiceResult.getString("verify_token"));
				}
			} else {
				resultMap.put("code", "1001");
				resultMap.put("msg", "获取token信息失败");
			}

			return resultMap;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return resultMap;
		}
	}

	public static Map<String, String> faceprintSubmitIdcard(String name, String idCard,String faceprintVerifyToken) {

		Map<String, String> resultMap = new HashMap<String, String>();

		// 请求url

		String url = "https://aip.baidubce.com/rpc/2.0/brain/solution/faceprint/idcard/submit";
		try {

			String access_token = getAuth(AIFACE_API_KEY, AIFACE_SECRET_KEY);
			if (access_token != null) {

				Map<String, String> reqMap = new HashMap<>();
				reqMap.put("verify_token", faceprintVerifyToken);
				reqMap.put("id_name", name);
				reqMap.put("id_no", idCard);

				String param = JSON.toJSONString(reqMap);

				logger.info("指定用户信息上报接口请求地址:" + url);

				String result = HttpUtil.postJson(url, access_token, param);
				logger.info("指定用户信息上报接口响应信息:" + result);

				JSONObject jsonObject = new JSONObject(result);
				if (jsonObject.getBoolean("success")) {
					resultMap.put("success", String.valueOf(jsonObject.get("result")));
				}else {
					resultMap.put("msg", jsonObject.getString("message"));
				}

			} else {
				resultMap.put("code", "1001");
				resultMap.put("msg", "获取token信息失败");
			}

			return resultMap;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return resultMap;
		}
	}

	public static JSONObject faceVideoResult(String faceprintVerifyToken) {

		// 请求url
		JSONObject resultMap = new JSONObject();
		resultMap.put("success",false);
		String url = "https://aip.baidubce.com/rpc/2.0/brain/solution/faceprint/result/media/query";
		try {

			String access_token = getAuth(AIFACE_API_KEY, AIFACE_SECRET_KEY);
			if (access_token != null) {
				Map<String, String> reqMap = new HashMap<>();
				reqMap.put("verify_token", faceprintVerifyToken);

				String param = JSON.toJSONString(reqMap);
				String result = HttpUtil.postJson(url, access_token, param);
				logger.info("获取百度认证视频接口响应信息:" + result);
				return new JSONObject(result);
			}
			return resultMap;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return resultMap;
		}
	}



	public static Map<String, String> queryFaceprintResult(String verifyToken) {

		Map<String, String> resultMap = new HashMap<String, String>();

		// 请求url
		String url = "https://aip.baidubce.com/rpc/2.0/brain/solution/faceprint/result/simple";
		try {

			String access_token = getAuth(AIFACE_API_KEY, AIFACE_SECRET_KEY);
			if (access_token != null) {

				Map<String, String> reqMap = new HashMap<>();
				reqMap.put("verify_token", verifyToken);

				String param = JSON.toJSONString(reqMap);

				logger.info("获取认证人脸接口请求地址:" + url);

				String result = HttpUtil.postJson(url, access_token, param);
				logger.info("获取认证人脸接口响应信息:" + result);

				JSONObject jsonObject = new JSONObject(result);
				if (jsonObject.getBoolean("success")) {
					String imageUrl = jsonObject.getJSONObject("result").getString("image");
					if (!StringUtil.isEmpty(imageUrl)) {
						logger.info("百度认证识别成功,采集人脸信息链接:{}" + imageUrl);
						resultMap.put("success", "true");
						resultMap.put("msg", "人脸认证识别成功");
						resultMap.put("image", imageUrl);
					} else {
						resultMap.put("success", "false");
						resultMap.put("msg", "人脸认证识别失败:" +
								jsonObject.getJSONObject("result").has("real_message") + "," + jsonObject
								.getString("message"));
					}
				} else {
					if (!jsonObject.isNull("result") && jsonObject
							.getJSONObject("result").has("real_message")) {
						resultMap.put("success", "false");
						resultMap.put("msg", "人脸认证识别失败:" +
								jsonObject.getJSONObject("result").getString("real_message") + "," + jsonObject
								.getString("message"));
					} else {
						resultMap.put("success", "doing");
						resultMap.put("msg", jsonObject.getString("message"));
					}
				}

			} else {
				resultMap.put("success", "true");
				resultMap.put("msg", "获取token信息失败");
			}

			return resultMap;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return resultMap;
		}
	}

}
