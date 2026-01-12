package com.example.demo.OCR;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.AuthSchemes;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

/**
 * @author chonglulu
 */
public class HttpGetUtil {
	
	/**
	 * get
	 */
	public static HttpResponse doGet(String host, String path, Map<String, String> headers, Map<String, String> querys)
            throws Exception {    	
    	HttpClient httpClient = wrapClient(host);

    	HttpGet request = new HttpGet(buildUrl(host, path, querys));
        for (Map.Entry<String, String> e : headers.entrySet()) {
        	request.addHeader(e.getKey(), e.getValue());
        }

        return httpClient.execute(request);
    }
	
	private static String buildUrl(String host, String path, Map<String, String> querys) throws UnsupportedEncodingException {
    	StringBuilder sbUrl = new StringBuilder();
    	sbUrl.append(host);
    	if (!isBlank(path)) {
    		sbUrl.append(path);
        }
    	if (null != querys) {
    		StringBuilder sbQuery = new StringBuilder();
        	for (Map.Entry<String, String> query : querys.entrySet()) {
        		if (0 < sbQuery.length()) {
        			sbQuery.append("&");
        		}
        		if (isBlank(query.getKey()) && !isBlank(query.getValue())) {
        			sbQuery.append(query.getValue());
                }
        		if (!isBlank(query.getKey())) {
        			sbQuery.append(query.getKey());
        			if (!isBlank(query.getValue())) {
        				sbQuery.append("=");
        				sbQuery.append(URLEncoder.encode(query.getValue(), "utf-8"));
        			}        			
                }
        	}
        	if (0 < sbQuery.length()) {
        		sbUrl.append("?").append(sbQuery);
        	}
        }
    	
    	return sbUrl.toString();
    }
	
	private static HttpClient wrapClient(String host) {
		HttpClient httpClient = HttpClientBuilder.create().build();
		String https = "https://";
		if (host.startsWith(https)) {
            return sslClient();
        }
		return httpClient;
	}

    /**
     * 在调用SSL之前需要重写验证方法，取消检测SSL
     * 创建ConnectionManager，添加Connection配置信息
     * @return HttpClient 支持https
     */
    private static HttpClient sslClient() {
        try {
            // 在调用SSL之前需要重写验证方法，取消检测SSL
            X509TrustManager trustManager = new X509TrustManager() {
                @Override public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                @Override public void checkClientTrusted(X509Certificate[] xcs, String str) {}
                @Override public void checkServerTrusted(X509Certificate[] xcs, String str) {}
            };
            SSLContext ctx = SSLContext.getInstance(SSLConnectionSocketFactory.TLS);
            ctx.init(null, new TrustManager[] { trustManager }, null);
            SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(ctx, NoopHostnameVerifier.INSTANCE);
            // 创建Registry
            RequestConfig requestConfig = RequestConfig.custom().setCookieSpec(CookieSpecs.STANDARD_STRICT)
                    .setExpectContinueEnabled(Boolean.TRUE).setTargetPreferredAuthSchemes(Arrays.asList(
                    AuthSchemes.NTLM, AuthSchemes.DIGEST))
                    .setProxyPreferredAuthSchemes(Collections.singletonList(AuthSchemes.BASIC)).build();
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https",socketFactory).build();
            // 创建ConnectionManager，添加Connection配置信息
            PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            return HttpClients.custom().setConnectionManager(connectionManager)
                    .setDefaultRequestConfig(requestConfig).build();
        } catch (KeyManagementException | NoSuchAlgorithmException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * 将结果转换成JSONObject
     */
    public static JSONObject getJson(HttpResponse httpResponse) throws IOException {
        HttpEntity entity = httpResponse.getEntity();
        String resp = EntityUtils.toString(entity, "UTF-8");
        EntityUtils.consume(entity);
        return JSON.parseObject(resp);
    }



    private static boolean isBlank(String arg){
        return (arg == null) || ("".equals(arg.trim()) || "null".equals(arg.trim()) || "NULL".equals(arg.trim()));
    }
}