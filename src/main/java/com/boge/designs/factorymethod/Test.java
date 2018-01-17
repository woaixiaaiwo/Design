package com.boge.designs.factorymethod;

import com.boge.designs.factorymethod.concatefactorys.AddFactory;
import com.boge.designs.factorymethod.concatefactorys.DivideFactory;
import com.boge.designs.factorymethod.concatefactorys.MinusFactory;
import com.boge.designs.factorymethod.concatefactorys.MutiplyFactory;
import com.boge.designs.factorymethod.interfaces.Operate;

public class Test {

	public static void main(String[] args) {
		Operate operate = new AddFactory().getBean();
		System.out.println(operate.calculate(1.0,2.0));
		operate = new MinusFactory().getBean();
		System.out.println(operate.calculate(1.0,2.0));
		operate = new MutiplyFactory().getBean();
		System.out.println(operate.calculate(1.0,2.0));
		operate = new DivideFactory().getBean();
		System.out.println(operate.calculate(1.0,2.0));
	}
	
}
