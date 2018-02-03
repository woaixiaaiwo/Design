package com.boge.designs.adapter;

import com.boge.designs.adapter.old.abstracts.Player;

/**
 * 适配器类，继承球员接口，保持一个外籍球员的引用
 * 在实现的方法中，调用外籍球员的方法
 */
public class Adapter extends Player{

	private ForeignCenter foreignCenter = new ForeignCenter();
	
	public Adapter(String name) {
		// TODO Auto-generated constructor stub
		this.name = name;
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub
		foreignCenter.setName(name);
		foreignCenter.foreignAttack();
	}

	@Override
	public void defend() {
		// TODO Auto-generated method stub
		foreignCenter.setName(name);
		foreignCenter.foreignDefend();
	}

	
}
