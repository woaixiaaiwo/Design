package com.boge.algorithm;

import java.util.Arrays;


public class KMP {

	/**
	 * 
	 * 获取next数组(失去匹配时，模式串应该移动的位置)
	 * 其实就是计算，当前字符前面的字符，和从零开始的字符，有几个相同的
	 * 比如abaabac
	 * 初始状态都为-1和0
	 * 只考察c这个字符，其他字符都一样
	 * c前面的字符，以a开头的，但是不以0开头的最长的字符为aaba
	 * 再判断从0开头的有没有aaba，没有，再接着考察aba
	 * 发现从零开头的有aba
	 * 所以，当c适配时，前面的aba就不用再匹配了，直接从aba的
	 * 下一个字符，a开始匹配即可。此时，next[6]=3。
	 * 具体计算next数组过程如下：
	 * 算法理解:
	 * str是要求的字符串
	 * 我们用数学归纳法来解决这个填值的问题。
	 * 这里我们借鉴数学归纳法的三个步骤（或者说是动态规划？）：
	 * 1、初始状态
	 * 2、假设第j位以及第j位之前的我们都填完了
	 * 3、推论第j+1位该怎么填
	 * 
	 * 初始状态我们稍后再说，我们这里直接假设第j位以及第j位之前的我们都填完了。也就是说，我们有如下已知条件：
	 * 
	 * 定义 next[j] == k;
	 * 表示，str从0到k，即str[0...k-1] == str[j-k...j-1]
	 * 比如字符串ABCDABD,next[6]=2,那么str[0...1] = str[4...5];
	 * 
	 * 定义 next[k] = x;
	 * 同理；即str[0...x-1] == str[k-x...k-1]，这个串是除了str[0...k-1] == str[j-k...j-1]
	 * 外的更小的匹配的部分
	 * 
	 * 此时，我们计算next[j+1]
	 * 
	 * 1.如果str[k] == str[j]，即str[0...k] == str[j-k...j]，那么很显然，
	 * next[j+1] = next[j]+1;
	 * 
	 * 2.如果str[k] ！= str[j]，此时，找剩下的串中匹配的部分，上面我们定义了 next[k] = x，
	 * 说明str[0...x-1] == str[k-x...k-1]成立，所以我们将下标回溯到next[k]，即k = x，
	 * 由于str[k-x...k-1] = str[j-x...j-1],所以一定有str[0...x-1] = str[j-x...j-1]
	 * 此时，可以再重复1,2两个步骤
	 * 
	 * 可以参考https://www.cnblogs.com/tangzhengyue/p/4315393.html
	 * 
	 * 优化：
	 * 不该出现p[j] = p[ next[j] ]。为什么呢？理由是：当p[j] != s[i] 时，下次匹配必然是p[ next [j]] 跟s[i]匹配，如果p[j] = p[ next[j] ]，必然导致后一步匹配失败（因为p[j]已经跟s[i]失配，然后你还用跟p[j]等同的值p[next[j]]去跟s[i]匹配，很显然，必然失配），所以不能允许p[j] = p[ next[j] ]。如果出现了p[j] = p[ next[j] ]咋办呢？
	 * 如果出现了，则需要再次递归，即令next[j] = next[ next[j] ]。总结即是：如果a位字符与它的next值(即next[a])指向的b位字符相等（即p[a] == p[next[a]]）,则a位的next值就指向b位的next值即（next[ next[a] ]）。
	 * 
	 */
	private static int[] getNext(String str){
		
		char[] arr = str.toCharArray();
		int next[] = new int[str.length()];
		next[0] = -1;
		int k=-1;
		int j = 0;
		while(true){
			
			if(j == str.length()-1){
				break;
			}
			
			if(k == -1 || arr[k] == arr[j]){
				//先设置next值为k+1
				next[++j] = ++k;
				//优化：如果arr[next[j]] == arr[k] == arr[j]，再进行一次递归，即next[j] = next[next[j]] = next[k]。具体解释见上面的注释
				/*if(arr[k] == arr[j]){
					next[j] = next[k];
				}*/
			}else{
				k = next[k];
			}
			
		}
		return next;
	}
	
	
	public static int mathch(String text,String p){
		int[] next = getNext(p);
		int i=0,j = 0;
		while(true){
			if(j == -1 || text.charAt(i) == p.charAt(j)){
				i++;
				j++;
			}else{
				//如果j != -1，且当前字符匹配失败（即S[i] != P[j]），则令 i 不变，j = next[j]      
                //next[j]即为j所对应的next值，效果为进行回溯 
				j = next[j];
			}
			

			if(j == p.length()){
				return i-j;
			}
			
			if(i == text.length()-1 && j != p.length()){
				return -1;
			}
		}
		
		
	}
	
	public static void main(String[] args) {
		//System.out.println(mathch("abdjfhjababggff","abggd"));
		System.out.println(Arrays.toString(getNext("abacdababc")));
		System.out.println(Arrays.toString(getNext("aaaaaaaaaa")));
		System.out.println(Arrays.toString(getNext("abaabac")));
	}
}
