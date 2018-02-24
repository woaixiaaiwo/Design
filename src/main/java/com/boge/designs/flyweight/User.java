package com.boge.designs.flyweight;

/**
 * 享元模式，用户类，作为网站的外部状态 
 */
public class User {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public User(String name) {
		this.name = name;
	}
	
	
	
}
