package com.boge.designs.simplefactory;

public class Test {

	public static void main(String[] args) {
		Operate operate = OperateFactory.getBean("add");
		System.out.println(operate.calculate(1.0,2.0));
		operate = OperateFactory.getBean("minus");
		System.out.println(operate.calculate(1.0,2.0));
		operate = OperateFactory.getBean("mutiply");
		System.out.println(operate.calculate(1.0,2.0));
		operate = OperateFactory.getBean("divide");
		System.out.println(operate.calculate(1.0,2.0));
	}
	
}
