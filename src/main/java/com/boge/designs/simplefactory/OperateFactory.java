package com.boge.designs.simplefactory;

public class OperateFactory {
	
	public static Operate getBean(String ope){
		
		switch (ope) {
		case "add":
			return new Add();
		case "minus":
			return new Minus();
		case "mutiply":
			return new Mutiply();
		case "divide":
			return new Divide();
		default:
			return null;
		}
		
	}

}
