package com.boge.designs.state.states;

import com.boge.designs.state.StateHandler;

public class NoonState implements AbstractState{

	@Override
	public void changeState(StateHandler handler) {
		// TODO Auto-generated method stub
		if(handler.getHour() >= 12 && handler.getHour() <= 18){
			System.out.println("下午");
		}else{
			handler.setState(new EveingState());
			handler.changeState();
		}
	}

}
