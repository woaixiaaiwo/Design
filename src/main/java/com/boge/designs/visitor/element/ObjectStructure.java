package com.boge.designs.visitor.element;

import java.util.ArrayList;
import java.util.List;

import com.boge.designs.visitor.element.abstracts.Element;
import com.boge.designs.visitor.visitors.abstracts.Visitor;

/**
 * 访问者模式，枚举访问类，用于让访问者访问元素
 */
public class ObjectStructure {

	private List<Element> elements = new ArrayList<>();
	
	public void attach(Element element){
		elements.add(element);
	}
	
	public void remove(Element element){
		elements.remove(element);
	}
	
	public void accept(Visitor visitor){
		for(Element element:elements){
			element.accept(visitor);
		}
	}
}
