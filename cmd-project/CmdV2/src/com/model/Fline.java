package com.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Fline extends Command {

	public Fline() {
		super("fline");
	}

	@Override
	public boolean execute() {
		this.describe();
		return true;
	}

	@Override
	public boolean execute(ArrayList<String> args) {
		boolean fichierExiste = false;
		boolean sansArg = false;
		boolean argN = false;
		boolean argS = false;
		boolean argDfirst = false;
		boolean argDlast = false;
		boolean argFfirst = false;
		boolean argFlast = false;
		boolean error = false;

		// Gestion des arguments

		switch (args.size()) {
		case 1: // Fichier.txt
			File vFile = new File(args.get(0));
			if (vFile.exists()) {
				fichierExiste = true;
				sansArg = true;
			} else {
				error = true;
			}
			break;

		case 2:// fichier.txt -n
			vFile = new File(args.get(0));
			if (!vFile.exists()) {
				error = true;
			} else if (args.get(1).equalsIgnoreCase("-n")) {
				fichierExiste = true;
				argN = true;
			} else {
				error = true;
			}
			break;

		case 3: // fichier.txt -s l1
			String argument2 = args.get(1);
			vFile = new File(args.get(0));
			if (!vFile.exists()) {
				error = true;
			} else if (!args.get(1).equalsIgnoreCase("-s")) {
				error = true;
			} else {
				fichierExiste = true;
				argS = true;
			}
			break;

		case 4:
			error = true;
			break;

		case 5:// fichier.txt -s bla -d 2
			vFile = new File(args.get(0));
			if (!vFile.exists()) {
				error = true;
			} else if (!args.get(1).equalsIgnoreCase("-s")) {
				error = true;
			} else if (args.get(3).equalsIgnoreCase("-d")) {
				argS = true;
				argDfirst = true;
			} else if (args.get(3).equalsIgnoreCase("-f")) {
				fichierExiste = true;
				argS = true;
				argFfirst = true;
			} else {
				error = true;
			}
			break;

		case 6:
			error = true;
			break;

		case 7:// fichier.txt -s bla -d 2 -f 3
			vFile = new File(args.get(0));
			if (!vFile.exists()) {
				error = true;
			} else if (args.get(1).equalsIgnoreCase("-s")) {
				argS = true;
				fichierExiste = true;
				if (args.get(3).equalsIgnoreCase("-d")) {
					argDfirst = true;
				} else if (args.get(3).equalsIgnoreCase("-f")) {
					argFfirst = true;
				} else {
					error = true;
				}
				System.out.println(args.get(5));
				if (args.get(5).equalsIgnoreCase("-d")) {
					argDlast = true;
				} else if (args.get(5).equalsIgnoreCase("-f")) {
					argFlast = true;
				} else {
					error = true;
				}
			} else {
				error = true;
			}

			break;

		default:
			break;
		}

//		System.out.println("fichierExiste " + fichierExiste);
//		System.out.println("argN " + argN);
//		System.out.println("argS " + argS);
//		System.out.println("argDfirst " + argDfirst);
//		System.out.println("argDlast " + argDlast);
//		System.out.println("argFfirst " + argFfirst);
//		System.out.println("argFlast " + argFlast);
//		System.out.println("error " + error);
//		System.out.println();

		// Action en fonction des tests
		if (!error) {
			// cas du sans option
			if (sansArg) {
				lireFichier(args.get(0));
			}
			// cas du -n compter le nombre de lignes
			if (argN) {
				System.out.println("Nombre de lignes :");
				System.out.println(compterLeslignes(args.get(0)));
			}
			// Cas du -s STRING args n°2
			if (argS && !argDfirst && !argFfirst && !argDlast && !argDlast) {
				stringIsPresent(args.get(0), args.get(2));
			}
			// cas du -s STRING avec début seulement
			if (argDfirst && !argDfirst && !argFlast) {
				stringIsPresentLimites(args.get(0), args.get(2), Integer.parseInt(args.get(4)),
						compterLeslignes(args.get(0)));
			}
			// cas du -s STRING avec fin seulement
			if (argFfirst && !argDfirst && !argFlast) {
				stringIsPresentLimites(args.get(0), args.get(2), 1, Integer.parseInt(args.get(4)));
			}

			if (argDfirst && argFlast) {
				stringIsPresentLimites(args.get(0), args.get(2), Integer.parseInt(args.get(4)),
						Integer.parseInt(args.get(6)));
			}
			if (argFfirst && argDlast) {
				stringIsPresentLimites(args.get(0), args.get(2), Integer.parseInt(args.get(6)),
						Integer.parseInt(args.get(4)));
			}

		} else {
			System.out.println("Erreur dans les arguments");
		}

		return true;

	}

	@Override
	public void describe() {
		System.out.println("fline fichier [-option1] [-option2] : Traitement sur le fichier ligne par ligne.");
		System.out.println("fline fichier -n : affiche le nombre de lignes");
		System.out.println(
				"fline fichier -s : permet de chercher un string dans une ligne, peut être utilisée avec les options -d et -f");
		System.out.println(
				"fline fichier -d : permet de définir le numero de ligne à partir duquel le traitement va être fait (inclut).");
		System.out.println(
				"fline fichier -f : permet de définir le numero de ligne jusqu'auquel le traitement va être fait (inclut).");

	}

	public static void lireFichier(String pNomFichier) {
		String vNomDuFichier = pNomFichier;
		try {
			File vFile = new File(System.getProperty("user.dir") + "\\" + vNomDuFichier);
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
			System.out.println("Impossible de lire le fichier " + pNomFichier);
		}
	}

	public static void stringIsPresent(String pNomFichier, String pStringATester) {
		boolean stringPresent = false;
		try {
			File vFile = new File(System.getProperty("user.dir") + "\\" + pNomFichier);
			BufferedReader br = new BufferedReader(new FileReader(vFile));
			String vligne;
			while ((vligne = br.readLine()) != null) {
				if (vligne.contains(pStringATester)) {
					stringPresent = true;
					System.out.println(vligne);
				}
			}
		} catch (IOException e) {
			System.out.println("Impossible de lire le fichier " + pNomFichier);
		}
		if (!stringPresent) {
			System.out.println("le fichier ne contient pas " + pStringATester);
		}
	}

	public static int compterLeslignes(String pNomFichier) {
		int cmptLignes = 0;
		try {
			File vFile = new File(System.getProperty("user.dir") + "\\" + pNomFichier);
			BufferedReader br = new BufferedReader(new FileReader(vFile));
			String vligne;
			while ((vligne = br.readLine()) != null) {
				cmptLignes++;
			}
		} catch (IOException e) {
			System.out.println("Impossible de lire le fichier " + pNomFichier);
		}
		return cmptLignes;
	}

	public static int affichageLignes(String pNomFichier) {
		int cmptLignes = 0;
		try {
			File vFile = new File(System.getProperty("user.dir") + "\\" + pNomFichier);
			BufferedReader br = new BufferedReader(new FileReader(vFile));
			String vligne;
			System.out.println("Nombre de lignes : ");
			while ((vligne = br.readLine()) != null) {
				cmptLignes++;
			}
		} catch (IOException e) {
			System.out.println("Impossible de lire le fichier " + pNomFichier);
		}
		return cmptLignes;
	}
	// nomfichier début et fin(nbligne)
	// valeurs par def --> début =0 et fin = compterlignes

	public static void stringIsPresentLimites(String pNomFichier, String pStringATester, int pDebut, int pFin) {
		boolean stringPresent = false;
		int cmptLigneEnCours = 1;
		try {
			File vFile = new File(System.getProperty("user.dir") + "\\" + pNomFichier);
			BufferedReader br = new BufferedReader(new FileReader(vFile));
			String vligne;
			while ((vligne = br.readLine()) != null) {
				if (pDebut <= cmptLigneEnCours && cmptLigneEnCours <= pFin) {
					if (vligne.contains(pStringATester)) {
						stringPresent = true;
						System.out.println(vligne);
					}
				}
				cmptLigneEnCours++;
			}
		} catch (IOException e) {
			System.out.println("Impossible de lire le fichier " + pNomFichier);
		}
		if (!stringPresent) {
			System.out.println("le fichier ne contient pas " + pStringATester);
		}
	}

}
