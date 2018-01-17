package com.boge.designs.builder;

public class Person {

	private String head;
	
	private String hand;
	
	private String foot;

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

	public String getHand() {
		return hand;
	}

	public void setHand(String hand) {
		this.hand = hand;
	}

	public String getFoot() {
		return foot;
	}

	public void setFoot(String foot) {
		this.foot = foot;
	}
	
	public void display(){
		
		System.out.println(head+","+hand+","+foot);
		
	}
	
}
