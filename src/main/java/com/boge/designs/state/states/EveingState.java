package com.boge.designs.state.states;

import com.boge.designs.state.StateHandler;

public class EveingState implements AbstractState{

	@Override
	public void changeState(StateHandler handler) {
		// TODO Auto-generated method stub
		System.out.println("晚上了，睡觉");
		
	}

}
