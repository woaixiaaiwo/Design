package com.boge.designs.adapter.old;

import com.boge.designs.adapter.old.abstracts.Player;

/**
 * 后卫
 */
public class Guards extends Player{

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		System.out.println("后卫:"+name+"进攻");
	}

	@Override
	public void defend() {
		// TODO Auto-generated method stub
		System.out.println("后卫:"+name+"防守");
		
	}

}
