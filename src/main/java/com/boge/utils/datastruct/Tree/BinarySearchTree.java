package com.boge.utils.datastruct.Tree;



/**
 * 二叉查找树 
 */
public class BinarySearchTree<T extends Comparable<? super T>> {

	private static class BinaryNode<T>{
		
		T element;
		
		int times;
		
		BinaryNode<T> left;
		
		BinaryNode<T> right;
		
		public BinaryNode(){
			
		}

		public BinaryNode(T element, BinaryNode<T> left, BinaryNode<T> right) {
			this.element = element;
			this.times = 0;
			this.left = left;
			this.right = right;
		}

		public BinaryNode(T element) {
			this.element = element;
		}
	}
	
	private BinaryNode<T> root;
	
	public BinarySearchTree(){} 
	
	public void makeEmpty(){
		root = null;
	}
	
	public boolean isEmpty(){
		return root == null;
	}
	
	public boolean contains(T e){
		return contains(e, root);
	}
	
	public T findMin(){
		
		if(root == null){
			throw new RuntimeException("树为空!");
		}
		return findMin(root).element;
		
	}
	
	public T findMax(){
		
		if(root == null){
			throw new RuntimeException("树为空!");
		}
		return findMax(root).element;
		
	}
	
	public void insert(T e){
		
		root = insert(e, root);
		
	}
	
	public void remove(T e){
		root = remove(e, root);
	}
	
	public void printTree(){
		printTree(root);
	}
	
	private boolean contains(T e,BinaryNode<T> t){
		
		if(t == null)return false;
		
		int compareResult = e.compareTo(t.element);
		
		if(compareResult < 0){
			return contains(e,t.left);
		}else if(compareResult > 0){
			return contains(e,t.right);
		}else{
			return true;
		}
	}
	
	private BinaryNode<T> findMin(BinaryNode<T> t){
		
		if(t == null)
			return null;
		if(t.left == null)
			return t;
		
		return findMin(t.left);
		
	}
	
	private BinaryNode<T> findMax(BinaryNode<T> t){
		
		if(t != null){
			while(t.right != null){
				t = t.right;
			}
		}
		
		return t;
	}
	
	private BinaryNode<T> insert(T e,BinaryNode<T> t){
		
		if(t == null){
			return new BinaryNode<T>(e);
		}
		
		int compareResult = e.compareTo(t.element);
		
		if(compareResult < 0){
			t.left = insert(e, t.left);
		}else if(compareResult > 0){
			t.right = insert(e, t.right);
		}
		else{
			t.times = t.times+1;
		}
		
		return t;
	}
	
	private BinaryNode<T> remove(T e,BinaryNode<T> t){
		
		if(t == null)return t;
		
		int compareResult = e.compareTo(t.element);
		if(compareResult < 0){
			t.left = remove(e,t.left);
		}else if(compareResult > 0){
			t.right = remove(e,t.right);
		}else if(t.left != null && t.right != null){
			t.element = findMin(t.right).element;
			t.right = remove(t.element,t.right);
		}else{
			t = (t.left != null)?t.left:t.right;
		}
		
		return t;
	}
	
	private void printTree(BinaryNode<T> t){
		if(t != null){
			System.out.println(t.element);
			if(t.left != null){
				System.out.println(t.element+"的左孩子为:");
				printTree(t.left);
			}
			if(t.right != null){
				System.out.println(t.element+"的右孩子为:");
				printTree(t.right);
			}
		}
	}
}
