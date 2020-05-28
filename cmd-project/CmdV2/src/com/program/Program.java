package com.program;

import java.util.Scanner;

import com.manager.CmdV2;

public class Program {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		boolean continuer = true;

		System.out.println("Hello : saisie : >");
		CmdV2 vtest = new CmdV2();

		while (continuer) {

			System.out.print("> ");
			String vStr = scan.nextLine();

			continuer = vtest.execute(vStr);
		}

	}
}
