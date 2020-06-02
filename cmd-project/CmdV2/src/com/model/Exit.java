package com.model;

import java.util.ArrayList;

public class Exit extends Command {

	public Exit() {
		super("exit");
	}

	@Override
	public boolean execute() {
		System.out.println("Au revoir !");
		return false; // return false car c'est la commande pour quitter le programme
	}

	// Camouflage no jutsu !
	@Override
	public boolean execute(ArrayList<String> args) {
		this.describe();
		return true;
	}

	@Override
	public void describe() {
		System.out.println("exit : Met fin au programme.");
	}

}
