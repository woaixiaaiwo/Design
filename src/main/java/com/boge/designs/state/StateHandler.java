package com.boge.designs.state;

import com.boge.designs.state.states.AbstractState;
import com.boge.designs.state.states.MorningState;

public class StateHandler {
	private AbstractState state;
	private Integer hour;
	//初始状态为早上
	StateHandler(){
		this.state = new MorningState();
	}
	public void changeState(){
		state.changeState(this);
	}
	

	public AbstractState getState() {
		return state;
	}
	public void setState(AbstractState state) {
		this.state = state;
	}
	public Integer getHour() {
		return hour;
	}
	public void setHour(Integer hour) {
		this.hour = hour;
	}
	
}
