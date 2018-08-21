package com.boge.utils;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

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

public class HttpTest {

	private static AtomicLong totalTime = new AtomicLong(0);
	
	public static void main(String[] args) throws InterruptedException {
		//test("3");
		//test("2");
		//singalTest("");
		test("");
		
		//singalTest("2");
	}
	
	
	private static void singalTest(String type) throws InterruptedException {
		int times = 1;
		Long start = System.currentTimeMillis();
		for(int i=0;i<times;i++) {
			httpPostWithJson("http://uaremedia.com/bus-webapi/rest/service/strategy",type);
		}
		Long end = System.currentTimeMillis();
		System.out.println("总耗时:"+(end-start)+",平均:"+(end-start)/times);
	}
	
	private static void test(String type) throws InterruptedException {
		totalTime = new AtomicLong(0);
		int times = 100;
		int threadNum = times/20;
		CountDownLatch countDownLatch = new CountDownLatch(threadNum);
		ExecutorService executorService = Executors.newCachedThreadPool();
		TTest test = new TTest(countDownLatch, type);
		for(int i=0;i<threadNum;i++) {
			executorService.execute(test);
		}
		countDownLatch.await();
		executorService.shutdown();
		System.out.println("总耗时:"+totalTime.get()+",平均:"+(totalTime.get())/times);
	}
	
	private static class TTest implements Runnable{

		private String type;
		private CountDownLatch countDownLatch;
		
		public TTest(CountDownLatch countDownLatch,String type) {
			this.type = type;
			this.countDownLatch = countDownLatch;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Long start = System.currentTimeMillis();
			for(int i=0;i<20;i++) {
				httpPostWithJson("http://uaremedia.com/bus-webapi/rest/service/strategy",type);
			}
			Long end = System.currentTimeMillis();
			totalTime.addAndGet(end-start);
			countDownLatch.countDown();
		}
		
	}
	
	public static boolean httpPostWithJson(String url,String type){
	    boolean isSuccess = false;
	    
	    HttpPost post = null;
	    try {
	        HttpClient httpClient = new DefaultHttpClient();

	        // 设置超时时间
	        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 200000);
	        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, 200000);
	            
	        post = new HttpPost(url);
	        // 构造消息头
	        post.setHeader("phone_model","A37fw");
	        post.setHeader("imei","435465676544");
	        post.setHeader("is_system","10");
	        post.setHeader("vendor","OPPO");
	        post.setHeader("resolution","720*1280");
	        post.setHeader("imsi","null");
	        post.setHeader("country","CN");
	        post.setHeader("serial","1d41e4df");
	        post.setHeader("ad_place_id","null");
	        post.setHeader("ad_type","null");
	        post.setHeader("android_id","73eda247660a718c");
	        post.setHeader("android_version","5.1.1");
	        post.setHeader("board","msm8916");
	        post.setHeader("brand","OPPO");
	        post.setHeader("build_num","A37fwEX_11_171008");
	        post.setHeader("google_account","280819578@qq.com");
	        post.setHeader("has_brower","null");
	        post.setHeader("idfa","null");
	        post.setHeader("imsi2","null");
	        post.setHeader("ip","180.169.135.83");
	        post.setHeader("is_sdk","0");
	        post.setHeader("is_tcard_insert","null");
	        post.setHeader("latitude","null");
	        post.setHeader("locale","en");
	        post.setHeader("longitude","null");
	        post.setHeader("mac","1c,dd,ea,c6,72,c8");
	        post.setHeader("media_type","3");
	        post.setHeader("network","Wifi");
	        post.setHeader("network_type","null");
	        post.setHeader("oid","null");
	        post.setHeader("os","20");
	        post.setHeader("os_type","21");
	        post.setHeader("pay_app_id","null");
	        post.setHeader("pay_channel","null");
	        post.setHeader("phone_num","null");
	        post.setHeader("project_model","null");
	        post.setHeader("province","310000");
	        post.setHeader("pub_key","test-adp");
	        post.setHeader("smsc","null");
	        post.setHeader("ua","null");
	        post.setHeader("user_attr","null");
	        post.setHeader("user_id","866345030838256");
	        post.setHeader("user_media_id","test-adp_866345030838256");
	        post.setHeader("version_code","1");
	        post.setHeader("version_name","1.0");
	        post.setHeader("connection","keep-alive");
	        post.setHeader("content-type","application/x-www-form-urlencoded; charset=UTF-8");
	        post.setHeader("host","dominoppo.com");
	        post.setHeader("is_test","true");
	                    
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
	           /* HttpEntity stringEntity = response.getEntity();
	            String content = EntityUtils.toString(stringEntity);
	            System.out.println(content);*/
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
