package com.boge.designs.state.states;

import com.boge.designs.state.StateHandler;

public class MorningState implements AbstractState{

	@Override
	public void changeState(StateHandler handler) {
		// TODO Auto-generated method stub
		if(handler.getHour() < 12){
			System.out.println("上午");
		}else{
			handler.setState(new NoonState());
			handler.changeState();
		}
	}

}
