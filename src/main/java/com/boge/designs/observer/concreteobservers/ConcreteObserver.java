package com.boge.designs.observer.concreteobservers;

import com.boge.designs.observer.abstractobservers.AbstractObserver;

/**
 * 具体观察者角色 
 */
public class ConcreteObserver implements AbstractObserver{

	@Override
	public void update(String param) {
		// TODO Auto-generated method stub
		System.out.println("我是通知者"+param+"，对象"+this.hashCode()+"，你被通知了!");
	}

}
