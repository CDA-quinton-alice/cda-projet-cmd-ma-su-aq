package com.model;

import java.io.File;
import java.util.ArrayList;

import com.manager.CmdV2;

public class cmdFind extends Command {

	public cmdFind() {
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
		String pwd = CmdV2.getPwd();
		File dir = new File(pwd);
		String s[] = dir.list();

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
			} else if (str.equalsIgnoreCase("-start")) {
				isStart = true;
			} else if (str.equalsIgnoreCase("-end")) {
				isEnd = true;
			} else {
				exact = str;
			}
		}
		for (int i = 0; i < s.length; i++) {
			String curr = s[i].toLowerCase();
			if (start != null) {
				if (curr.matches("^" + start + ".*")) {
					answer.add(s[i]);
				}
			} else if (end != null) {
				if (curr.matches(".*" + end + "$")) {
					answer.add(s[i]);
				}
			} else {
				if (curr.matches("^" + exact + "$")) {
					answer.add(s[i]);
				}
			}

		}

		if (answer.size() > 0) {
			answer.stream().forEach(System.out::println);
		} else {
			System.out.println("Aucune occurence trouvé.");
		}

		return true;
	}

	@Override
	public void describe() {
		System.out.println("find [-start] [-end]  : Cherche dans le répertoire courant des fichiers commençant et/ou \n"
				+ "terminant le nom spécifié en paramètre ou cherche l'exacte terminologie sinon.");

	}

}
