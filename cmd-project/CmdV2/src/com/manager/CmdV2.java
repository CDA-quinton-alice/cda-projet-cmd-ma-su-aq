package com.manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.model.Command;
import com.model.History;

public class CmdV2 {
	private static String pwd;
	private static ArrayList<Command> vCommands;

	public CmdV2() {
		pwd = System.getProperty("user.dir");
		vCommands = new ArrayList<>();

		// repertoire courant
		File dir = new File(pwd + "\\src\\com\\model");

		// on recupere la liste des fichiers
		String s[] = dir.list();
		String s2[] = new String[s.length];

		// formatage des fichiers pour retirer l'extension Cat.java => Cat
		for (int z = 0; z < s.length; z++) {
			s2[z] = s[z].substring(0, s[z].lastIndexOf("."));

		}

		try {
			// parcours de chaque fichier
			for (int i = 0; i < s2.length; i++) {
				String classe = s2[i];

				// on tente de recuperer chaque classe
				Class<?> cls = Class.forName("com.model." + classe);

				// et on teste s'il herite de Command
				if (Command.class.isAssignableFrom(cls) && !(cls.getName().toString().equals("com.model.Command"))) {
					try {
						// on instancie alors la classe puis on l'ajoute dans la liste des commandes
						// disponible
						Command c = (Command) cls.newInstance();
						vCommands.add(c);
					} catch (InstantiationException | IllegalAccessException e) {
						e.printStackTrace();
					}
				}
			}
		} catch (ClassNotFoundException cnf) {
			System.out.println(cnf.toString());
			cnf.getStackTrace();
		}
	}

	// GETTER
	public static ArrayList<Command> getvCommands() {
		return vCommands;
	}

	public static String getPwd() {
		return pwd;
	}

	// SETTER
	public static void setPwd(String pwd) {
		CmdV2.pwd = pwd;
	}

	public boolean execute(String pCommande, ArrayList<String> pListeArgs) throws IOException {
		Command command = getCommandByName(pCommande);
		if (command != null) {
			((History) getCommandByName("history")).ajouterCommande(pCommande, pListeArgs);
			return command.execute(pListeArgs);
		} else {
			System.out.println("La commande n'existe pas !");
		}
		return true;
	}

	public boolean execute(String pCommande) {
		Command command = getCommandByName(pCommande);
		if (command != null) {
			((History) getCommandByName("history")).ajouterCommande(pCommande, null);
			return command.execute();
		} else {
			System.out.println("La commande n'existe pas !");
		}
		return true;
	}

	public static Command getCommandByName(String pCmd) {
		for (Command command : vCommands) {
			if (command.getvNomCommand().equalsIgnoreCase(pCmd)) {
				return command;
			}
		}
		return null;
	}
}
