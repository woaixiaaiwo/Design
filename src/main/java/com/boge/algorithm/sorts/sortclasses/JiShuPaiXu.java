package com.boge.algorithm.sorts.sortclasses;

import java.util.Arrays;

/**
 * 用于字符串排序的计数基数排序
 * 思路：不使用ArrayList，使用两个数组，一个Count数组，用于表示
 * 数据的个数，一个offset数组，表示位置。
 * 如：abc,acb,cba,abc
 * count:a:1,b:1,c:2
 * offset:a:0,b:1,c:2
 * 此时，遍历数组，第一个元素是abc，根据offset数组，其排序后位置为 
 * 2,所以arr[2]=abc,此时offset[c]+1,count[c]减一。以此类推
 */
public class JiShuPaiXu {

	public static void main(String[] args) {
		
		String[] arr = {"012","021","120","210","201"};
		sort(arr,3);
		System.out.println(Arrays.toString(arr));
	}
	
	private static void sort(String[] arr,int length){
		
		int N = 60;
		int k;
		String[] buffer = new String[arr.length];
		
		int count[] = new int[N];
		int offset[] = new int[N];
		
		for(int i=length-1;i>=0;i--){
			for(int j=0;j<arr.length;j++){
				//生成count数组
				count[arr[j].charAt(i)]++;
			}
			//根据count数组，生成offset数组
			for(k=1;k<offset.length;k++){
				offset[k] = count[k]+offset[k-1];
				offset[k-1] = offset[k-1] - count[k-1];
			}
			offset[k-1] = offset[k-1] - count[k-1];
			
			for(int j=0;j<arr.length;j++){
				//设置数据
				if(count[arr[j].charAt(i)] > 0){
					buffer[offset[arr[j].charAt(i)]++] = arr[j];
					count[arr[j].charAt(i)]--;
				}
			}
			System.arraycopy(buffer,0, arr, 0, buffer.length);
			count = new int[N];
			offset = new int[N];
		}
		
	}
	
}
