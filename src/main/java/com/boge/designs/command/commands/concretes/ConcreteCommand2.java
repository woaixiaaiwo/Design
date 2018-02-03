package com.boge.designs.command.commands.concretes;

import com.boge.designs.command.commands.abstracts.Command;
import com.boge.designs.command.receivers.Receiver;

/**
 * 具体命令2 
 */
public class ConcreteCommand2 extends Command{

	public ConcreteCommand2(Receiver receiver) {
		super(receiver);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void excute() {
		// TODO Auto-generated method stub
		receiver.excute2();
	}

}
