package com.boge.designs.abstractfactory;

import com.boge.designs.abstractfactory.factorys.abstractfactorys.IFactory;
import com.boge.designs.abstractfactory.factorys.concretefactorys.NorthFactory;
import com.boge.designs.abstractfactory.factorys.concretefactorys.SouthFactory;
import com.boge.designs.abstractfactory.products.abstractproducts.Fruit;
import com.boge.designs.abstractfactory.products.abstractproducts.Veggie;

public class Test {

	public static void main(String[] args) {
		IFactory factory = new SouthFactory();
		
		Fruit fruit = factory.createFruit("苹果");
		Veggie veggie = factory.createVeggie("大白菜");
		fruit.display();
		veggie.display();
	}
}
