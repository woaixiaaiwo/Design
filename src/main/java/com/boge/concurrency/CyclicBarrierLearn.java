package com.boge.concurrency;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 使用栅栏实现点名功能 
 * 游客游玩景点，每到一个地方，要等所有游客都集合，才开始下一个地方
 * 栅栏是可以重复使用的，用于等待其他线程
 */
public class CyclicBarrierLearn {

	private final static int VISITOR_NUMBER = 5;
	
	private static final CyclicBarrier cyclicBarrier = new CyclicBarrier(VISITOR_NUMBER,new Runnable() {
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			System.out.println("所有游客都到齐，开始嗨吧!");
		}
	});
	
	public static void main(String[] args) throws InterruptedException {

		Thread thread = null;
		for(int i=0;i<VISITOR_NUMBER;i++){
			thread = new Thread(new Visitor("游客"+i,cyclicBarrier));
			thread.start();
		}
	}
}

class Visitor implements Runnable{

	private String name;
	private CyclicBarrier cyclicBarrier;
	
	
	
	public Visitor(String name, CyclicBarrier cyclicBarrier) {
		super();
		this.name = name;
		this.cyclicBarrier = cyclicBarrier;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			System.out.println("我是游客："+name+",我到了第一个景点!");
			cyclicBarrier.await();
			System.out.println("我是游客："+name+",我到了第二个景点!");
			cyclicBarrier.await();
			System.out.println("我是游客："+name+",我到了第三个景点!");
			cyclicBarrier.await();
			System.out.println("我是游客："+name+",我到了第四个景点!");
			cyclicBarrier.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	}
	
}