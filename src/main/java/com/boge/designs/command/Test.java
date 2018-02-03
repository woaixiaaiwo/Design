package com.boge.designs.command;

import com.boge.designs.command.commands.concretes.ConcreteCommand1;
import com.boge.designs.command.commands.concretes.ConcreteCommand2;
import com.boge.designs.command.invokers.Invoker;
import com.boge.designs.command.receivers.Receiver;

public class Test {

	public static void main(String[] args) {
		
		Receiver receiver = new Receiver();
		
		ConcreteCommand1 command1 = new ConcreteCommand1(receiver);
		ConcreteCommand2 command2 = new ConcreteCommand2(receiver);
		ConcreteCommand1 command3 = new ConcreteCommand1(receiver);
		
		Invoker invoker = new Invoker();
		invoker.addCommand(command1);
		invoker.addCommand(command2);
		invoker.addCommand(command3);
		invoker.invokeCommands();
		
	}
	
}
