package com.boge.designs.visitor.element.concretes;

import com.boge.designs.visitor.element.abstracts.Element;
import com.boge.designs.visitor.visitors.abstracts.Visitor;

/**
 * 访问者模式，具体元素B
 */
public class ConcreteElementB extends Element{

	public ConcreteElementB(String name){
		this.name = "元素B_"+name;
	}
	
	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub
		visitor.visitConcreteElementB(this);
	}

}
