package com.boge.designs.interpreter.abstracts;

import com.boge.designs.interpreter.Context;

/**
 * 解释权模式，抽象表达式 
 */
public abstract class AbstractExpression {

	public abstract void interpret(Context context);
	
}
