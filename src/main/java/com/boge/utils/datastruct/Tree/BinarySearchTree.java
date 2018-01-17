package com.boge.utils.datastruct.Tree;

public class BinarySearchTree<T extends Comparable<? super T>> {

	private static class BinaryNode<T>{
		
		T element;
		
		BinaryNode<T> left;
		
		BinaryNode<T> right;
		
		public BinaryNode(){
			
		}

		public BinaryNode(T element, BinaryNode<T> left, BinaryNode<T> right) {
			this.element = element;
			this.left = left;
			this.right = right;
		}

		public BinaryNode(T element) {
			this.element = element;
		}
	}
	
	private BinaryNode<T> root;
	
	public BinarySearchTree(){} 
	
	
}
