package com.boge.designs.templatemethod;

public class Template1 extends Template{

	@Override
	public String getData() {
		// TODO Auto-generated method stub
		return "123";
	}
	
	public static void main(String[] args) {
		new Template1().displayData();
	}

}
