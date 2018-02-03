package com.boge.designs.adapter;

import com.boge.designs.adapter.old.Center;
import com.boge.designs.adapter.old.abstracts.Player;

public class Test {

	public static void main(String[] args) {
		
		Player a = new Center("路人甲");
		a.attack();
		a.defend();
		
		Player b  = new Adapter("姚明");
		b.attack();
		b.defend();
	}
	
}
