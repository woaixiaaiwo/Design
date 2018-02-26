package com.boge.designs.interpreter.concrete;

import com.boge.designs.interpreter.Context;
import com.boge.designs.interpreter.abstracts.AbstractExpression;

/**
 * 解释器模式，终结符表达式 
 */
public class TerminalExpression extends AbstractExpression{

	@Override
	public void interpret(Context context) {
		// TODO Auto-generated method stub
		System.out.println("终端解释器");
	}

}
