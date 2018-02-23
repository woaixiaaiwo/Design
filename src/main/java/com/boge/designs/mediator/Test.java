package com.boge.designs.mediator;

import com.boge.designs.mediator.concretes.ConcreteColleague1;
import com.boge.designs.mediator.concretes.ConcreteColleague2;
import com.boge.designs.mediator.concretes.ConcreteMediator;

public class Test {
	
	public static void main(String[] args) {
		
		ConcreteMediator mediator = new ConcreteMediator();
		
		//同事类要认识中介者
		ConcreteColleague1 colleague1 = new ConcreteColleague1(mediator);
		//同事类要认识中介者
		ConcreteColleague2 colleague2 = new ConcreteColleague2(mediator);
		
		//中介者类要认识所有同事
		mediator.setColleague1(colleague1);
		mediator.setColleague2(colleague2);
		
		colleague1.send("同事2你好，我是同事1");
		colleague2.send("同事1你好，我是同事2");
		
	}

}
