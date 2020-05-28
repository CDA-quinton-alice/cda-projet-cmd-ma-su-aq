package com.model;

public abstract class Command {
	protected String vNomCommand;
	
	public Command(String vNomCommand) {
		this.vNomCommand = vNomCommand;
	}
	
	public abstract void execute();
	public abstract void describe();

	public String getvNomCommand() {
		return vNomCommand;
	}
	
	
}
