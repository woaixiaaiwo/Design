package com.boge.designs.mediator.concretes;

import com.boge.designs.mediator.abstracts.Colleague;
import com.boge.designs.mediator.abstracts.Mediator;

/**
 * 具体同事类2 
 */
public class ConcreteColleague2 extends Colleague{

	public ConcreteColleague2(Mediator mediator) {
		super(mediator);
		// TODO Auto-generated constructor stub
	}
	
	public void send(String message){
		mediator.send(message, this);
	}
	
	public void notify(String message){
		System.out.println("同事2得到消息:"+message);
	}

}
