package com.manager;

import java.util.ArrayList;

import com.model.Cd;
import com.model.Command;
import com.model.Exit;
import com.model.Help;
import com.model.Pwd;

public class CmdV2 {
	private static String pwd;
	private ArrayList<Command> vCommands;

	public CmdV2() {
		// a remplacer par un ajout modulaire en fonction des classes présentes
		pwd = System.getProperty("user.dir");
		vCommands = new ArrayList<>();
		vCommands.add(new Help());
		vCommands.add(new Exit());
		vCommands.add(new Pwd());
		vCommands.add(new Cd());
	}

	// GETTER
	public ArrayList<Command> getvCommands() {
		return vCommands;
	}

	public static String getPwd() {
		return pwd;
	}

	// SETTER
	public static void setPwd(String pwd) {
		CmdV2.pwd = pwd;
	}

	// Execution avec arguments
	// A faire, gérer les cas où la commande n'existe pas
	public boolean execute(String pCommande, ArrayList<String> pListeArgs) {
		for (Command command : vCommands) {
			if (command.getvNomCommand().equalsIgnoreCase(pCommande)) {
				return command.execute(pListeArgs);
			}
		}
		return true;
	}

	// Execution sans argument
	// A faire, gérer les cas où la commande n'existe pas
	public boolean execute(String pCommande) {
		// On parcours la liste de toutes les commandes disponibles !
		for (Command command : vCommands) {
			if (command.getvNomCommand().equalsIgnoreCase(pCommande)) {
				return command.execute();
			}
		}
		return true;
	}
}
