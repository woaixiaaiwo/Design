package com.boge.designs.builder;

public class ConcatePersonBuilder1 extends PersonBuilder{
	
	Person person;

	@Override
	void buildHead(Person person) {
		// TODO Auto-generated method stub
		person.setHead("方头");
	}

	@Override
	void buildHand(Person person) {
		// TODO Auto-generated method stub

		person.setHand("四只手");
	}

	@Override
	void buildFoot(Person person) {
		// TODO Auto-generated method stub
		person.setFoot("八条腿");
	}

}
