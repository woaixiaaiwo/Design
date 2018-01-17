package com.boge.designs.observer.abstractnotifications;

import java.util.ArrayList;
import java.util.List;

import com.boge.designs.factorymethod.impls.Add;
import com.boge.designs.observer.abstractobservers.AbstractObserver;

/**
 * 抽象主题角色 
 * 保留抽象观察者列表
 */
public abstract class AbstractNotifications {
	
	public List<AbstractObserver> list = new ArrayList<>();
	
	public void add(AbstractObserver observer){
		list.add(observer);
	}
	
	public void remove(AbstractObserver observer){
		list.remove(observer);
	}
	
    public abstract void notifyObservers(String param);

}
