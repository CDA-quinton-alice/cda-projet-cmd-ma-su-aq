package com.model;

import java.io.File;
import java.util.ArrayList;

import com.manager.CmdV2;

public class Cd extends Command {

	public Cd() {
		super("cd");
	}

	@Override
	public boolean execute() {
		this.describe();
		return true;
	}

	@Override
	public boolean execute(ArrayList<String> args) {
		String str = args.get(0);
		for (int i = 1; i < args.size(); i++) {
			str += " " + args.get(i);
		}
		String curr = CmdV2.getPwd();

		if (isPoints(str) && !isRoot(curr)) {
			if (isLastBackSlash(curr)) {
				curr = curr.substring(0, str.length() - 1);
			}
			curr = curr.substring(0, curr.lastIndexOf("\\"));
			File f2 = new File(curr);
			if (f2.exists()) {
				CmdV2.setPwd(curr);
			} else {
				System.out.println("Erreur lors du déplacement en amont des fichiers spécifiés.");
			}
		} else {
			if (isRoot(str)) {
				CmdV2.setPwd(str);
			} else {
				File f3 = new File(str);
				if (f3.isAbsolute()) {
					CmdV2.setPwd(str);
				} else {
					File f4 = new File(curr + "/" + str);
					if (f4.exists()) {
						if (isRoot(curr)) {
							CmdV2.setPwd(curr.substring(0, curr.length() - 1) + "/" + str);
						} else {
							CmdV2.setPwd(curr + "/" + str);
						}
					} else {
						System.out.println("Le chemin demandé n'existe pas !");
					}
				}
			}
		}

		return true;
	}

	@Override
	public void describe() {
		System.out.println("cd [param] : changer le dossier courant pour celui placé en paramètre.");
	}

	public static boolean isPoints(String s) {
		return s.matches("^\\.\\.");
	}

	public static boolean isRoot(String s) {
		s = s.toLowerCase();
		return s.matches("[a-zA-Z]:\\\\") || s.matches("[a-zA-Z]:/");
	}

	public static boolean isLastBackSlash(String s) {
		int liob = s.lastIndexOf("\\");
		if (liob == s.length() - 1)
			return true;
		else
			return false;
	}
}
