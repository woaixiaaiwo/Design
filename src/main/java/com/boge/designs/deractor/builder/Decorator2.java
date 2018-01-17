package com.boge.designs.deractor.builder;

import com.boge.designs.deractor.normal.Component;

/**
 * 具体装饰角色2 
 */
public class Decorator2 extends Component{
	
	private Component deractor;
	
	public Decorator2(Component deractor){
		this.deractor = deractor;
	}

	@Override
	public void draw(StringBuilder data) {
		// TODO Auto-generated method stub
		deractor.draw(data);
		System.out.println("对数据进行校验...");
		data = data.append("校验");
		System.out.println("加密后数据为:"+data);
	}

}
