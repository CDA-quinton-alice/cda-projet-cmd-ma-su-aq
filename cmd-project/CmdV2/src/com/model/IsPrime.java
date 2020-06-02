package com.model;

import java.util.ArrayList;

public class IsPrime extends Command {

	public IsPrime() {
		super("isprime");
	}

	@Override
	public boolean execute() {
		this.describe();
		return false;
	}

	@Override
	public boolean execute(ArrayList<String> args) {
		int vNbaTester = Integer.parseInt(args.get(0));
		int tmp = 0;
		boolean trouve = true;

		if (vNbaTester == 0 || vNbaTester == 1) {
			System.out.println("no");
		} else {
			tmp = vNbaTester / 2;

			for (int i = 2; i <= tmp; i++) {
				if (vNbaTester % i == 0) {
					System.out.println("no");
					trouve = false;
					break;
				}
			}
			if (trouve) {
				System.out.println("yes");
			}
		}
		return true;
	}

	@Override
	public void describe() {
		System.out.println(
				"isprime : Vérifie si le nombre est un nombre premier. Affiche yes si ce dernier en est un, no si il n'en n'est pas un.");
	}

}
