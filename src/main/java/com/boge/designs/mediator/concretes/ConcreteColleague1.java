package com.boge.designs.mediator.concretes;

import com.boge.designs.mediator.abstracts.Colleague;
import com.boge.designs.mediator.abstracts.Mediator;

/**
 * 具体同事类1 
 */
public class ConcreteColleague1 extends Colleague{

	public ConcreteColleague1(Mediator mediator) {
		super(mediator);
		// TODO Auto-generated constructor stub
	}
	
	public void send(String message){
		mediator.send(message, this);
	}
	
	public void notify(String message){
		System.out.println("同事1得到消息:"+message);
	}

}
