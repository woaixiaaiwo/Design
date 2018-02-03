package com.boge.designs.memento.originator;

import com.boge.designs.memento.memento.RoleMemento;

/**
 * 游戏角色，发起人类 
 */
public class PlayRole {

	//攻击力
	private int attack;
	
	//生命值
	private int life;
	
	//用于创建备忘录，保存信息
	public RoleMemento createMemrnto(){
		RoleMemento roleMemento = new RoleMemento(this.attack,this.life);
		return roleMemento;
	}
	//用于从备忘录中读取信息
	public void reduceFromMemrnto(RoleMemento roleMemento){
		this.attack = roleMemento.getAttack();
		this.life = roleMemento.getLife();
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}
	
	public void display(){
		System.out.println("攻击力：" + this.attack + "|生命力：" + this.life);
	}
	
}
