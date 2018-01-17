package com.boge.designs.deractor;

import com.boge.designs.deractor.builder.Decorator1;
import com.boge.designs.deractor.builder.Decorator2;
import com.boge.designs.deractor.normal.Component;

public class Test {
	
	public static void main(String[] args) {
		Component concateDeractor = new ConcateComponent();
		Component deractor1 = new Decorator1(concateDeractor);
		Component deractor2 = new Decorator2(deractor1);
		StringBuilder sb = new StringBuilder("原始数据");
		deractor2.draw(sb);
		System.out.println(sb);
	}

}
