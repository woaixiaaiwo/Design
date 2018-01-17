package com.boge.designs.observer.concretenotifications;

import com.boge.designs.observer.abstractnotifications.AbstractNotifications;
import com.boge.designs.observer.abstractobservers.AbstractObserver;

/**
 * 具体主题角色 
 */
public class ConcreteNotification1 extends AbstractNotifications{

	private String name;
	
	public ConcreteNotification1(){}
	
	public ConcreteNotification1(String name){
		this.name = name;
	}
	
	@Override
	public void notifyObservers(String param) {
		// TODO Auto-generated method stub
		for(AbstractObserver observer:list){
			if(param == null)param = name;
			observer.update(param);
		}
	}

}
