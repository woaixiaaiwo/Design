package com.boge.designs.state;

public class Test {

	public static void main(String[] args) {
		StateHandler stateHandler = new StateHandler();
		stateHandler.setHour(22);
		stateHandler.changeState();
	}
	
}
