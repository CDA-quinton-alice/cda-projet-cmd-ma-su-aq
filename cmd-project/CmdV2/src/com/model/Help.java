package com.model;

import java.util.ArrayList;

public class Help extends Command implements INonHistory{

	public Help() {
		super("help");
	}

	@Override
	public boolean execute() {
		System.out.println("Voici les différentes commandes du programme CMdV2 que vous pouvez utiliser : ");
		return true;
	}

	@Override
	public boolean execute(ArrayList<String> args) {
		System.out.println("Console cda avec les différents arguments pris en charge : ");
		for (String string : args) {
			System.out.print(string+" ");
		}
		return true;
	}	
	
	
	@Override
	public void describe() {
		System.out.println("help [param] : Sans paramètre. Donne la liste des commandes disponibles, sinon donne la description de la commande en paramètre.");
	}


}
