package com.boge.designs.abstractfactorybyreflection.products.concreteproducts;

import com.boge.designs.abstractfactorybyreflection.products.abstractproducts.Fruit;

/**
 * 具体产品，南方水果
 */
public class SouthFruit implements Fruit{

	String name;
	
	public SouthFruit(){}
	
	public SouthFruit(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public void display() {
		// TODO Auto-generated method stub
		System.out.println("南方水果:"+name);
	}
}
