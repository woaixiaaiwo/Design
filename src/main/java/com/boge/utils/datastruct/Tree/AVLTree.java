package com.boge.utils.datastruct.Tree;
/**
 * 平衡二叉查找树(AVL树) 
 */
public class AVLTree {
	
	private static class AVLNode<T>{
		
		T element;
		
		int height;
		
		AVLNode<T> left;
		
		AVLNode<T> right;
		
		public AVLNode(){
			
		}

		public AVLNode(T element, AVLNode<T> left, AVLNode<T> right) {
			this.element = element;
			this.height = 0;
			this.left = left;
			this.right = right;
		}

		public AVLNode(T element) {
			this.element = element;
		}
	}

}
