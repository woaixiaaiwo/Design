package com.boge.algorithm.sorts.sortclasses;

import java.util.Arrays;

import com.boge.algorithm.sorts.utils.DataCreaterUtils;


public class QuickSort implements BaseSort{
	
	
	
	public static void main(String[] args) {
		
		int[] arr = DataCreaterUtils.createIntArr(10,100);
		
		System.out.println(Arrays.toString(arr));
		qSort(arr,0,arr.length-1);
		
		System.out.println(Arrays.toString(arr));
		System.out.println(DataCreaterUtils.isSorted(arr));
		
	}
	
	public void sort(int[] arr){
		System.out.println("快速排序：");
		qSort(arr,0,arr.length-1);
	}
	
	private static void qSort(int[] arr,int begin,int end){
		if(begin < end){
			int center = quickSort(arr,begin,end);
			qSort(arr,begin,center);
			qSort(arr,center+1,end);
		}
	}
	
	private static int quickSort(int arr[],int begin,int end){
		
		//默认枢纽元为第一个元素
		int tmp = arr[begin];
		int i=begin,j=end;
		while(i < j){
			while(arr[j]>=tmp && i<j){
				j--;
			}
			swap(arr, i, j);
			while(arr[i]<=tmp && i<j){
				i++;
			}
			swap(arr, i, j);
		}
		return i;
	}
	
	private static void swap(int arr[],int index1,int index2){
		int tmp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = tmp;
	}
}
