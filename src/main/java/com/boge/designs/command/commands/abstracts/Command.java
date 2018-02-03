package com.boge.designs.command.commands.abstracts;

import com.boge.designs.command.receivers.Receiver;

/**
 * 命令模式，抽象命令类 
 */
public abstract class Command {

	protected Receiver receiver;
	
	public Command(Receiver receiver){
		this.receiver = receiver;
	}
	
	public abstract void excute();
	
}
