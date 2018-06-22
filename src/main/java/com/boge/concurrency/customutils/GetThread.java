package com.boge.concurrency.customutils;

public class GetThread implements Runnable{

	private BaseBoundedBuffer baseBoundedBuffer;
	
	public GetThread(BaseBoundedBuffer baseBoundedBuffer) {
		// TODO Auto-generated constructor stub
		this.baseBoundedBuffer = baseBoundedBuffer;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(true){
				baseBoundedBuffer.take();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
