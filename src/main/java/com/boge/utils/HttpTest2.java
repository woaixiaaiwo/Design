package com.boge.utils;

import java.util.List;
import java.nio.charset.Charset;
import java.util.ArrayList;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class HttpTest2 {

	public static void main(String[] args) {
		test("");
		test("3");
		test("2");
		test("1");
	}
	
	private static void test(String type) {
		Long start = System.currentTimeMillis();
		for(int i=0;i<50;i++) {
			httpPostWithJson("http://127.0.0.1:8082/bus-webapi/rest/service/strategy",type);
		}
		Long end = System.currentTimeMillis();
		System.out.println("耗时:"+(end-start));
	}
	
	public static boolean httpPostWithJson(String url,String type){
	    boolean isSuccess = false;
	    
	    HttpPost post = null;
	    try {
	        HttpClient httpClient = new DefaultHttpClient();

	        // 设置超时时间
	        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 2000);
	        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 2000);
	            
	        post = new HttpPost(url);
	        // 构造消息头
	        post.setHeader("Accept","application/json, text/javascript, */*; q=0.01");
	        post.setHeader("Accept-Encoding","gzip, deflate, br");
	        post.setHeader("Accept-Language","zh-CN,zh;q=0.9");
	        post.setHeader("ad_place_id",null);
	        post.setHeader("ad_type",null);
	        post.setHeader("android_id","d2a8739f6ca060f0");
	        post.setHeader("android_version","7.0");
	        post.setHeader("board","zsl865_an");
	        post.setHeader("brand","CMCC");
	        post.setHeader("build_num","zsl865_an-userdebug 7.0 NRD90M eng.moppo.20180401.");
	        post.setHeader("Cache-Control","no-cache");
	        post.setHeader("Connection","keep-alive");
	        post.setHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
	        post.setHeader("country","");
	        post.setHeader("google_account",null);
	        post.setHeader("has_brower",null);
	        post.setHeader("Host","127.0.0.1:8082");
	        post.setHeader("idfa",null);
	        post.setHeader("imei","864159031119483");
	        post.setHeader("imsi",null);
	        post.setHeader("imsi2",null);
	        post.setHeader("ip","192.168.1.56");
	        post.setHeader("is_sdk","0");
	        post.setHeader("is_system","22");
	        post.setHeader("is_tcard_insert",null);
	        post.setHeader("is_test","true");
	        post.setHeader("latitude",null);
	        post.setHeader("locale","en-US");
	        post.setHeader("longitude",null);
	        post.setHeader("mac",null);
	        post.setHeader("media_type","6");
	        post.setHeader("network","wifi");
	        post.setHeader("network_type","0");
	        post.setHeader("oid",null);
	        post.setHeader("Origin","http//127.0.0.1:8082");
	        post.setHeader("os","20");
	        post.setHeader("os_type","21");
	        post.setHeader("pay_app_id",null);
	        post.setHeader("pay_channel",null);
	        post.setHeader("phone_model","CMCC A3");
	        post.setHeader("phone_num",null);
	        post.setHeader("plmn","");
	        post.setHeader("Pragma","no-cache");
	        post.setHeader("project_model",null);
	        post.setHeader("province","unknown");
	        post.setHeader("pub_key","gp_test");
	        post.setHeader("Referer","http://127.0.0.1:8082/domino-webapp/adminIndex");
	        post.setHeader("resolution","720 * 1280");
	        post.setHeader("serial","HB6CE1RPL002WW");
	        post.setHeader("smsc",null);
	        post.setHeader("ua",null);
	        post.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/64.0.3282.186 Safari/537.36");
	        post.setHeader("user_attr",null);
	        post.setHeader("user_id","864159031119483");
	        post.setHeader("user_media_id","gp_test_864159031119483");
	        post.setHeader("vendor","CMCC");
	        post.setHeader("version_code","300");
	        post.setHeader("version_name","3.0.0");
	        post.setHeader("X-Requested-With","XMLHttpRequest");
	                    
	        NameValuePair nameValuePair = new BasicNameValuePair("r_type",type);
	        List<NameValuePair> list = new ArrayList<>();
	        list.add(nameValuePair);
	        // 构建消息实体
	        StringEntity entity = new UrlEncodedFormEntity(list,Charset.forName("UTF-8"));
	        entity.setContentEncoding("UTF-8");
	        // 发送Json格式的数据请求
	        entity.setContentType("application/x-www-form-urlencoded");
	        post.setEntity(entity);
	            
	        HttpResponse response = httpClient.execute(post);
	            
	        // 检验返回码
	        int statusCode = response.getStatusLine().getStatusCode();
	        if(statusCode != HttpStatus.SC_OK){
	            System.out.println("请求出错: "+statusCode);
	            isSuccess = false;
	        }else{
	            // 返回码中包含retCode及会话Id
	            HttpEntity stringEntity = response.getEntity();
	            String content = EntityUtils.toString(stringEntity);
	            //System.out.println(content);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        isSuccess = false;
	    }finally{
	        if(post != null){
	            try {
	                post.releaseConnection();
	                Thread.sleep(500);
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return isSuccess;
	}
	
}
