package com.boge.designs.composite.abstracts;

/**
 * 组合模式，公司类 
 */
public abstract class Company {

	private String name;
	
	public Company(String name) {
		super();
		this.name = name;
	}
	public Company(){}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public abstract void add(Company company);
	public abstract void romove(Company company);
	public abstract void display(int depth);
	
}
