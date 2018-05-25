package com.boge.concurrency;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * 使用闭锁实现玩家连接服务器
 * 每个玩家连接服务器的时长不等，要等待所有玩家都连接上，才能开始游戏 
 * 闭锁只能使用一次，用于等待某个事件发生
 */
public class CountDownLatchLearn {

	private final static int PLAYER_NUMBER = 5;
	
	private final static CountDownLatch countDownLatch = new CountDownLatch(PLAYER_NUMBER);
	
	public static void main(String[] args) throws InterruptedException {

		Thread thread = null;
		Random random = new Random();
		
		for(int i=0;i<PLAYER_NUMBER;i++){
			thread = new Thread(new Player("玩家"+i,Math.abs(random.nextLong())%2000,countDownLatch));
			thread.start();
		}
		countDownLatch.await();
		System.out.println("所有玩家都已经进入游戏，游戏开始!");
	}
}

class Player implements Runnable{

	private String name;
	private Long connectTime;
	private CountDownLatch countDownLatch;
	
	
	
	public Player(String name, Long connectTime, CountDownLatch countDownLatch) {
		super();
		this.name = name;
		this.connectTime = connectTime;
		this.countDownLatch = countDownLatch;
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("玩家："+name+"开始连接游戏...");
		try {
			Thread.sleep(connectTime);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("玩家："+name+"连接游戏成功，等待其他玩家...");
		countDownLatch.countDown();
	}
	
}
