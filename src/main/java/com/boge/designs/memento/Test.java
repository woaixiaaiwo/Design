package com.boge.designs.memento;

import com.boge.designs.memento.caretaker.Caretaker;
import com.boge.designs.memento.memento.RoleMemento;
import com.boge.designs.memento.originator.PlayRole;

public class Test {
	
	public static void main(String[] args) {
		//初始化信息
		PlayRole playRole = new PlayRole();
		playRole.setAttack(100);
		playRole.setLife(100);
		System.out.println("初始状态为：");
		playRole.display();

		//记录当前游戏角色信息
		Caretaker caretaker = new Caretaker();
		RoleMemento roleMemento = caretaker.createMemrnto(playRole);
		
		//打Boss
		playRole.setAttack(0);
		playRole.setLife(0);
		System.out.println("激战Boss后状态为：");
		playRole.display();
		
		//读取进度
		System.out.println("读取进度后状态为：");
		playRole.reduceFromMemrnto(roleMemento);
		playRole.display();
	}

}
