package com.model;

import java.util.ArrayList;

public class River extends Command {

	public River() {
		super("river");
	}

	@Override
	public boolean execute() {
		this.describe();
		return true;
	}

	@Override
	public boolean execute(ArrayList<String> args) { // river 13 45
//	 Tu récupères le args[0] --> String attention --> donne le r1
//	 Tu récup le args[1] --> r2
		String r1 = args.get(0);
		String r2 = args.get(1);
		int nbIt = 0;

		while (!r1.equals(r2) && nbIt < 100) {
			while (Long.parseLong(r1) < Long.parseLong(r2)) {
				r1 = nextValue(r1);
				nbIt++;
			}
			while (Long.parseLong(r1) > Long.parseLong(r2)) {
				r2 = nextValue(r2);
				nbIt++;
			}
		}
		if (nbIt < 100) {
			System.out.println("Première intersection des rivières numériques obtenues pour ces paramètres : " + r1);
		} else {
			System.out.println(
					"Maurice, tu as dépassé les bornes des limites pour trouver l'intersection des rivières numériques ! C'est la pension !");
		}

		return true;
	}

	private String nextValue(String value) {
		long vInitiale = Long.parseLong(value);
		for (int i = 0; i < value.length(); i++) {
			vInitiale += Character.getNumericValue(value.charAt(i)); // on va ajouter Initiale à chacun de ces digits et
																		// on va retourner vInitiale
		}
		return vInitiale + "";
	}

	@Override
	public void describe() {
		System.out.println(
				"river [param1 param2] : prend 2 paramètres en entrée puis affiche la première intersection des rivières numériques obtenues pour ces derniers.");

	}

}