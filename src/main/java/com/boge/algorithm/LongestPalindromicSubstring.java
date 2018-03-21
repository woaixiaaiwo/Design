package com.boge.algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;


/**
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 长度最长为1000。
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba"也是有效答案 
 */
public class LongestPalindromicSubstring {
	
	public static String longestPalindrome(String s) {
		//上一个相连字符串的最后一个索引值，寄abba，lastCoutinue为2。主要用来区分相连字符串与不想连字符串
		int lastCoutinue = 0;
		//相连字符串的长度，如果相连字符串是分隔符，则要记录该长度。如abba，相连字符串长度为2，即bb
		int splitLength = 0;
		StringBuilder sb = new StringBuilder();
		List<String> list = new ArrayList<String>();
		//前一个索引
		int mid = 0;
		//后一个索引
		int index = 0;
		int i = 1;
		while(true){
			if(i >= s.length()){
				break;
			}
			//先判断相邻字符是否相等
			if(s.charAt(mid) == s.charAt(i) && mid == i-1){
				if(sb.length() == 0){
					sb.append(s.charAt(mid));
				}
				sb.append(s.charAt(i));
				//前后索引值都增加
				mid++;
				i++;
				lastCoutinue = i;
			}else{
				//判断上一步是不是相连的字符串
				if(i == lastCoutinue){
					if(sb.length() != 0){
						list.add(sb.toString());
						splitLength = sb.length()-1;
					}
					sb = new StringBuilder();
				}
				//获取当前索引的对称索引，如abbba，splitLength为2，mid为3，i为4，此时它的对称索引应该是2*3-4-2=0;
				index = 2*mid-i-splitLength;
				if(index >= 0){
					if(s.charAt(index) == s.charAt(i)){
						if(sb.length() == 0){
							//要加上相连字符串的长度
							for(int split=0;split<splitLength+1;split++){
								sb.append(s.charAt(mid));
							}
						}
						sb.insert(0,s.charAt(index));
						sb.append(s.charAt(i));
						i++;
					}else{
						//前索引加1
						mid = mid+1;
						//后索引为前索引加1
						i = mid+1;
						if(sb.length() != 0){
							list.add(sb.toString());
						}
						sb = new StringBuilder();
						splitLength = 0;
					}
				}else{
					//前索引加1
					mid = mid+1;
					//后索引为前索引加1
					i = mid+1;
					if(sb.length() != 0){
						list.add(sb.toString());
					}
					sb = new StringBuilder();
					splitLength = 0;
				}
			}
			
			
		}
			

		if(sb.length() != 0){
			list.add(sb.toString());
		}
		System.out.println(list);
		return s;
    }
	
	public static String longestPalindrome1(String s) {
		
		if(s == null || s.length() == 0){
			return "";
		}
		
		int lastCoutinue = 0;
		int splitLength = 0;
		StringBuilder sb = new StringBuilder();
		StringBuilder res = new StringBuilder();
		int mid = 0;
		int index = 0;
		int i = 1;
		while(true){
			if(i >= s.length()){
				break;
			}
			//先判断相邻字符是否相等
			if(s.charAt(mid) == s.charAt(i) && mid == i-1){
				if(sb.length() == 0){
					sb.append(s.charAt(mid));
				}
				sb.append(s.charAt(i));
				mid++;
				i++;
				lastCoutinue = i;
			}else{
				if(i == lastCoutinue){
					if(sb.length() > 0){
						if(sb.length() > res.length()){
							res = new StringBuilder(sb.toString());
						}
						splitLength = sb.length()-1;
					}
					sb = new StringBuilder();
				}
				index = 2*mid-i-splitLength;
				if(index >= 0){
					if(s.charAt(index) == s.charAt(i)){
						if(sb.length() == 0){
							for(int split=0;split<splitLength+1;split++){
								sb.append(s.charAt(mid));
							}
						}
						sb.insert(0,s.charAt(index));
						sb.append(s.charAt(i));
						i++;
					}else{
						mid = mid+1;
						i = mid+1;
						if(sb.length() > res.length()){
							res = new StringBuilder(sb.toString());
						}
						sb = new StringBuilder();
						splitLength = 0;
					}
				}else{
					mid = mid+1;
					i = mid+1;
					if(sb.length() > res.length()){
						res = new StringBuilder(sb.toString());
					}
					sb = new StringBuilder();
					splitLength = 0;
				}
			}
			
			
		}
		if(sb.length() > res.length()){
			res = new StringBuilder(sb.toString());
		}
		if(res.length() == 0){
			return s.charAt(0)+"";
		}
		return res.toString();
    }
	
	/**
	 * 求最大回文串的贪婪算法 
	 */
	public static String longestPalindrome2(String s) {
	    char[] ca = s.toCharArray();
	    int rs = 0, re = 0;
	    //已经获取到的最大回文串的长度
	    int max = 0;
	    for(int i = 0; i < ca.length; i++) {
	    	//注意的i-max-1到i,是比当前max值大2的长度。如果有这样的回文串，max直接加2
	        if(isPalindrome(ca, i - max - 1, i)) {
	            rs = i - max - 1; re = i;
	            max += 2;
	        } 
	        //注意的i-max到i,是比当前max值大1的长度。如果有这样的回文串，max直接加1
	        else if(isPalindrome(ca, i - max, i)) {
	            rs = i - max; re = i;
	            max += 1;
	        }
	    }
	    return s.substring(rs, re + 1);
	}

	//判断是否是回文串
	private static boolean isPalindrome(char[] ca, int s, int e) {
	    if(s < 0) return false;
	    
	    while(s < e) {
	        if(ca[s++] != ca[e--]) return false;
	    }
	    return true;
	}
	
	
	
	public static void main(String[] args) {
		String a = "ajjaddjjjajjj";
		System.out.println(longestPalindrome2(a));
	}

}
