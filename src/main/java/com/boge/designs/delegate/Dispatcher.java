package com.boge.designs.delegate;

/**
 * 委派者，指定哪个执行器执行任务 
 */
public class Dispatcher {

	public void doDispatch(IExecute execute) {
		execute.execute();
	}
	
}
