package com.boge.algorithm.sorts;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.boge.algorithm.sorts.sortclasses.BaseSort;
import com.boge.algorithm.sorts.sortclasses.HeapSort;
import com.boge.algorithm.sorts.utils.DataCreaterUtils;

public class TestSort {

	private static class SortProxy implements InvocationHandler{

		public static Object getProxy(Object obj){
			SortProxy sortProxy = new SortProxy(obj);
			ClassLoader classLoader = obj.getClass().getClassLoader();
			Class<?>[] interfaces = obj.getClass().getInterfaces();
			return Proxy.newProxyInstance(classLoader, interfaces, sortProxy);
		}
		
		private Object obj;
		
		SortProxy(Object obj){
			this.obj = obj;
		}
		
		@Override
		public Object invoke(Object proxy, Method method, Object[] args)
				throws Throwable {
			// TODO Auto-generated method stub
			
			Long start = System.currentTimeMillis();
			method.invoke(obj,args);
			Long end = System.currentTimeMillis();
			if(DataCreaterUtils.isSorted((int[])args[0])){
				System.out.println("排序成功!耗时:"+(end-start));
			}
			return null;
		}
		
	}
	
	public static void main(String[] args) {
		
		int[] arr = DataCreaterUtils.createIntArr(10,10000000);
		BaseSort heapSort = (BaseSort) SortProxy.getProxy(new HeapSort());
		heapSort.sort(arr);
		
		/*BaseSort heapSort = (BaseSort) SortProxy.getProxy(new InsertSort());
		heapSort.sort(arr);*/
		
		/*BaseSort heapSort = (BaseSort) SortProxy.getProxy(new ShellSort());
		heapSort.sort(arr);*/
	}
	
}
