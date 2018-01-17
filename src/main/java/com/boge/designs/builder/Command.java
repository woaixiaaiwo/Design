package com.boge.designs.builder;

public class Command {
	
	
	public static Person getPerson(PersonBuilder personBuilder){
		Person person = new Person();
		personBuilder.buildHead(person);
		personBuilder.buildHand(person);
		personBuilder.buildFoot(person);
		return person;
	}
	
}
