package com.boge.designs.templatemethod;

public abstract class Template {

	public void displayData(){
		System.out.println("数据为:"+getData());
	}
	
	public abstract String getData();
}
