package com.boge.designs.visitor.element.abstracts;

import com.boge.designs.visitor.visitors.abstracts.Visitor;

/**
 * 访问者模式，抽象元素，用于访问 
 */
public abstract class Element {


	protected String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public abstract void accept(Visitor visitor);
	
}
