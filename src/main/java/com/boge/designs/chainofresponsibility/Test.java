package com.boge.designs.chainofresponsibility;

import com.boge.designs.chainofresponsibility.handlers.ConctreteHandler1;
import com.boge.designs.chainofresponsibility.handlers.ConctreteHandler2;
import com.boge.designs.chainofresponsibility.handlers.Handler;

public class Test {

	public static void main(String[] args) {
		Handler handler1 = new ConctreteHandler1();
		Handler handler2 = new ConctreteHandler2();
		//在这里设置处理类1的上级，这是和状态模式不同的地方
		handler1.setNext(handler2);
		handler1.HandleRequest(19);
	}
	
}
