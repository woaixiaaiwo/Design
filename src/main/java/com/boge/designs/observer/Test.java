package com.boge.designs.observer;

import com.boge.designs.observer.concretenotifications.ConcreteNotification1;
import com.boge.designs.observer.concreteobservers.ConcreteObserver;

public class Test {

	public static void main(String[] args) {
		ConcreteObserver observer1 = new ConcreteObserver();
		ConcreteObserver observer2 = new ConcreteObserver();
		ConcreteObserver observer3 = new ConcreteObserver();
		
		ConcreteNotification1 notification1 = new ConcreteNotification1("001");
		//注册观察者
		notification1.add(observer1);
		notification1.add(observer2);
		notification1.add(observer3);
		//通知观察者
		notification1.notifyObservers(null);
		System.out.println("---------------------移除观察者："+observer1.hashCode()+"---------------------");
		//移除观察者
		notification1.remove(observer1);
		//通知观察者
		notification1.notifyObservers(null);
	}
	
}
