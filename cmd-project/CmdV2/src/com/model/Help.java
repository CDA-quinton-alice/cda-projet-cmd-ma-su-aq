package com.model;

import java.util.ArrayList;

public class Help extends Command implements INonHistory{

	public Help() {
		super("help");
	}

	@Override
	public boolean execute() {
		System.out.println("Bonjour sur la console cda");
		return true;
	}

	@Override
	public boolean execute(ArrayList<String> args) {
		System.out.println("Bonjour sur la console cda avec les arguments : ");
		for (String string : args) {
			System.out.print(string+" ");
		}
		return true;
	}	
	
	
	@Override
	public void describe() {
		System.out.println("help [param] : Sans paramètre, donne la liste des commandes disponible, donne la description de la commande en paramètre sinon");
	}


}
