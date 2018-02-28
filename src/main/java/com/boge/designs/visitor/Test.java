package com.boge.designs.visitor;

import com.boge.designs.visitor.element.ObjectStructure;
import com.boge.designs.visitor.element.concretes.ConcreteElementA;
import com.boge.designs.visitor.element.concretes.ConcreteElementB;
import com.boge.designs.visitor.visitors.concretes.ConcreteVisitor1;
import com.boge.designs.visitor.visitors.concretes.ConcreteVisitor2;

public class Test {

	public static void main(String[] args) {
		
		//初始化枚举访问类
		ObjectStructure objectStructure = new ObjectStructure();
		
		//初始化访问者
		ConcreteVisitor1 concreteVisitor1 = new ConcreteVisitor1();
		ConcreteVisitor2 concreteVisitor2 = new ConcreteVisitor2();
		
		//初始化元素
		ConcreteElementA concreteElementA = new ConcreteElementA("A元素");
		ConcreteElementB concreteElementB = new ConcreteElementB("B元素");
		
		//关联枚举访问类和元素
		objectStructure.attach(concreteElementA);
		objectStructure.attach(concreteElementB);
		
		//访问
		objectStructure.accept(concreteVisitor1);
		objectStructure.accept(concreteVisitor2);
	}
	
}
