package com.boge.designs.builder;

public class ConcatePersonBuilder2 extends PersonBuilder{
	
	Person person;

	@Override
	void buildHead(Person person) {
		// TODO Auto-generated method stub
		person.setHead("圆头");
	}

	@Override
	void buildHand(Person person) {
		// TODO Auto-generated method stub

		person.setHand("两只手");
	}

	@Override
	void buildFoot(Person person) {
		// TODO Auto-generated method stub
		person.setFoot("四条腿");
	}

}
