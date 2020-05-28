package com.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Cat extends Command {

	public Cat() {
		super("cat");
	}

	@Override
	public boolean execute() {
		return false;
	}

	@Override
	public boolean execute(ArrayList<String> args) {
		String vNomFicher = args.get(0);
		try {
			File vFile = new File(System.getProperty("user.dir") + "\\" + vNomFicher); // Ne r�cup�re pas le fichier
			if (!vFile.exists()) {
				System.out.println("Erreur le fichier est introuvable");
			} else {
				BufferedReader br = new BufferedReader(new FileReader(vFile));
				String vligne;
				System.out.println("Contenu du fichier : ");
				while ((vligne = br.readLine()) != null) {
					System.out.println(vligne);
				}
			}
		} catch (IOException e) {
			System.out.println("Impossible de lire le fichier " + args.get(0));
		}
		return true;
	}

	@Override
	public void describe() {
		System.out.println("Je suis une commande cat et j'affiche le contenu du fichier");
	}

}
