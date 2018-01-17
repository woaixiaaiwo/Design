package com.boge.utils.datastruct.Tree;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

/**
 * 普通的树实现 
 */
public class NormalTree<T>{
	
	public static class TreeNode<T>{
		
		public T eleMent;
		
		public TreeNode<T> firstChirld;
		
		public TreeNode<T> nextSibling;
		
		public TreeNode(){}

		public TreeNode(T eleMent, TreeNode<T> firstChirld,
				TreeNode<T> nextSibling) {
			super();
			this.eleMent = eleMent;
			this.firstChirld = firstChirld;
			this.nextSibling = nextSibling;
		}
		
		
		
	}
	
	//根结点
	private TreeNode<T> root;
	
	public TreeNode<T> getRoot() {
		return root;
	}


	public void setRoot(TreeNode<T> root) {
		this.root = root;
	}

	NormalTree(T t){
		this.root = new TreeNode<>();
		root.eleMent = t;
	}
	
	public void xianxu(TreeNode<T> root,int deep){
		if(root != null){
			for(int i=0;i<deep;i++){
				System.out.print("——");
			}
			System.out.println(root.eleMent);
			xianxu(root.firstChirld,deep+1);
			xianxu(root.nextSibling,deep);
		}
		
	}
	
	public int houxu(TreeNode<UnixFile> root,int deep){
		int size = 0;
		if(root != null){
			if(root.eleMent.type == 1){
				size = root.eleMent.size;
				TreeNode<UnixFile> firstChildNode = root.firstChirld;
				while(firstChildNode != null){
					size = size+houxu(firstChildNode,deep+1);
					firstChildNode = firstChildNode.nextSibling;
				}
			}else{
				size = root.eleMent.size;
			}
			for(int i=0;i<deep;i++){
				System.out.print("——");
			}
			System.out.println(root.eleMent.name+":"+size);
		}
		return size;
	}
	
	public static void buildTree(TreeNode<UnixFile> root,UnixFile bro){
		
		List<UnixFile> list = root.eleMent.childs;
		if(CollectionUtils.isNotEmpty(list)){
			root.firstChirld = new TreeNode<UnixFile>(list.get(0),null,null);
			if(bro == null)root.nextSibling = null;
			else root.nextSibling = new TreeNode<UnixFile>(bro,null,null);
			
			TreeNode<UnixFile> paramRoot = root.firstChirld;
			
			for(int i=0;i<list.size();i++){
				UnixFile brother = null;
				if(i+1 <= list.size()-1)brother = list.get(i+1);
				buildTree(paramRoot,brother);
				paramRoot = paramRoot.nextSibling;
			}
			
		}else{
			root.firstChirld = null;
			if(bro == null)root.nextSibling = null;
			else root.nextSibling = new TreeNode<UnixFile>(bro,null,null);
		}
		
	}

	public static void main(String[] args) {
		
		NormalTree<UnixFile> tree = new NormalTree<UnixFile>(createData());
		
		TreeNode<UnixFile> root = tree.getRoot();
		
		buildTree(root, null);
		
		tree.houxu(root,0);
	}
	
	
	private static UnixFile createData(){

		
		UnixFile ch1 = new UnixFile("ch1.r",0,3,null);
		UnixFile ch2 = new UnixFile("ch2.r",0,2,null);
		UnixFile ch3 = new UnixFile("ch3.r",0,4,null);
		UnixFile book = new UnixFile("book",1,1,Arrays.asList(ch1,ch2,ch3));
		
		
		UnixFile syl1 = new UnixFile("syl.r",0,1,null);
		UnixFile fall = new UnixFile("fall05",1,1,Arrays.asList(syl1));
		
		UnixFile syl2 = new UnixFile("syl.r",0,5,null);
		UnixFile spr = new UnixFile("spr06",1,1,Arrays.asList(syl2));
		
		UnixFile syl3 = new UnixFile("syl.r",0,2,null);
		UnixFile sum = new UnixFile("sum06",1,1,Arrays.asList(syl3));
		
		UnixFile cop3530 = new UnixFile("cop3530",1,1,Arrays.asList(fall,spr,sum));
		UnixFile course = new UnixFile("course",1,1,Arrays.asList(cop3530));
		

		UnixFile junk = new UnixFile("junk",0,6,null);
		UnixFile mark = new UnixFile("mark",1,1,Arrays.asList(book,course,junk));
		//UnixFile mark = new UnixFile("mark",1,1,Arrays.asList(book));
		
		UnixFile junk2 = new UnixFile("junk",0,8,null);
		UnixFile alex = new UnixFile("alex",1,1,Arrays.asList(junk2));
		
		UnixFile grades = new UnixFile("grades",0,3,null);
		UnixFile prog1 = new UnixFile("prog1.r",0,4,null);
		UnixFile prog2 = new UnixFile("prog2.r",0,1,null);
		UnixFile fall2 = new UnixFile("fall05",1,1,Arrays.asList(grades,prog1,prog2));
		
		UnixFile prog3 = new UnixFile("prog2.r",0,2,null);
		UnixFile prog4 = new UnixFile("prog1.r",0,7,null);
		UnixFile grades2 = new UnixFile("grades",0,9,null);
		UnixFile fall3 = new UnixFile("fall06",1,1,Arrays.asList(prog3,prog4,grades2));
		
		UnixFile cop3212 = new UnixFile("cop3212",1,1,Arrays.asList(fall2,fall3));
		UnixFile course2 = new UnixFile("course",1,1,Arrays.asList(cop3212));
		UnixFile work = new UnixFile("work",0,1,null);
		UnixFile bill = new UnixFile("bill",1,1,Arrays.asList(work,course2));
		
		UnixFile usr = new UnixFile("usr",1,1,Arrays.asList(mark,alex,bill));
		
		//UnixFile usr = new UnixFile("usr",1,1,Arrays.asList(mark));
		
		return usr;
	}

}
class UnixFile{
	
	public String name;
	
	public int type;
	
	public int size;
	
	public UnixFile(String name, int type, int size, List<UnixFile> childs) {
		super();
		this.name = name;
		this.type = type;
		this.size = size;
		this.childs = childs;
	}

	@Override
	public String toString() {
		return name;
	}

	public List<UnixFile> childs;
	
}
