package com.model;

import java.io.File;
import java.util.ArrayList;

public class Crd extends Command {

	public Crd() {
		super("crd");

	}

	@Override
	public boolean execute() {
		System.out.println("Impossible de créer un répertoire sans nom");
		return false;
	}

	@Override
	public boolean execute(ArrayList<String> args) {

		boolean erreurCreationRepertoire = false;
		String vNomRepertoire = args.get(0); // --> pour récupérer le nom du répertoire en guise de paramètre (indice 0)
		File file = new File(System.getProperty("user.dir") + "\\" + vNomRepertoire);

		if (!file.exists()) {
			file.mkdir();
			if (!erreurCreationRepertoire) {
				System.out.println("Le répertoire " + vNomRepertoire + " a bien été créé. ");
			}
		} else {
			System.out.println("Erreur dans la création du dossier " + vNomRepertoire
					+ ". Ce dernier existe déjà. Veuillez spécifier un autre nom de répertoire à créer s'il vous plaît.");

		}
		return true;
	}

	@Override
	public void describe() {
		System.out.println("crd [param] : Créée le dossier placé en paramètre.");

	}

}
