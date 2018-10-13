package com.boge.designs.delegate;

public class Test {

	public static void main(String[] args) {
		Dispatcher dispatcher = new Dispatcher();
		//指定员工1执行任务
		dispatcher.doDispatch(new Executor1());
	}
	
}
