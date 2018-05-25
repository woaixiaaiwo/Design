package com.boge.algorithm;

public class MaxSum {
	
	public static int getMaxSum(int[] arr){
		
		int maxNum = 0;
		int currentMaxNum = 0;
		int oldBegin=0,newBegin=0;
		int end=0;
		for(int i=0;i<arr.length;i++){
			if(currentMaxNum+arr[i] < 0){
				currentMaxNum = 0;
				newBegin = i+1;
			}else{
				currentMaxNum = currentMaxNum+arr[i];
				if(maxNum < currentMaxNum){
					oldBegin = newBegin;
					maxNum = currentMaxNum;
					end = i;
				}
			}
		}
		System.out.println(oldBegin);
		System.out.println(end);
		return maxNum;
		
	}
	
	public static int get(int n){
		if(n == 1)return 1;
		if(n == 2)return 2;
		int num = 0;
		int a=1;
		int b=2;
		for(int i=3;i<=n;i++){
			if(i % 2 ==0){
				num = a*b;
			}else{
				num = a+b;
			}
			a = b;
			b = num;
		}
		return num;
	}
	
	public static int getN(int n){
		if(n == 1)return 1;
		if(n == 2)return 2;
		if(n % 2 ==0){
			return getN(n-1)*getN(n-2);
		}else{
			return getN(n-1)+getN(n-2);
		}
	}
	
	public static void main(String[] args) {
		System.out.println(getN(5));
	}
}
