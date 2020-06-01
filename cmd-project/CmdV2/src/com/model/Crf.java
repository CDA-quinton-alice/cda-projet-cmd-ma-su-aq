package com.model;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Crf extends Command {

	public Crf() {
		super("crf");
	}

	@Override
	public boolean execute() {
	System.out.println("Impossible de créer un fichier sans nom");
		return true;
	}

	@Override
	public boolean execute(ArrayList<String> args) {
		boolean erreurCreation = false;
		String vNomFichier = args.get(0); // Récupération du paramètre = nom du fichier
		File vFile = new File(System.getProperty("user.dir") + "\\" + vNomFichier);
		
		
		if (!vFile.exists()) {
			// Test si il existe n'existe pas --> ok pour création
			try {
				vFile.createNewFile();
			} catch (IOException e) {
				erreurCreation = true;
			}
			if (!erreurCreation) {
				System.out.println("Le fichier"+vNomFichier +" a bien été créé.");
			}
		} else {
			System.out.println("Attention le fichier"+vNomFichier+" existe déjà. Veuillez spécifier un autre nom de fichier à créer s'il vous plaît.");
			//--> COPIER LE FICHER avec même code que commande COPY
		}
		return true;
	}

	@Override
	public void describe() {
		System.out.println("crf [param] : Crée le fichier placé en paramètre.");
	}

}
