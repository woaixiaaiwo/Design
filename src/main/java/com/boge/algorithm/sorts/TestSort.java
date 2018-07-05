package com.boge.algorithm.sorts;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.boge.algorithm.sorts.sortclasses.BaseSort;
import com.boge.algorithm.sorts.sortclasses.HeapSort;
import com.boge.algorithm.sorts.sortclasses.InsertSort;
import com.boge.algorithm.sorts.sortclasses.MeargeSort;
import com.boge.algorithm.sorts.sortclasses.QuickSort;
import com.boge.algorithm.sorts.sortclasses.ShellSort;
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
		
		int eleNum = 300000;
		int maxNum = 10000000;
		
		int[] arr = DataCreaterUtils.createIntArr(eleNum,maxNum);
		BaseSort heapSort = (BaseSort) SortProxy.getProxy(new HeapSort());
		heapSort.sort(arr);
		
		arr = DataCreaterUtils.createIntArr(eleNum,maxNum);
		heapSort = (BaseSort) SortProxy.getProxy(new MeargeSort());
		heapSort.sort(arr);
		
		arr = DataCreaterUtils.createIntArr(eleNum,maxNum);
		heapSort = (BaseSort) SortProxy.getProxy(new ShellSort());
		heapSort.sort(arr);
		
		arr = DataCreaterUtils.createIntArr(eleNum,maxNum);
		heapSort = (BaseSort) SortProxy.getProxy(new QuickSort());
		heapSort.sort(arr);
		
		arr = DataCreaterUtils.createIntArr(eleNum,maxNum);
		List<Integer> list = new ArrayList<Integer>();
		for(int i=0;i<arr.length;i++){
			list.add(arr[i]);
		}
		Long start = System.currentTimeMillis();
		Collections.sort(list);
		Long end = System.currentTimeMillis();
		System.out.println("系统排序:");
		System.out.println("排序成功!耗时:"+(end-start));
		/*arr = DataCreaterUtils.createIntArr(eleNum,maxNum);
		heapSort = (BaseSort) SortProxy.getProxy(new InsertSort());
		heapSort.sort(arr);*/
		
		/*BaseSort heapSort = (BaseSort) SortProxy.getProxy(new InsertSort());
		heapSort.sort(arr);*/
		
		/*BaseSort heapSort = (BaseSort) SortProxy.getProxy(new ShellSort());
		heapSort.sort(arr);*/
	}
	
}
