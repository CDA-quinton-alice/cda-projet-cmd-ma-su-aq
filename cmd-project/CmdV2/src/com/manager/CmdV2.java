package com.manager;

import java.util.ArrayList;

import com.model.Command;
import com.model.Help;

public class CmdV2 {
	private ArrayList<Command> vCommands ;

	public CmdV2() {
		vCommands = new ArrayList<>();
		vCommands.add(new Help());
	}

	public boolean execute(String pCommande, ArrayList<String> pListeArgs) {
		return false;
	}

	public boolean execute(String pCommande) { // user tape help
		for (Command command : vCommands) {
			if (command.getvNomCommand().equalsIgnoreCase(pCommande)) {
				command.execute();
			}
		}
		return true;
	}

	public ArrayList<Command> getvCommands() {
		return vCommands;
	}
}
