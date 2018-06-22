package com.boge.algorithm.sorts.sortclasses;

import java.util.Arrays;

public class InsertSort implements BaseSort{
	
	public void sort(int[] arr){
		int tmp,i,j;
		for(i=1;i<arr.length;i++){
			tmp = arr[i];
			for(j=i-1;j>=0 && tmp<arr[j];j--){
					arr[j+1] = arr[j];
			}
			arr[++j] = tmp;
		}
		
	}
	
}
