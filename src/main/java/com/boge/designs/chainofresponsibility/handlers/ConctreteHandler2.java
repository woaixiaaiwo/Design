package com.boge.designs.chainofresponsibility.handlers;

/**
 * 职责链模式，具体处理类2 
 */
public class ConctreteHandler2 extends Handler{

	@Override
	public void HandleRequest(int request) {
		// TODO Auto-generated method stub
		if(request > 10 && request<20){
			System.out.println("处理器2可以处理");
		}else{
			System.out.println("处理器2无法处理，交给上一级");
			if(next != null){
				next.HandleRequest(request);
			}
		}
	}

}
