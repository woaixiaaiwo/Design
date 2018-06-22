package com.boge.algorithm.sorts.sortclasses;

import java.util.Arrays;


public class MeargeSort implements BaseSort {
	
	public void sort(int[] arr){
	}
	
	public static void main(String[] args) {
		
		int[] arr = {1,2,0,3,1,4};
		
		int[] tem = new int[arr.length];
		
		meargeSort(arr,tem,0,3);
		
		System.out.println(Arrays.toString(arr));
		
	}
	
	private static void mSort(int[] arr,int tmpArr[]){
		
		
		
	}
	
	private static void meargeSort(int arr[],int tmpArr[],int begin,int end){
		

		
		int length = end-begin+1;
		
		int center = length/2;
		
		int leftStart = begin;
		int leftEnd = center>begin?center-1:begin;
		
		int rightStart = leftEnd+1;
		int rightEnd = end;
		int i=0;
		
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
