package com.boge.algorithm;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class CopyOfTestHash {
	
	private final static int allowNumber = 100;

	public static void main(String[] args) {
		
		String[] resolutions = {"1024x768",
				"1080x1920",
				"1280x720",
				"1366x768",
				"1440x900",
				"1536x864",
				"1600x900",
				"1920x1080",
				"300x160",
				"316x682",
				"320x480",
				"320x568",
				"360x592",
				"360x598",
				"360x640",
				"360x720",
				"375x667",
				"377x459",
				"377x707",
				"381x246",
				"383x707",
				"393x699",
				"400x183",
				"400x336",
				"400x445",
				"400x522",
				"400x545",
				"400x553",
				"400x571",
				"400x677",
				"412x694",
				"412x732",
				"414x736",
				"540x960",
				"598x360",
				"640x360",
				"720x1280",
				"768x1024",
				"800x1280",
				"800x600",
				"854x480"};
		
		print(createResolutionMap(resolutions));
	}
	
	private static Map createResolutionMap(String[] resolutions){
		
		Integer width = null;
		Integer height = null;
		String[] resolutionArr = null;
		Map<String,String> map = new LinkedHashMap<>();
		
		boolean isMatch = false;
		for(String resolution:resolutions){
			isMatch = false;
			resolutionArr = resolution.split("x");
			if(map.get(resolutionArr[0]) == null){
				map.put(resolutionArr[0],resolution);
			}else{
				String data = map.get(resolutionArr[0]);
				map.put(resolutionArr[0],data+","+resolution);
			}
			/*
			width = Integer.valueOf(resolutionArr[0]);
			height = Integer.valueOf(resolutionArr[1])
			if(map.size() == 0){
				map.put(resolution,resolution);
			}else{
				for(String mapResolution:map.keySet()){
					if(match(mapResolution, width, height)){
						isMatch = true;
						String mapData = map.get(mapResolution);
						map.put(mapResolution,mapData+","+resolution);
						break;
					}
				}
				if(!isMatch){
					if(map.get(resolution) == null){
						map.put(resolution, resolution);
					}
				}
			}*/
		}
		return map;
	}
	
	/*private static boolean match(String res,Integer width,Integer height){
		Integer mWidth = Integer.valueOf(res.split("x")[0]);
		Integer mHeight = Integer.valueOf(res.split("x")[1]);
		Double data1 = Math.pow((mWidth-width),2)+Math.pow((mHeight-height),2);
		Double data2 = Math.pow(allowNumber,2);
		return  data1<=data2 ;
	}*/
	
	private static void print(Map<String,String> map){
		for(Map.Entry<String,String> entry:map.entrySet()){
			System.out.print("键为:"+entry.getKey());
			System.out.print("值为:"+entry.getValue());
			System.out.println();
		}
	}
}
