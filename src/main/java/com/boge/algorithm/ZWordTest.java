package com.boge.algorithm;

/**
 */
public class ZWordTest {

	public static void main(String[] args) {
		
		String arr = "PAYPALISHIRING0";
		System.out.println(convert(arr,4));
	}
	
	public static String convert(String s, int numRows) {
		int width = getWidth(s.length(),numRows);
		char[][] arr = zWord(s,numRows,width);
		return getRes(arr,width,numRows).toString();
    }
	
	private static char[][] zWord(String str,int height,int width){
		char[] arr = str.toCharArray();
		
		if(height == 1) {
			char[][] newArr = new char[1][str.length()];
			for(int i=0;i<arr.length;i++) {
				newArr[0][i] = arr[i];
			}
			return newArr;
		}
		char[][] newArr = new char[height][width];
		int[] index = {0,0};
		int z = 0;
		boolean isDown = false,isUp = false;
		while(z<arr.length) {
			if(index[0] == 0) {
				isDown = true;
				isUp = false;
			}else if(index[0] == height-1) {
				isDown = false;
				isUp = true;
			}
			if(isDown) {
				down(arr[z], newArr, index);
			}else if(isUp) {
				up(arr[z], newArr, index);
			}
			z++;
		}
		return newArr;
	}
	
	private static void down(char data,char[][] arr,final int[] index) {
		int i= index[0],j=index[1];
		arr[i][j] = data;
		index[0]++;
	}
	
	private static void up(char data,char[][] arr,final int[] index) {
		int i= index[0],j=index[1];
		arr[i][j] = data;
		index[0]--;
		index[1]++;
	}
	
	private static StringBuilder getRes(char[][] arr,int width,int height) {
		
		StringBuilder sb = new StringBuilder();
		
		for(int i=0;i<height;i++) {
			for(int j=0;j<width;j++) {
				if(Integer.valueOf(arr[i][j]) != 0) {
					sb.append(arr[i][j]);
				}
			}
		}
		return sb;
	}
	
	private static int getWidth(int length,int height) {
		
		if(height == 1)return length;
		//根据height获取周期数
		int cycle = height*2-2;
		//一个周期占的长度
		int cycleLegth = height-1;
		
		//判断满不满一周期
		int mod = length/cycle;
		if(mod == 0) {
			if(length <= height) {
				return 1;
			}
			return 1+length-height;
		}else {
			int res = length/cycle*cycleLegth;
			if(length % cycle == 0)return res;
			if(length % cycle <= height) {
				res = res+1;
			}else {
				res = res+(length % cycle - height + 1);
			}
			return res;
		}
	}
	

	
	private static void printArr(char[][] arr,int width,int height) {
	
		for(int i=0;i<height;i++) {
			for(int j=0;j<width;j++) {
				System.out.print(arr[i][j]);
				System.out.print("	");
			}
			System.out.println();
		}
	}
}
