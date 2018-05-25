package com.boge.algorithm;

public class IntegerIsAPalindrome {
	
	public static boolean isPalindrome(int x) {
		if(x<0){
			return false;
		}
		
		String d = String.valueOf(x);
		
		int i=0;
		int j=d.length()-1;
		
		while(i<j){
			if(d.charAt(i++) != d.charAt(j--)) return false;
		}
		
		return true;
        
    }
	
	public static void main(String[] args) {
		System.out.println(isPalindrome(11));
	}

}
