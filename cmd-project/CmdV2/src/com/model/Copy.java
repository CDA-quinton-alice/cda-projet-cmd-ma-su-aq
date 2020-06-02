package com.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

public class Copy extends Command {
	private static final int BUFFER_SIZE = 8192;
	static int cmpt = 1;

	public Copy() {
		super("copy");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute() {
		this.describe();
		return true;
	}

	@Override
	public boolean execute(ArrayList<String> args) { // Erreur de saisie � g�rer
		boolean sortie = false;
		String vNomFichierRecherche = args.get(0); // R�cup�ration du param�tre = nom du fichier
		File vFichierRecherche = new File(vNomFichierRecherche);

		if (!vFichierRecherche.exists()) {
			System.out.println("Impossible de trouver le fichier � copier"); // Controle
		} else {
			int vIndexDernierPoint = vNomFichierRecherche.lastIndexOf('.');
			String vFichierRecherchePartieNom = vNomFichierRecherche.substring(0, vIndexDernierPoint);
			String vFichierRechecheExtension;
			if (vIndexDernierPoint == -1) { // Si le fichier ne contient pas d'extension
				vFichierRechecheExtension = "";
			} else {
				vFichierRechecheExtension = vNomFichierRecherche.substring(vIndexDernierPoint,
						vNomFichierRecherche.length());
				System.out.println(vFichierRechecheExtension);
			}
			// Construction du nouveau fichier
			String vFichierCibleNom = vFichierRecherchePartieNom + "-" + (++cmpt) + vFichierRechecheExtension;
			File vFichierCible = new File(vFichierCibleNom);

			while (!sortie) {

				if (!vFichierCible.exists()) {
					System.out.println("Le fichier cible n'existe pas, cr�ation ok");
					// Construction d'un nouveau fichier

					try {
						vFichierCible.createNewFile();
					} catch (IOException e1) {
						System.out.println("erreur dans la cr�ation du fichier demand�");
						e1.printStackTrace();
					}
					sortie = true;
				} else {
					vFichierCibleNom = vFichierRecherchePartieNom + "-" + (++cmpt) + vFichierRechecheExtension;
					vFichierCible = new File(vFichierCibleNom);
					try {
						vFichierCible.createNewFile();
					} catch (IOException e1) {
						System.out.println("erreur dans la cr�ation du fichier demand�");
						e1.printStackTrace();
					}
					sortie = true;
				}
				try (InputStream fichierSource = new FileInputStream(vFichierRecherche);
						OutputStream fichierSortie = new FileOutputStream(vFichierCible)) {
					byte[] vBufferDeTransfert = new byte[BUFFER_SIZE];
					int nbOctetsLus = fichierSource.read(vBufferDeTransfert);
					while (nbOctetsLus != -1) {
						fichierSortie.write(vBufferDeTransfert, 0, nbOctetsLus);
						nbOctetsLus = fichierSource.read(vBufferDeTransfert);
					}
					System.out
							.println("Copie du fichier faite avec succ�s (" + vFichierRecherche.length() + " octets).");

				} catch (IOException e) {
					System.out.println(
							"La commande demand�e a rencontr� un probl�me. Veuillez recommencer ou sp�cifier une autre commande s'il vous pla�t. "
									+ e.getMessage() + ".");
				}
			}
		}
		return true;
	}

	@Override
	public void describe() {
		System.out.println("copy [param] : Copie le fichier plac� en param�tre.");
	}

}
