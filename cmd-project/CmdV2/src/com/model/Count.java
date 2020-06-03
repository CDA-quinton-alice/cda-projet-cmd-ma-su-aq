package com.model;

import java.io.File;
import java.util.ArrayList;

import com.manager.CmdV2;

public class Count extends Command {

	public Count() {
		super("count");
	}

	@Override
	public boolean execute() {
		File curr = new File(CmdV2.getPwd());
		String[] fichiers = curr.list();
		int nbF = 0;
		int nbD = 0;
		for (int k = 0; k < fichiers.length; k++) {
			File tmp = new File(curr + "\\" + fichiers[k]);
			if (tmp.isDirectory()) {
				nbD++;
			} else if (tmp.isFile()) {
				nbF++;
			}
		}

		System.out.println(nbF + " fichiers");
		System.out.println(nbD + " dossiers");
		return true;
	}

	@Override
	public boolean execute(ArrayList<String> args) {
		boolean argF = false;
		boolean argR = false;
		boolean argD = false;
		boolean error = false;
		boolean mismatch = false;

		// gestion des arguments
		for (String str : args) {
			if (!str.matches("^[-][rfd].*")) {
				mismatch = true;
			}
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == 'r') {
					argR = true;
					error = false;
				} else if (str.charAt(i) == 'f') {
					if (!argR) {
						argF = true;
						error = false;
					} else {
						error = true;
					}
				} else if (str.charAt(i) == 'd') {
					if (!argR) {
						argD = true;
						error = false;
					} else {
						error = true;
					}
				} else {
					if (str.charAt(i) == '-') {
						error = true;
					} else {
						mismatch = true;
					}
				}
			}
		}

		System.out.println("");

		// phase de comptage
		if (!error && !mismatch) {
			File curr = new File(CmdV2.getPwd());
			String[] fichiers = curr.list();
			int nbF = 0;
			int nbD = 0;
			if (!argR) {
				for (int k = 0; k < fichiers.length; k++) {
					File tmp = new File(curr + "\\" + fichiers[k]);
					if (tmp.isDirectory() && argD) {
						nbD++;
					} else if (tmp.isFile() && argF) {
						nbF++;
					}
				}
			} else {
				argF = argD = true;
				ArrayList<String> files = new ArrayList<>();
				File path = new File(CmdV2.getPwd());
				listeRepertoireV2(path, files);

				for (String strFile : files) {
					File tmpFile = new File(strFile);
					System.out.println(strFile);
					if (tmpFile.isDirectory()) {
						nbD++;
					} else if (tmpFile.isFile()) {
						nbF++;
					}
				}

			}

			if (argF) {
				System.out.println(nbF + " fichiers");
			}
			if (argD) {
				System.out.println(nbD + " dossiers");
			}

		} else {
			System.out.println("Erreur des arguments de la commande.");
			this.describe();
		}

		return true;
	}

	@Override
	public void describe() {
		System.out.println("count [-r] [-fd] : compte le nombre de dossier et fichier du répertoire courant.");
		System.out.println("\t r : inclue tous les sous-dossiers");
		System.out.println("\t f : ne compte que les fichiers");
		System.out.println("\t d : ne compte que les dossiers");
	}

	public static void listeRepertoireV2(File path, ArrayList<String> allFiles) {

		String currentFilePath = path.getAbsolutePath();
		allFiles.add(currentFilePath);
		if (path.isDirectory()) {
			File[] list = path.listFiles();
			if (list != null) {
				for (int i = 0; i < list.length; i++) {
					// Appel récursif sur les sous-répertoires
					listeRepertoireV2(list[i], allFiles);
				}
			} else {
				System.err.println(path + " : Erreur de lecture.");
			}
		}
	}

}
