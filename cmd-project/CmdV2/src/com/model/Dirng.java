package com.model;

import java.io.File;
import java.util.ArrayList;

import com.manager.CmdV2;

public class Dirng extends Command {

	public Dirng() {
		super("dirng");
	}

	@Override
	public boolean execute() {
		int vCompteurFichier = 0;
		int vCompteurRepertoire = 0;

		String vPath = CmdV2.getPwd();
		File vFile = new File(vPath);
		for (File vFileTmp : vFile.listFiles()) {
			if (vFileTmp.isDirectory()) {
				vCompteurFichier++;
				System.out.println("<DIR>  " + vFileTmp.getName());
			} else if (vFileTmp.isFile()) {
				vCompteurRepertoire++;
				System.out.println("		" + vFileTmp.getName());
			}
		}
		System.out.println(vCompteurFichier > 1 ? vCompteurFichier + " fichiers" : vCompteurFichier + " fichier");
		System.out.println(
				vCompteurRepertoire > 1 ? vCompteurRepertoire + " repertoires" : vCompteurRepertoire + " repertoires");
		return true;
	}

	@Override
	public boolean execute(ArrayList<String> args) {
		this.describe();
		return true;
	}

	@Override
	public void describe() {
		System.out.println("dirng : Affiche le contenu du répertoire en cours ainsi que les compteurs.");

	}

}
