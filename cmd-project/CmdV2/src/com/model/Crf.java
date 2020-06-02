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
		this.describe();
		return true;
	}

	@Override
	public boolean execute(ArrayList<String> args) {
		boolean erreurCreation = false;
		String vNomFichier = args.get(0); // R�cup�ration du param�tre = nom du fichier
		File vFile = new File(System.getProperty("user.dir") + "\\" + vNomFichier);

		if (!vFile.exists()) {
			// Test si il existe n'existe pas --> ok pour cr�ation
			try {
				vFile.createNewFile();
			} catch (IOException e) {
				erreurCreation = true;
			}
			if (!erreurCreation) {
				System.out.println("Le fichier " + vNomFichier + " a bien �t� cr��.");
			}
		} else {
			System.out.println("Attention le fichier " + vNomFichier
			// --> COPIER LE FICHER avec m�me code que commande COPY
					+ " existe d�j�. Veuillez sp�cifier un autre nom de fichier � cr�er s'il vous pla�t.");
		}
		return true;
	}

	@Override
	public void describe() {
		System.out.println("crf [param] : Cr��e le fichier plac� en param�tre.");
	}

}
