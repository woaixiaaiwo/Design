package com.boge.designs.flyweight;

import com.boge.designs.flyweight.abstracts.WebSite;

public class Test {

	public static void main(String[] args) {
		
		User user = new User("小明");
		WebSite w = WebSiteFactory.getWebSiteByName("博客");
		w.Use(user);
		user = new User("小红");
		w = WebSiteFactory.getWebSiteByName("博客");
		w.Use(user);
		user = new User("小康");
		w = WebSiteFactory.getWebSiteByName("产品展示");
		w.Use(user);
		user = new User("小明");
		w = WebSiteFactory.getWebSiteByName("其他");
		w.Use(user);
		
		System.out.println("网站种类有:"+WebSiteFactory.getWebSiteNumbers()+"种");
	}
	
}
