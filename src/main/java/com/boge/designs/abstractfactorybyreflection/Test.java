package com.boge.designs.abstractfactorybyreflection;

import com.boge.designs.abstractfactorybyreflection.factorys.SimpleFactory;
import com.boge.designs.abstractfactorybyreflection.products.abstractproducts.Fruit;
import com.boge.designs.abstractfactorybyreflection.products.abstractproducts.Veggie;

public class Test {

	public static void main(String[] args) {
		SimpleFactory.area = "South";
		
		Fruit fruit = SimpleFactory.createFruit("苹果");
		
		Veggie veggie = SimpleFactory.createVeggie("白菜");
		
		fruit.display();
		veggie.display();
	}
	
}
