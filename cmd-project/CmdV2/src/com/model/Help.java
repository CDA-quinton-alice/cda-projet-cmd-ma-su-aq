package com.model;

import java.util.ArrayList;

import com.manager.CmdV2;

public class Help extends Command implements INonHistory {

	public Help() {
		super("help");
	}

	@Override
	public boolean execute() { // Sans paramètre, doit afficher toutes les commandes et leur describe
		System.out.println("Voici les différentes commandes du programme CMdV2 que vous pouvez utiliser : ");
		for (Command vCommand : CmdV2.getvCommands()) {
			vCommand.describe();
		}
		return true;
	}

	@Override
	public boolean execute(ArrayList<String> args) { // Doit afficher le describe de la commande
		String vNomCommand = args.get(0);
		Command vCommand = CmdV2.getCommandByName(vNomCommand);
		vCommand.describe();
		return true;
	}

	@Override
	public void describe() {
		System.out.println(
				"help [param] : Sans paramètre, donne la liste des commandes disponibles, donne la description de la commande en paramètre sinon");

	}
}
