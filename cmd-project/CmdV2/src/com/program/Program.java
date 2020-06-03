package com.program;

import java.util.ArrayList;
import java.util.Scanner;

import com.manager.CmdV2;

public class Program {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		boolean continuer = true;

		System.out.println("Bonjour ! Ca compile ?");
		CmdV2 vtest = new CmdV2();

		while (continuer) {

			// on récupère à chaque boucle le répertoire courant
			System.out.println(CmdV2.getPwd());
			System.out.print("> ");

			// on demande la saisie à l'utilisateur
			String vStr = scan.nextLine();
			vStr = vStr.trim();
			vStr = vStr.replaceAll(" +", " ");

			String[] vCommandArgument = vStr.split("\\ ");

			String vCom = vCommandArgument[0];
			ArrayList<String> vArg = new ArrayList<>();

			for (int i = 1; i < vCommandArgument.length; i++) {
				vArg.add(vCommandArgument[i]);
			}

			// gestion du cas de cd..

			if (vArg.size() == 0) {
				String vCdTest = vCom.substring(0, 2);
				if (vCom.length() > 2 && vCdTest.equalsIgnoreCase("cd")) {
					vCom = "cd";
					vArg.add("..");
				}
			}

			if (vArg.size() == 0) {
				continuer = vtest.execute(vCom);
			} else {
				continuer = vtest.execute(vCom, vArg);
			}

			System.out.println();

		}
	}
}
