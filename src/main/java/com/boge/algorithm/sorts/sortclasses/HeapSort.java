package com.boge.algorithm.sorts.sortclasses;

import java.util.Arrays;

public class HeapSort implements BaseSort{

	/*public static void main(String[] args) {
		
		int arr[] = TestDataCreater.createIntArr(10,100);
		System.out.println(Arrays.toString(arr));
		heapSort(arr);
		System.out.println(Arrays.toString(arr));
		
	}*/
	
	public void sort(int[] arr){
		
		int tmp;
		//先把堆建好
		buildMaxHeap(arr,arr.length);
		tmp = arr[0];
		arr[0] = arr[arr.length-1];
		arr[arr.length-1] = tmp;
		for(int i=arr.length-1;i>0;i--){
			//调整第一个数据即可
			adjustHeap(0,arr,i);
			tmp = arr[0];
			arr[0] = arr[i-1];
			arr[i-1] = tmp;
		}
		
	}
	
	private static void buildMaxHeap(int[] arr,int length){
		for(int i=length/2-1;i>=0;i--){
			adjustHeap(i,arr,length);
		}
	}
	
	/**
	 * 调整堆
	 */
	private static void adjustHeap(int hole,int[] arr,int end){
		int tmp = arr[hole];
		int child;
		
		while(true){
			if((child = hole*2+1) >= end)break;
			if(child+1 < end){
				child = arr[child]>arr[child+1]?child:child+1;
			}
			if(tmp < arr[child]){
				arr[hole] = arr[child];
				hole = child;
			}else{
				break;
			}
		}
		arr[hole] = tmp;
	}
}
