package com.boge.algorithm.sorts.sortclasses;

public class ShellSort implements BaseSort {
	
	public void sort(int[] arr){
		System.out.println("希尔排序：");
		int tmp,j,length;
		length = arr.length;
		for(int gap=length/2;gap>0;gap/=2){
			for(int i=gap;i<arr.length;i++){
				tmp = arr[i];
				for(j=i-gap;j>=0 && tmp<arr[j];j-=gap){
						arr[j+gap] = arr[j];
				}
				arr[j+gap] = tmp;
			}
		}
	}
}
