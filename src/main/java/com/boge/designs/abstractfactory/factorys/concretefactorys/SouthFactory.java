package com.boge.designs.abstractfactory.factorys.concretefactorys;

import com.boge.designs.abstractfactory.factorys.abstractfactorys.IFactory;
import com.boge.designs.abstractfactory.products.abstractproducts.Fruit;
import com.boge.designs.abstractfactory.products.abstractproducts.Veggie;
import com.boge.designs.abstractfactory.products.concreteproducts.NorthFruit;
import com.boge.designs.abstractfactory.products.concreteproducts.NorthVeggie;
import com.boge.designs.abstractfactory.products.concreteproducts.SouthFruit;
import com.boge.designs.abstractfactory.products.concreteproducts.SouthVeggie;

/**
 * 南方工厂类，创建具体的南方产品 
 */
public class SouthFactory implements IFactory{

	@Override
	public Fruit createFruit(String name) {
		// TODO Auto-generated method stub
		return new SouthFruit(name);
	}

	@Override
	public Veggie createVeggie(String name) {
		// TODO Auto-generated method stub
		return new SouthVeggie(name);
	}

}
