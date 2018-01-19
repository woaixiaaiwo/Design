package com.boge.designs.abstractfactorybyreflection.products.concreteproducts;
import com.boge.designs.abstractfactorybyreflection.products.abstractproducts.Veggie;

/**
 * 具体产品，南方蔬菜
 */
public class SouthVeggie implements Veggie{

	String name;
	

	public SouthVeggie(){}
	
	public SouthVeggie(String name){
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
		System.out.println("南方蔬菜:"+name);
	}
}
