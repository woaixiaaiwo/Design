package com.boge.designs.interpreter;

/**
 * 解释器模式，全局信息，表示某个语言等
 */
public class Context {

	private String input;
	
	private String output;

	public String getInput() {
		return input;
	}

	public void setInput(String input) {
		this.input = input;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}
}
