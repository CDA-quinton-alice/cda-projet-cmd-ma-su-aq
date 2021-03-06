package com.model;

import java.util.ArrayList;

public class Quit extends Command {

	public Quit() {
		super("quit");
	}

	@Override
	public boolean execute() {
		System.out.println("Bye !");
		return false; // Retourne False pour sortir de la boucle
	}

	// Camouflage...
	@Override
	public boolean execute(ArrayList<String> args) {
		this.describe();
		return true;
	}

	@Override
	public void describe() {
		System.out.println("quit : Met fin au programme.");
	}

}
