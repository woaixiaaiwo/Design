package com.boge.designs.mediator.concretes;

import com.boge.designs.mediator.abstracts.Colleague;
import com.boge.designs.mediator.abstracts.Mediator;

/**
 * 具体中介者 
 */
public class ConcreteMediator extends Mediator{

	private ConcreteColleague1 colleague1;
	
	private ConcreteColleague2 colleague2;



	@Override
	public void send(String message,Colleague colleague) {
		// TODO Auto-generated method stub
		//如果是当前对象，向除了当前对象的其他对象发送消息
		if(colleague == colleague1){
			colleague2.notify(message);
		}else{
			colleague1.notify(message);
		}
	}
	
	public ConcreteColleague1 getColleague1() {
		return colleague1;
	}



	public void setColleague1(ConcreteColleague1 colleague1) {
		this.colleague1 = colleague1;
	}



	public ConcreteColleague2 getColleague2() {
		return colleague2;
	}



	public void setColleague2(ConcreteColleague2 colleague2) {
		this.colleague2 = colleague2;
	}

}
