package com.manager;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import com.model.Command;
import com.model.History;

public class CmdV2 {
	private static String pwd;
	private static ArrayList<Command> vCommands;

	public CmdV2() {
		pwd = System.getProperty("user.dir");
		vCommands = new ArrayList<>();
		boolean isJar = false;

		// recuperer le repertoire courant
		File dir = new File(pwd + "\\src\\com\\model");
		String s[];

		if (!dir.exists()) {
			ArrayList<String> tmp = getClasseNames(pwd + "\\cda-projet-cmd-ma-su-aq.jar");
			s = new String[tmp.size()];
			s = tmp.toArray(s);
			isJar = true;
		} else {
			s = dir.list();
		}

		if (System.getProperty("cdi.default.folder") != null) {
			File vFile = new File(System.getProperty("cdi.default.folder"));
			if (vFile.exists()) {
				String vStock = System.getProperty("cdi.default.folder");
				if (vStock.charAt(vStock.length() - 1) != '\\') {
					pwd = vStock + "\\";
				} else {
					pwd = vStock;
				}
			}
		}

		// on récupere la liste des fichiers
		String s2[] = new String[s.length];

		// formatage des fichiers pour retirer l'extension Cat.java => Cat
		for (int z = 0; z < s.length; z++) {
			s2[z] = s[z].substring(0, s[z].lastIndexOf("."));
		}

		try {
			// parcours de chaque fichier
			for (int i = 0; i < s2.length; i++) {
				String classe = s2[i];

				// on tente de récuperer chaque classe
				Class<?> cls;
				if (!isJar) {
					cls = Class.forName("com.model." + classe);
				} else {
					cls = Class.forName(classe);
				}

				// et on teste s'il hérite de Command
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

	public boolean execute(String pCommande, ArrayList<String> pListeArgs) {
		// on recupere la commande
		Command command = getCommandByName(pCommande);

		// on teste si elle existe
		if (command != null) {
			// si oui on tente d'ajouter la commande entree dans les logs
			// on laisse la classe Histoire se debrouiller
			((History) getCommandByName("history")).ajouterCommande(pCommande, pListeArgs);

			// on execute la commande qui accepte la liste d'argument
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
		// on parcours toutes les commandes que l'on a deja accepte
		for (Command command : vCommands) {
			if (command.getvNomCommand().equalsIgnoreCase(pCmd)) {
				// si les noms sont concordants
				return command;
			}
		}
		return null;
	}

	public static ArrayList<String> getClasseNames(String jarName) {
		ArrayList<String> classes = new ArrayList<>();

		try {
			JarInputStream jarFile = new JarInputStream(new FileInputStream(jarName));
			JarEntry jarEntry;

			while (true) {
				jarEntry = jarFile.getNextJarEntry();
				if (jarEntry == null) {
					break;
				}
				if (jarEntry.getName().endsWith(".class")) {
					classes.add(jarEntry.getName().replaceAll("/", "\\."));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return classes;
	}
}
