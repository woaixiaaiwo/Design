package com.boge.designs.abstractfactory.factorys.abstractfactorys;

import com.boge.designs.abstractfactory.products.abstractproducts.Fruit;
import com.boge.designs.abstractfactory.products.abstractproducts.Veggie;

/**
 * 抽象工厂，定义如何创建产品 
 */
public interface IFactory {

	Fruit createFruit(String name);
	
	Veggie createVeggie(String name);
	
}
