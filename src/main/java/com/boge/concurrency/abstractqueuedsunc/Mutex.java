package com.boge.concurrency.abstractqueuedsunc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;


public class Mutex implements Lock {
	// 静态内部类，自定义同步器
	private static class Sync extends AbstractQueuedSynchronizer {
		// 是否处于占用状态
		protected boolean isHeldExclusively() {
			return getState() == 1;
		}
		// 当状态为0的时候获取锁
		public boolean tryAcquire(int acquires) {
			//尝试将状态设置为acquires，无锁状态下进行
			if (compareAndSetState(0,acquires)) {
				setExclusiveOwnerThread(Thread.currentThread());
				return true;
			}
			return false;
		}
		// 释放锁，将状态设置为0
		protected boolean tryRelease(int releases) {
			//如果当前状态无锁，抛出IllegalMonitorStateException异常
			if (getState() == 0) throw new IllegalMonitorStateException();
			//将锁指向的线程设置为空
			setExclusiveOwnerThread(null);
			//当前状态设置为0，释放锁
			setState(0);
			return true;
		}
		// 返回一个Condition，每个condition都包含了一个condition队列
		Condition newCondition() { 
			return new ConditionObject(); 
		}
	}
	// 仅需要将操作代理到Sync上即可
	private final Sync sync = new Sync();
	
	public void lock() { 
		sync.acquire(1); 
	}
	
	public boolean tryLock() { 
		return sync.tryAcquire(1); 
	}
	
	public void unlock() {
		sync.release(1); 
	}
	
	public Condition newCondition() { 
		return sync.newCondition(); 
	}
	
	public boolean isLocked() { 
		return sync.isHeldExclusively(); 
	}
	
	public boolean hasQueuedThreads() { 
		return sync.hasQueuedThreads(); 
	}
	
	public void lockInterruptibly() throws InterruptedException {
		sync.acquireInterruptibly(1);
	}
	
	public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
		return sync.tryAcquireNanos(1, unit.toNanos(timeout));
	}
}
