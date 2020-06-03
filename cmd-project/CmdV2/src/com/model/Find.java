package com.model;

import java.io.File;
import java.util.ArrayList;

import com.manager.CmdV2;

public class Find extends Command {

	public Find() {
		super("find");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute() {
		this.describe();
		return true;
	}

	@Override
	public boolean execute(ArrayList<String> args) {
		String start = null;
		String end = null;
		String exact = null;
		ArrayList<String> answer = new ArrayList<>();

		boolean isStart = false;
		boolean isEnd = false;
		for (String str : args) {

			// On s'occupe de récupérer ce qui a été placé en argument
			if (isStart) {
				start = str;
				isStart = false;
			} else if (isEnd) {
				end = str;
				isEnd = false;
			} else if (str.equalsIgnoreCase("-starts")) {
				isStart = true;
			} else if (str.equalsIgnoreCase("-ends")) {
				isEnd = true;
			} else {
				exact = str;
			}
		}

		String pwd = CmdV2.getPwd();
		File dir = new File(pwd);
		ArrayList<String> allFiles = new ArrayList<>();

		listeRepertoire(dir, allFiles);

		for (String s : allFiles) {
			String curr = s.toLowerCase();
			if (start != null) {
				if (curr.matches("^" + start + ".*")) {
					answer.add(s);
				}
			} else if (end != null) {
				if (curr.matches(".*" + end + "$")) {
					answer.add(s);
				}
			} else {
				if (curr.matches("^" + exact + "$")) {
					answer.add(s);
				}
			}

		}

		if (answer.size() > 0) {
			answer.stream().forEach(System.out::println);
		} else {
			System.out.println("Aucune occurence trouvée.");
		}

		return true;
	}

	@Override
	public void describe() {
		System.out.println(
				"find [-start] [-end] [param] : Cherche dans le répertoire courant des fichiers commençant et/ou \n"
						+ "terminant le nom spécifié en paramètre ou cherche l'exacte terminologie sinon.");

	}

	public static void listeRepertoire(File path, ArrayList<String> allFiles) {

		if (path.isDirectory()) {
			File[] list = path.listFiles();
			if (list != null) {
				for (int i = 0; i < list.length; i++) {
					// Appel récursif sur les sous-répertoires
					listeRepertoire(list[i], allFiles);
				}
			} else {
				System.err.println(path + " : Erreur de lecture.");
			}
		} else {
			String currentFilePath = path.getAbsolutePath();
			allFiles.add(currentFilePath);
		}
	}

}
