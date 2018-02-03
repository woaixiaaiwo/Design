package com.boge.designs.adapter.old;

import com.boge.designs.adapter.old.abstracts.Player;

/**
 * 前锋 
 */
public class Forwards extends Player{

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		System.out.println("前锋:"+name+"进攻");
	}

	@Override
	public void defend() {
		// TODO Auto-generated method stub
		System.out.println("前锋:"+name+"防守");
		
	}

}
