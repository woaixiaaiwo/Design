package com.boge.designs.abstractfactorybyreflection.factorys;

import java.lang.reflect.Constructor;

import com.boge.designs.abstractfactorybyreflection.products.abstractproducts.Fruit;
import com.boge.designs.abstractfactorybyreflection.products.abstractproducts.Veggie;

/**
 * 使用简单工厂类和反射实现抽象工厂模式
 */
public class SimpleFactory {

	public static String area = "North";
	
	public static Fruit createFruit(String name){
		
		String className = "com.boge.designs.abstractfactorybyreflection.products.concreteproducts."+area+"Fruit";
		try {
			Class clazz = Class.forName(className);
			Constructor<Fruit> constructor = clazz.getConstructor(String.class);
			return constructor.newInstance(name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public static Veggie createVeggie(String name){
		
		String className = "com.boge.designs.abstractfactorybyreflection.products.concreteproducts."+area+"Veggie";
		try {
			Class clazz = Class.forName(className);
			Constructor<Veggie> constructor = clazz.getConstructor(String.class);
			return constructor.newInstance(name);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
}
