package com.model;

import java.util.ArrayList;

public abstract class Command {
	protected String vNomCommand;

	public Command(String vNomCommand) {
		this.vNomCommand = vNomCommand;
	}

	public abstract boolean execute();

	public abstract boolean execute(ArrayList<String> args);

	public abstract void describe();

	public String getvNomCommand() {
		return vNomCommand;
	}

	public String toString() {
		return this.vNomCommand;
	}
}
