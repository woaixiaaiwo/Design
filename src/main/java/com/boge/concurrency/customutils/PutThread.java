package com.boge.concurrency.customutils;

public class PutThread implements Runnable{

	private BaseBoundedBuffer baseBoundedBuffer;
	
	public PutThread(BaseBoundedBuffer baseBoundedBuffer) {
		// TODO Auto-generated constructor stub
		this.baseBoundedBuffer = baseBoundedBuffer;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(true){
				baseBoundedBuffer.put("123");
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
