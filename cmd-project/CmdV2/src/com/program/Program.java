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
			//on r�cup�re � chaque boucle le r�pertoire courant
			System.out.println(CmdV2.getPwd());
			System.out.print("> ");
			
			//on demande la saisie � l'utilisateur
			String vStr = scan.nextLine();
			
			
			//d�coupage de la saisie de l'utilisateur
			//Pas la meilleure solution, possible am�lioration !
			//les commandes doivent fonctionner quelque soit le nombre d'espaces
			
			String[] vCommandArgument = vStr.split("\\ ");
			String vCom = vCommandArgument[0];
			ArrayList<String> vArg = new ArrayList<>();
			
			for(int i = 1;i<vCommandArgument.length;i++) {
				vArg.add(vCommandArgument[i]);
			}

			if(vArg.size() == 0) {
				continuer = vtest.execute(vCom);
			}else {
				continuer = vtest.execute(vCom,vArg);
			}
			
			
			System.out.println();
			
		}
	}
}
