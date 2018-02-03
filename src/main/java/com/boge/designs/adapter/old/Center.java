package com.boge.designs.adapter.old;

import com.boge.designs.adapter.old.abstracts.Player;

/**
 * 中锋 
 */
public class Center extends Player{

	public Center(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		System.out.println("中锋:"+name+"进攻");
	}

	@Override
	public void defend() {
		// TODO Auto-generated method stub
		System.out.println("中锋:"+name+"防守");
		
	}

}
