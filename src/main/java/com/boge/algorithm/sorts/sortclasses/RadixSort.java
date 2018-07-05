package com.boge.algorithm.sorts.sortclasses;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 用于字符串排序的基数排序
 * 思路：由于字符串都是小于256的ASC码，所以建立一个长度为256的数组
 * 里面的数据是一个List，每次读入一个字符，List就将该字符串放入，最后
 * 遍历数组即可。重复多次这个步骤即可完成排序 
 */
public class RadixSort {

	public static void main(String[] args) {
		
		String[] arr = {"abc","bac","acb","bca"};
		System.out.println(Arrays.toString(arr));
		radixSort(arr,3);
		System.out.println(Arrays.toString(arr));
	}
	
	private static void radixSort(String[] arr,int strLength){
		
		final int BUCKETS = 256;
		
		ArrayList<String>[] listArr = new ArrayList[BUCKETS];
		
		//初始化数组
		for(int i=0;i<BUCKETS;i++){
			listArr[i] = new ArrayList<>();
		}
		
		for(int i=strLength-1;i>=0;i--){
			for(int j=0;j<arr.length;j++){
				String s = arr[j];
				listArr[s.charAt(i)].add(s);
			}
			int index = 0;
			for(int k=0;k<BUCKETS;k++){
				for(String s:listArr[k]){
					arr[index++] = s;
				}
				listArr[k].clear();
			}
		}
		
	}
}
