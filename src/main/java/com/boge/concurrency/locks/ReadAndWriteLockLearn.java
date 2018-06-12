package com.boge.concurrency.locks;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


/**
 * 读写锁测试 
 */
public class ReadAndWriteLockLearn {

	
	
	public static void main(String[] args) {
		ReadWriteMap<String,String> map = new ReadWriteMap(new HashMap<>());
		Runnable read = new ReadThread(map);
		Runnable write = new WriteThread(map);
		
		Thread readThread1 = new Thread(read, "读线程1");
		Thread readThread2 = new Thread(read, "读线程2");
		Thread writeThread1 = new Thread(write, "写线程1");
		Thread writeThread2 = new Thread(write, "写线程2");
		
		readThread1.start();
		readThread2.start();
		writeThread1.start();
		writeThread2.start();
		
	}
	
}

class ReadThread implements Runnable{

	private ReadWriteMap<String,String> map;
	
	ReadThread(ReadWriteMap<String,String> map){
		this.map = map;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Random random = new Random();
		while(true){
			map.get(String.valueOf(random.nextInt(100)));
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
}

class WriteThread implements Runnable{

	private ReadWriteMap<String,String> map;
	
	WriteThread(ReadWriteMap<String,String> map){
		this.map = map;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Random random = new Random();
		while(true){
			map.put(String.valueOf(random.nextInt(100)),String.valueOf(random.nextInt(100)));
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
}

class ReadWriteMap<K,V>{
	private final Map<K, V> map;
	
	private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	
	private final Lock readLock = readWriteLock.readLock();
	
	private final Lock writeLock = readWriteLock.writeLock();
	
	public ReadWriteMap(Map<K, V> map){
		this.map = map;
	}
	
	/**
	 * 写锁中可以获取读锁
	 * 线程在获取到写锁后，别的线程无法获取写锁，也无法获取读锁 
	 */
	public void put(K key,V value){
		if(writeLock.tryLock()){
			try{
				System.out.println(Thread.currentThread().getName()+"获取写锁成功！");
				System.out.println("在写锁中获取读锁...");
				this.get(key);
				Thread.sleep(2000);
				System.out.println(Thread.currentThread().getName()+"----写入数据成功----");
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				writeLock.unlock();
			}
		}else{
			System.out.println(Thread.currentThread().getName()+"获取写锁失败！");
		}
	}
	
	/**
	 * 读锁中不可以获取写锁
	 * 线程在获取到读锁后，别的线程无法获取写锁，但是可以获取读锁 
	 */
	public void get(K key){
		if(readLock.tryLock()){
			try{
				System.out.println(Thread.currentThread().getName()+"获取读锁成功！");
				//这步不会成功，读锁中无法获取写锁
				/*System.out.println("在读锁中获取写锁...");
				this.put(key,null);*/
				Thread.sleep(2000);
				System.out.println(Thread.currentThread().getName()+"----读取数据成功----");
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				readLock.unlock();
			}
		}else{
			System.out.println(Thread.currentThread().getName()+"获取读锁失败！");
		}
		
	}
}
