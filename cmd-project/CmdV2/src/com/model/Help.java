package com.model;

public class Help extends Command {

	
	
	public Help() {
		super("help");
	}

	@Override
	public void execute() {
		System.out.println("Bonjour �a compile ?");
	}

	@Override
	public void describe() {
		System.out.println("Je suis une commande help");
	}

}
