package com.boge.hotels.datasource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.boge.hotels.jdbc.JdbcHelper;
import com.boge.hotels.pojo.HotelInfo;

public class Downloader {

	private final static String url = "http://120.76.205.241:8000/hotel/ctrip?cityid=25&checkInDate=2018-07-28&checkOutDate=2018-07-30&kw=%E6%B9%96%E9%87%8C%E5%8C%BA&apikey=LwpMQ1WFEDx1f5Bhwg2tMs2oF6VIi56OTa2FVmDxpR4dCBkXgPkFO6QxwSrs7T7h&pageToken=";
	
	public static void main(String[] args){
		int index = 1;
		int res;
		while (true) {
			res = run(index);
			if(res == 1){
				index++;
				continue;
			}
			if(res == 3){
				System.out.println("页数:"+index+"请求数据失败，重试。。。");
				continue;
			}
			System.out.println("所有数据都已经请求完毕!");
			break;
		}
	}
	
	public static void DownLoadData(int index){
		
		run(index);
		
	}
	
	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
		sb.append((char) cp);
		}
		return sb.toString();
	}
	
	public static JSONObject getRequestFromUrl(String url) throws Exception {
		URL realUrl = new URL(url);
		URLConnection conn = realUrl.openConnection();
		InputStream instream = conn.getInputStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(instream, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = JSONObject.parseObject(jsonText);
			return json;
		} finally {
			instream.close();
		}
	}
	

	public static int run(int index) {
		// TODO Auto-generated method stub
		int res = 1;
		try {
			String requestUrl = url+index;
			System.out.println("开始请求数据,页数为:"+index);
			JSONObject oldJsonObject = getRequestFromUrl(requestUrl);
			JSONArray jsonArray = oldJsonObject.getJSONArray("data");
			List<HotelInfo> list = new ArrayList<HotelInfo>();
			JSONObject jsonObject = new JSONObject();
			for(int i=0;i<jsonArray.size();i++){
				HotelInfo hotelInfo = new HotelInfo();
				jsonObject = jsonArray.getJSONObject(i);
				hotelInfo.setPosition(jsonObject.getString("address"));
				hotelInfo.setTitle(jsonObject.getString("title"));
				hotelInfo.setPrice(jsonObject.getString("price"));
				hotelInfo.setRating(jsonObject.getString("rating"));
				hotelInfo.setProps(jsonObject.getString("pros"));
				hotelInfo.setLon(jsonObject.getJSONObject("geoPoint").getString("lon"));
				hotelInfo.setLat(jsonObject.getJSONObject("geoPoint").getString("lat"));
				list.add(hotelInfo);
			}
			JdbcHelper.batchInsert(list);
			if(oldJsonObject != null){
				if(!oldJsonObject.getBooleanValue("hasNext")){
					res = 2;
				}
			}
			return res;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return 3;
		}
		
	}
	
}
