package com.boge.hotels.datasource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.boge.hotels.jdbc.JdbcHelper;
import com.boge.hotels.pojo.HotelInfo;

public class Calculater {

	private final static String busUrl = "http://api.map.baidu.com/direction/v2/transit?origin={origin}&destination={destination}&ak=40FsapyhTFmFGsWkzutz4GmSDkQxXYSN";
	
	private final static String taxiUrl = "http://api.map.baidu.com/direction/v2/driving?origin={origin}&destination={destination}&ak=40FsapyhTFmFGsWkzutz4GmSDkQxXYSN";

	private final static String airport_des = "24.540399,118.13862";
	
	private final static String hotel_des = "24.528524,118.204071";
	
	public static void main(String[] args) throws Exception{
		//查询所有数据
		List<HotelInfo> list = (List<HotelInfo>) JdbcHelper.queryList();
		//list = list.subList(0,1);
		String origin = "";
		String commonUrl="";
		String requestUrl = "";
		List<Rout> routs;
		Rout rout;
		JSONObject jsonObject = new JSONObject();
		int i=0;
		for(HotelInfo h:list){
			System.out.println("请求第"+(++i)+"条数据！");
			origin = h.getLat()+","+h.getLon();
			
			//airport-bus
			commonUrl = busUrl.replace("{origin}",origin);
			requestUrl = commonUrl.replace("{destination}",airport_des);
			jsonObject = getRequestFromUrl(requestUrl);
			routs =  JSONObject.parseArray(jsonObject.getJSONObject("result").getJSONArray("routes").toJSONString(),Rout.class);
			rout = routs.stream().sorted().findFirst().get();
			h.setAirportBusDistance(rout.getDistance());
			h.setAirportBusTime(rout.getDuration());
			

			//hotel-bus
			requestUrl = commonUrl.replace("{destination}",hotel_des);
			jsonObject = getRequestFromUrl(requestUrl);
			routs =  JSONObject.parseArray(jsonObject.getJSONObject("result").getJSONArray("routes").toJSONString(),Rout.class);
			rout = routs.stream().sorted().findFirst().get();
			h.setHotelBusDistance(rout.getDistance());
			h.setHotelBusTime(rout.getDuration());
			
			//airport-taxi
			commonUrl = taxiUrl.replace("{origin}",origin);
			requestUrl = commonUrl.replace("{destination}",airport_des);
			jsonObject = getRequestFromUrl(requestUrl);
			routs =  JSONObject.parseArray(jsonObject.getJSONObject("result").getJSONArray("routes").toJSONString(),Rout.class);
			rout = routs.stream().sorted().findFirst().get();
			h.setAirportTaxiDistance(rout.getDistance());
			h.setAirportTaxiTime(rout.getDuration());
			

			//hotel-taxi
			requestUrl = commonUrl.replace("{destination}",hotel_des);
			jsonObject = getRequestFromUrl(requestUrl);
			routs =  JSONObject.parseArray(jsonObject.getJSONObject("result").getJSONArray("routes").toJSONString(),Rout.class);
			rout = routs.stream().sorted().findFirst().get();
			h.setHotelTaxiDistance(rout.getDistance());
			h.setHotelTaxiTime(rout.getDuration());
			
			//System.out.println(h);
		}
		JdbcHelper.batchUpdate(list);
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
	
	private static class Rout implements Comparable<Rout>{
		
		public Rout(){
			
		}
		
		private String distance;
		
		private String duration;

		public String getDistance() {
			return distance;
		}

		public void setDistance(String distance) {
			this.distance = distance;
		}

		public String getDuration() {
			return duration;
		}

		public void setDuration(String duration) {
			this.duration = duration;
		}

		@Override
		public int compareTo(Rout o) {
			// TODO Auto-generated method stub
			return Integer.valueOf(duration) - Integer.valueOf(o.duration);
		}
		
		
		
	}
	
}
