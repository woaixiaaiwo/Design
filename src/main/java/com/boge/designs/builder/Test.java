package com.boge.designs.builder;

public class Test {

	public static void main(String[] args) {
		PersonBuilder personBuilder1 = new ConcatePersonBuilder1();
		PersonBuilder personBuilder2 = new ConcatePersonBuilder2();
		
		Person person1 = Command.getPerson(personBuilder1);
		
		Person person2 = Command.getPerson(personBuilder2);
		
		person1.display();
		person2.display();
		
	}
	
}
