package com.boge.designs.visitor.element.concretes;

import com.boge.designs.visitor.element.abstracts.Element;
import com.boge.designs.visitor.visitors.abstracts.Visitor;

/**
 * 访问者模式，具体元素A
 */
public class ConcreteElementA extends Element{

	public ConcreteElementA(String name){
		this.name = "元素A_"+name;
	}


	@Override
	public void accept(Visitor visitor) {
		// TODO Auto-generated method stub
		visitor.visitConcreteElementA(this);
	}

}
