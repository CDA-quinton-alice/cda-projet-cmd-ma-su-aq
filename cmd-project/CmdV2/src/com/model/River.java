package com.model;

import java.util.ArrayList;
import java.util.Scanner;

public class River extends Command {

	public River() {
		super("river");
	}

	@Override
	public boolean execute(ArrayList<String> args) {
		System.out.println("Saisissez votre premi�re rivi�re num�rique");
		Scanner in = new Scanner(System.in);
		long r1 = in.nextLong();
		System.out.println("Saisissez votre seconde rivi�re num�rique");
		long r2 = in.nextLong();
		while (r1 != r2) {
			while (r1 < r2) {
				r1 = nextValue(r1);
			}
			while (r1 > r2) {
				r2 = nextValue(r2);
			}
		}
		System.out.println("premi�re intersection des rivi�res num�riques obtenues pour ces param�tres : " + r1);
		return true;
	}

	static long nextValue(long value) {
		for (char c : String.valueOf(value).toCharArray()) {
			value += c - '0';
		}
		return value;
	}

	@Override
	public void describe() {
		System.out.println(
				"river [param1 param2] : prend 2 param�tres en entr�e puis affiche la premi�re intersection des rivi�res num�riques obtenues pour ces derniers.");

	}

	@Override
	public boolean execute() {
		return false;
	}

}