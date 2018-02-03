package com.boge.designs.memento.caretaker;

import com.boge.designs.memento.memento.RoleMemento;
import com.boge.designs.memento.originator.PlayRole;

/**
 * 管理者类，用于保存游戏角色信息 
 */
public class Caretaker {
	
	public RoleMemento createMemrnto(PlayRole playRole){
		return playRole.createMemrnto();
	}

}
