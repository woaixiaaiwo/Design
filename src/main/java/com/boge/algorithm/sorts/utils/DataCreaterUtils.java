package com.boge.algorithm.sorts.utils;

import java.util.Random;

public class DataCreaterUtils {

	public static int[] createIntArr(int length,int max){
		
		int arr[] = new int[length];
		Random random = new Random();
		for(int i=0;i<length;i++){
			arr[i] = random.nextInt(max);
		}
		return arr;
	}
	
	public static boolean isSorted(int[] arr){
		
		for(int i=1;i<arr.length;i++){
			if(arr[i]<arr[i-1])return false;
		}
		return true;
	}
	
}
