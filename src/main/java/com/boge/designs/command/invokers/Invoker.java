package com.boge.designs.command.invokers;

import java.util.ArrayList;
import java.util.List;

import com.boge.designs.command.commands.abstracts.Command;

/**
 * 命令执行者 
 */
public class Invoker {
	
	List<Command> commands = null;
	
	public Invoker(){
		commands = new ArrayList<Command>();
	}
	
	public void addCommand(Command command){
		commands.add(command);
	}
	
	public void invokeCommands(){
		for(Command command:commands){
			command.excute();
		}
	}

}
