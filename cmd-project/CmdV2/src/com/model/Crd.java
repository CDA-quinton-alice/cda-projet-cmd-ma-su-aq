package com.model;

import java.io.File;
import java.util.ArrayList;

public class Crd extends Command {

	public Crd() {
		super("crd");

	}

	@Override
	public boolean execute() {
		System.out.println("Impossible de cr�er un r�pertoire sans nom");
		return false;
	}

	@Override
	public boolean execute(ArrayList<String> args) {

		boolean erreurCreationRepertoire = false;
		String vNomRepertoire = args.get(0); // --> pour r�cup�rer le nom du r�pertoire en guise de param�tre (indice 0)
		File file = new File(System.getProperty("user.dir") + "\\" + vNomRepertoire);

		if (!file.exists()) {
			file.mkdir();
			if (!erreurCreationRepertoire) {
				System.out.println("Le r�pertoire " + vNomRepertoire + " a bien �t� cr��. ");
			}
		} else {
			System.out.println("Erreur dans la cr�ation du dossier " + vNomRepertoire
					+ ". Ce dernier existe d�j�. Veuillez sp�cifier un autre nom de r�pertoire � cr�er s'il vous pla�t.");

		}
		return true;
	}

	@Override
	public void describe() {
		System.out.println("crd [param] : Cr��e le dossier plac� en param�tre.");

	}

}
