package com.boge.designs.flyweight;

import java.util.HashMap;

import com.boge.designs.flyweight.abstracts.WebSite;
import com.boge.designs.flyweight.concretes.ConcreteWebSite;


/**
 * 享元模式，共享对象工厂 
 */
public class WebSiteFactory {
	
	private static HashMap<String,WebSite> map = new HashMap<>();

	
	public static WebSite getWebSiteByName(String key){
		WebSite webSite = map.get(key);
		
		if(webSite == null){
			webSite = new ConcreteWebSite(key);
			map.put(key, webSite);
		}
		
		return webSite;
	}
	
	public static int getWebSiteNumbers(){
		return map.size();
	}
	
}
