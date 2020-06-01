package com.model;

import java.io.IOException;
import java.util.ArrayList;

public abstract class Command {
	protected String vNomCommand;

	public Command(String vNomCommand) {
		this.vNomCommand = vNomCommand;
	}

	public abstract boolean execute();

<<<<<<< HEAD
	public abstract boolean execute(ArrayList<String> args) throws IOException;
=======
	public abstract boolean execute(ArrayList<String> args);
>>>>>>> 4eb1209df0ee038f85c02cb528d41e9d6b6c3ba5

	public abstract void describe();

	public String getvNomCommand() {
		return vNomCommand;
	}

	public String toString() {
		return this.vNomCommand;
	}
}
