package com.boge.designs.memento.memento;

/**
 * 游戏角色备忘录，保留游戏角色的一些信息 
 */
public class RoleMemento {
	
	//攻击力
	private int attack;
	
	//生命值
	private int life;

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

	public RoleMemento(int attack, int life) {
		super();
		this.attack = attack;
		this.life = life;
	}
	
	public RoleMemento() {
	}

}
