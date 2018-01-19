package com.boge.designs.abstractfactorybyreflection.products.concreteproducts;
import com.boge.designs.abstractfactorybyreflection.products.abstractproducts.Veggie;

/**
 * 具体产品，北方蔬菜
 */
public class NorthVeggie implements Veggie{


	String name;
	
	public NorthVeggie(){}
	
	public NorthVeggie(String name){
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
		System.out.println("北方蔬菜:"+name);
	}
	
}
