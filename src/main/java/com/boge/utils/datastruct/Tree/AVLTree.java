package com.boge.utils.datastruct.Tree;

import java.util.HashMap;

/**
 * 平衡二叉查找树(AVL树) 
 */
public class AVLTree<T extends Comparable<? super T>>{
	
	//左右子树允许相差的高度值，高度差大于该值时需要调整
	private static final int ALLOWED_IMBALANCE = 1;
	
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
			this.height = 0;
		}
	}
	
	private AVLNode<T> root;
	
	public void insert(T e){
		
		root = insert(e, root);
		
	}
	
	public void remove(T e){
		
		root = remove(e, root);
		
	}
	
	public void printTree(){
		printTree(root);
	}
	
	private AVLNode<T> insert(T e,AVLNode<T> t){
		
		if(t == null){
			return new AVLNode<T>(e);
		}
		
		int compareResult = e.compareTo(t.element);
		
		if(compareResult < 0){
			t.left = insert(e, t.left);
		}else if(compareResult > 0){
			t.right = insert(e, t.right);
		}
		else{
			System.out.println("dosomething...");
		}
		
		return balanceNode(t);
	}
	
	private int calculateHeight(AVLNode<T> t){
		return t == null?-1:t.height;
	}

	private AVLNode<T> balanceNode(AVLNode<T> t){
		
		if(t== null)return t;
		
		//判断该结点是否平衡
		int leftHeight = calculateHeight(t.left);
		int rightHeight = calculateHeight(t.right);
		
		//左子树不平衡
		if(leftHeight-rightHeight > ALLOWED_IMBALANCE){
			//左子树的左子树高度大于等于右子树高度，说明插入发生在左子树，即左-左的情况，进行左单旋转
			//这里的等于情况，可能发生在删除元素的情况下，考虑5-2-6-1-3这棵树，5为根节点，2为左孩子结点，6为右
			//孩子节点，此时移除6，变成5-2-1-3,1和3在同一高度，要进行一次单旋转即可恢复
			if(calculateHeight(t.left.left) >= calculateHeight(t.left.right)){
				t = leftSignalRotate(t);
			}
			//左子树的左子树高度小于右子树高度，说明插入发生在右子树，即左-右的情况，进行左双旋转
			else{
				t = leftDoubleRotate(t);
			}
		}
		
		//右子树不平衡
		if(rightHeight-leftHeight > ALLOWED_IMBALANCE){
			//右子树的右子树高度大于等于左子树高度，说明插入发生在右子树，即右-右的情况，进行右单旋转
			if(calculateHeight(t.right.right) >= calculateHeight(t.right.left)){
				t = rightSignalRotate(t);
			}
			//右子树的右子树高度小于左子树高度，说明插入发生在左子树，即右-左的情况，进行右双旋转
			else{
				t = rightDoubleRotate(t);
			}
		}
		//设置高度
		t.height = Math.max(calculateHeight(t.left),calculateHeight(t.right))+1;
		
		return t;
	}
	
	private AVLNode<T> remove(T e,AVLNode<T> t){
		
		if(t == null)return t;
		
		int compareResult = e.compareTo(t.element);
		if(compareResult < 0){
			t.left = remove(e,t.left);
		}else if(compareResult > 0){
			t.right = remove(e,t.right);
		}else if(t.left != null && t.right != null){
			//左右孩子都存在的情况，找右孩子中的最小值
			t.element = findMin(t.right).element;
			//递归移除该最小值
			t.right = remove(t.element,t.right);
		}else{
			t = (t.left != null)?t.left:t.right;
		}
		
		return balanceNode(t);
	}
	
	private AVLNode<T> findMin(AVLNode<T> t){
		
		if(t == null)
			return null;
		if(t.left == null)
			return t;
		
		return findMin(t.left);
		
	}
	
	private AVLNode<T> findMax(AVLNode<T> t){
		
		if(t != null){
			while(t.right != null){
				t = t.right;
			}
		}
		
		return t;
	}
	
	private void printTree(AVLNode<T> t){
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
	
	/**
	 * 左单旋转 
	 */
	private AVLNode<T> leftSignalRotate(AVLNode<T> k1){
		AVLNode<T> k2 = k1.left;
		k1.left = k2.right;
		k2.right = k1;
		k1.height = Math.max(calculateHeight(k1.left),calculateHeight(k1.right))+1;
		k2.height = Math.max(calculateHeight(k2.left),calculateHeight(k1))+1;
		return k2;
	}
	
	/**
	 * 右单旋转 
	 */
	private AVLNode<T> rightSignalRotate(AVLNode<T> k1){
		AVLNode<T> k2 = k1.right;
		k1.right = k2.left;
		k2.left = k1;
		k1.height = Math.max(calculateHeight(k1.left),calculateHeight(k1.right))+1;
		k2.height = Math.max(calculateHeight(k2.right),calculateHeight(k1))+1;
		return k2;
	}
	
	/**
	 * 左双旋转 
	 */
	private AVLNode<T> leftDoubleRotate(AVLNode<T> k1){
		//先进行左孩子结点的右单旋转
		k1.left = rightSignalRotate(k1.left);
		return leftSignalRotate(k1);
	}
	
	/**
	 * 右双旋转旋转 
	 */
	private AVLNode<T> rightDoubleRotate(AVLNode<T> k1){
		//先进行右孩子结点的左单旋转
		k1.right = leftSignalRotate(k1.right);
		return rightSignalRotate(k1);
	}
	
	
	public static void main(String[] args) {
		
		/*BinarySearchTree<Integer> tree = new BinarySearchTree<>();
		tree.insert(8);
		tree.insert(3);
		tree.insert(2);
		tree.insert(5);
		tree.insert(1);
		tree.insert(9);
		tree.printTree();*/
		
		AVLTree<Integer> tree1 = new AVLTree<>();
		tree1.insert(2);
		tree1.insert(1);
		tree1.insert(4);
		tree1.insert(3);
		tree1.insert(5);
		tree1.printTree();
		System.out.println("-----------------------");
		tree1.remove(1);
		tree1.printTree();
	}
}
