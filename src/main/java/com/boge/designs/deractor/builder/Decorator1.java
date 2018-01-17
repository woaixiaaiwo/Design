package com.boge.designs.deractor.builder;

import com.boge.designs.deractor.normal.Component;


/**
 * 具体装饰角色1 
 */
public class Decorator1 extends Component{
	
	private Component deractor;
	
	public Decorator1(Component deractor){
		this.deractor = deractor;
	}

	@Override
	public void draw(StringBuilder data) {
		// TODO Auto-generated method stub
		deractor.draw(data);
		System.out.println("对数据进行加密...");
		data = data.append("加密");
		System.out.println("加密后数据为:"+data);
	}

}
