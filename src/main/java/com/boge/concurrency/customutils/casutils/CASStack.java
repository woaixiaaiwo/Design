package com.boge.concurrency.customutils.casutils;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 无阻塞算法实现栈 
 */
public class CASStack<E> {

	private AtomicReference<Node<E>> top = new AtomicReference<Node<E>>();
	
	
	public void push(E e){
		Node<E> newNode = new Node<E>(e);
		Node<E> oldTop = null;
		while (true) {
			//获取旧的top值
			oldTop = top.get();
			//链接到新top值
			newNode.next = oldTop;
			//如果成功，跳出循环
			if(top.compareAndSet(oldTop, newNode)){
				break;
			}
		}
	}
	
	public E pop(){
		Node<E> newTop;
		Node<E> oldTop;
		while (true) {
			if(top.get() == null){
				return null;
			}
			oldTop = top.get();
			newTop = oldTop.next;
			//如果成功，跳出循环
			if(top.compareAndSet(oldTop, newTop)){
				return oldTop.item;
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	private class Node<E>{
		
		public final E item;
		
		public Node<E> next;

		public Node(E item) {
			this.item = item;
		}
	}
}
