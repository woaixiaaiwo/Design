package com.boge.designs.chainofresponsibility.handlers;

/**
 * 职责链模式，抽象处理类 
 */
public abstract class Handler {

	protected Handler next;

	public Handler getNext() {
		return next;
	}

	public void setNext(Handler next) {
		this.next = next;
	}
	
	public abstract void HandleRequest(int request);
	
}
