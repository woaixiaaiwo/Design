package com.boge.designs.interpreter;

import java.util.ArrayList;
import java.util.List;

import com.boge.designs.interpreter.abstracts.AbstractExpression;
import com.boge.designs.interpreter.concrete.NonterminalExpression;
import com.boge.designs.interpreter.concrete.TerminalExpression;

public class Test {

	public static void main(String[] args) {
		Context context = new Context();
		List<AbstractExpression> list = new ArrayList<AbstractExpression>();
		
		//构建语法树
		list.add(new TerminalExpression());
		list.add(new NonterminalExpression());
		list.add(new TerminalExpression());
		list.add(new TerminalExpression());
		
		//进行解释操作
		for(AbstractExpression expression:list){
			expression.interpret(context);
		}
	}
	
}
