package com.boge.algorithm.sorts.sortclasses;

import java.util.Arrays;

import com.boge.algorithm.sorts.utils.DataCreaterUtils;


public class MeargeSort implements BaseSort{
	
	
	
/*	public static void main(String[] args) {
		
		int[] arr = DataCreaterUtils.createIntArr(10,100);//{0,2,1,5,3,4};
		
		mSort(arr,0,arr.length-1);
		//meargeSort(arr,tem,0,5);
		
		System.out.println(Arrays.toString(arr));
		System.out.println(DataCreaterUtils.isSorted(arr));
		
	}*/
	
	public void sort(int[] arr){
		System.out.println("归并排序：");
		mSort(arr,0,arr.length-1);
	}
	
	private static void mSort(int[] arr,int begin,int end){
		if(begin == end)return;
		mSort(arr,begin,(begin+end)/2);
		mSort(arr,(begin+end)/2+1,end);
		meargeSort(arr,begin,end);
	}
	
	private static void meargeSort(int arr[],int begin,int end){
		

		
		int length = end-begin+1;
		
		int center = (begin+end)/2;
		
		int leftStart = begin;
		int leftEnd = center;
		
		int rightStart = leftEnd+1;
		int rightEnd = end;
		int i=0;
		
		int tmpArr[] = new int[length];
		
		while(leftStart<=leftEnd && rightStart<=rightEnd){
			if(arr[leftStart] < arr[rightStart]){
				tmpArr[i++] = arr[leftStart++];
			}else{
				tmpArr[i++] = arr[rightStart++];
			}
		}
		
		if(leftStart <= leftEnd){
			while(leftStart<=leftEnd){
				tmpArr[i++] = arr[leftStart++];
			}
		}else{
			while(rightStart<=rightEnd){
				tmpArr[i++] = arr[rightStart++];
			}
		}
		
		for(i=0;i<length;i++){
			arr[begin++] = tmpArr[i];
		}
		
	}
	
}
