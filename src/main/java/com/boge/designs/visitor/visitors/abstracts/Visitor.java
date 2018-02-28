package com.boge.designs.visitor.visitors.abstracts;

import com.boge.designs.visitor.element.concretes.ConcreteElementA;
import com.boge.designs.visitor.element.concretes.ConcreteElementB;

/**
 * 访问者模式，抽象访问者
 */
public abstract class Visitor {

	public abstract void visitConcreteElementA(ConcreteElementA concreteElementA);
	
	public abstract void visitConcreteElementB(ConcreteElementB concreteElementB);
	
}
