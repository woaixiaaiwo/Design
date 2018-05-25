package com.boge.algorithm;

import java.util.Random;

public class MaxArrayData {

	public static void main(String[] args) {
		
		int arr[] = new int[50];
		Random random = new Random();
		for(int i=0;i<arr.length;i++){
			arr[i] = random.nextInt()%20+1;
		}
		
	}
	
	
	//有一个长度为50的数组，里面的数都是1-20.设计一个算法 将重复次数最多的那个数找出来，算法复杂度要求O(n).不能使用辅助空间。
	//思路：使用前20个位置，先计算20-39中1-20的个数，如前三个数为4,4,3,4映射到位置3，如果a[3]=6,那么新的a[3]的值为6+100*1。
	//之后又是一个4，那么a[3]的值为6+100*2。以此类推。20-39算完后，获得了a[0]-a[19]的一个新的数组，
	//将a[3]的值设置为206/100，为2，说明20-39中4重复了2次，再通过206%100还原a[3]的值，并将a[20]-a[39]的值设置为a[0]-a[19]的值
	//此时a[0]-a[19]的值就是每个数重复的次数了，然后再算出a[20]-a[49]的重复次数就很简单了。
	public static int getMaxData(int[] arr){
		return 0;
	}
	
}
