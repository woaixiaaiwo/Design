package com.boge.designs.adapter.old.abstracts;

/**
 * 球员 
 */
public abstract class Player {

	protected String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Player(String name) {
		super();
		this.name = name;
	}
	
	public Player() {
	}
	
	public abstract void attack();
	
	public abstract void defend();
	
}
