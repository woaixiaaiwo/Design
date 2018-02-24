package com.boge.designs.flyweight.concretes;

import com.boge.designs.flyweight.User;
import com.boge.designs.flyweight.abstracts.WebSite;

/**
 * 享元模式，具体共享角色类 
 */
public class ConcreteWebSite extends WebSite{

	//name表示网站分类，不可改变，属于内部状态
	private String name;
	
	public ConcreteWebSite(String name){
		this.name = name;
	}
	
	@Override
	public void Use(User user) {
		// TODO Auto-generated method stub
		System.out.println("网站分类："+name+"，用户："+user.getName());
	}

}
