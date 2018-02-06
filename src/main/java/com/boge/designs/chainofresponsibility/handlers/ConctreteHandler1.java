package com.boge.designs.chainofresponsibility.handlers;

/**
 * 职责链模式，具体处理类1 
 */
public class ConctreteHandler1 extends Handler{

	@Override
	public void HandleRequest(int request) {
		// TODO Auto-generated method stub
		if(request < 10){
			System.out.println("处理器1可以处理");
		}else{
			System.out.println("处理器1无法处理，交给上一级");
			if(next != null){
				next.HandleRequest(request);
			}
		}
	}
}
