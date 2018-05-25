package com.boge.concurrency.threadpools;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * 使用线程池实现并行计算
 * 
 * 只有当计算的开销比较大，或者需要及时返回数据时才适用该方法，
 * 其他情况下，增强for的性能更强
 */
public class ComputeExcutorServiceTest {

	public static void main(String[] args) throws InterruptedException {
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		List<String> list = new ArrayList<>();
		for(int i=0;i<10000;i++){
			list.add(i+"");
		}
		Collection<String> newList = compute(executorService, list);
		executorService.shutdown();
		executorService.awaitTermination(Long.MAX_VALUE,TimeUnit.SECONDS);
	}
	
	public static Collection compute(ExecutorService exec,List<String> data){
		
		final Collection<String> res = new ConcurrentLinkedQueue<String>();
		
		for(final String d:data){
			exec.execute(new Runnable() {
				
				@Override
				public void run() {
					// TODO Auto-generated method stub
					res.add(d.toUpperCase());
				}
			});
		}
		
		return res;
	}
	
public static List compute(List<String> data){
		
		final List<String> res = new ArrayList<String>();
		
		for(final String d:data){
			res.add(d.toUpperCase());
		}
		
		return res;
	}
}

