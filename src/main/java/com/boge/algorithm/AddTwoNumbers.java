package com.boge.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给定两个非空链表来代表两个非负数，位数按照逆序方式存储，它们的每个节点只存储单个数字。将这两数相加会返回一个新的链表。
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807 
 */

class ListNode {
	 int val;
	 ListNode next;
	 ListNode(int x) {val = x;}
	 
	 public void print(){
		 ListNode listNode = this;
		 while(listNode != null){
			 System.out.println(listNode.val+"->");
			 listNode = listNode.next;
		 }
	}

	@Override
	public String toString() {
		return "ListNode [val=" + val + ", next=" + next + "]";
	}
	 
	 
}
public class AddTwoNumbers {
	
	/*public static void main(String[] args) {
		ListNode l1 = new ListNode(1);
		l1.next = new ListNode(8);
		//l1.next.next = new ListNode(3);
		
		ListNode l2 = new ListNode(0);
		//l2.next = new ListNode(6);
		//l2.next.next = new ListNode(4);
		//l2.next.next.next = new ListNode(8);
		
		addTwoNumbers(l1,l2).print();
	}*/
	
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		
		//List<ListNode> list = new ArrayList<>();
		int length1 = getListLength(l1);
		int length2 = getListLength(l2);
		ListNode tem = null;
		if(length1>length2){
			tem = l1;
			l1 = l2;
			l2 = tem;
		}
		
		int sum = l1.val+l2.val;
		boolean isCarry = false;
		if(sum >= 10){
			sum = sum%10;
			isCarry = true;
		}
		ListNode head = new ListNode(sum);
		l1 = l1.next;
		l2 = l2.next;
		if(l2 != null){
			head.next = new ListNode(0);
		}
		ListNode end = head.next;
		while(l2 != null){
			if(l1 != null){
				sum = l1.val+l2.val;
				l1 = l1.next;
			}else{
				sum = l2.val;
			}
			l2 = l2.next;
			if(isCarry){
				sum = sum+1;
				isCarry = false;
			}
			if(sum >= 10){
				sum = sum%10;
				isCarry = true;
			}
			end.val = sum;
			if(l2 != null){
				end.next = new ListNode(0);
				end = end.next;
			}
		}
		if(isCarry){
			if(end == null){
				head.next = new ListNode(1);
			}else{
				end.next = new ListNode(0);
				end = end.next;
				end.val = 1;
			}
		}
		return head;
    }
	
	public static int getListLength(ListNode l1){
		int length = 0;
		while(l1!=null){
			length++;
			l1 = l1.next;
		}
		return length;
	}
	
	
	public static void main(String sgf[]) {
		StringBuffer a=new StringBuffer("A");
		StringBuffer b=new StringBuffer("B");
		operate(a,b);
		System.out.println(a+"."+b);
	}
	static void operate(StringBuffer x,StringBuffer y) {
		x.append(y);
		System.out.println(x);
		System.out.println(y);
		y=x;
		System.out.println(x);
		System.out.println(y);
	}
}
