package com.boge.designs.visitor.visitors.concretes;

import com.boge.designs.visitor.element.concretes.ConcreteElementA;
import com.boge.designs.visitor.element.concretes.ConcreteElementB;
import com.boge.designs.visitor.visitors.abstracts.Visitor;

/**
 * 访问者模式，具体访问者1
 */
public class ConcreteVisitor1 extends Visitor{

	@Override
	public void visitConcreteElementA(ConcreteElementA concreteElementA) {
		// TODO Auto-generated method stub
		System.out.println("访问者1访问了:"+concreteElementA.getName());
	}

	@Override
	public void visitConcreteElementB(ConcreteElementB concreteElementB) {
		// TODO Auto-generated method stub
		System.out.println("访问者1访问了:"+concreteElementB.getName());
	}

}
