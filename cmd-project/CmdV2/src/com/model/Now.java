package com.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Now extends Command {

	public Now() {
		super("now");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute() {
		this.describe();
		return true;
	}

	@Override
	public boolean execute(ArrayList<String> args) {
		// TODO Auto-generated method stub

		boolean argT = false;
		boolean argD = false;
		boolean error = false;
		boolean mismatch = false;

		// vérification du format des arguments
		for (String str : args) {
			if (!str.matches("^[-][td].*")) {
				mismatch = true;
			}
			for (int i = 0; i < str.length(); i++) {
				if (str.charAt(i) == 't') {
					argT = true;
					error = false;
				} else if (str.charAt(i) == 'd') {
					argD = true;
					error = false;
				} else {
					if (str.charAt(i) == '-') {
						error = true;
					} else {
						mismatch = true;
					}

				}

			}
		}
		if (!error && !mismatch) {
			SimpleDateFormat formatDate;
			Date date = new Date();
			if (argT && !argD) {
				formatDate = new SimpleDateFormat("hh:mm:ss");

			} else if (argD && !argT) {
				formatDate = new SimpleDateFormat("dd-MM-yyyy");
			} else {
				formatDate = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
			}
			System.out.println(formatDate.format(date));
		} else {
			System.out.println("Erreur des arguments de la commande.");
			this.describe();
		}

		return true;
	}

	@Override
	public void describe() {
		// TODO Auto-generated method stub
		System.out.println("now [-t] [-d] : affiche date et/ou heure en cours.");

	}

}
