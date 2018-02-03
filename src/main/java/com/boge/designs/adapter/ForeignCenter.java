package com.boge.designs.adapter;

/**
 * 外籍中锋
 */
public class ForeignCenter {

	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void foreignAttack(){
		System.out.println("外籍中锋:"+name+"进攻");
	}
	
	public void foreignDefend(){
		System.out.println("外籍中锋:"+name+"防守");
	}
	
}
