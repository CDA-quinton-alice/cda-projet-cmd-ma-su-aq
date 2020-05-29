package com.model;

import java.io.File;
import java.util.ArrayList;

import com.manager.CmdV2;

public class Dir extends Command implements INonHistory{

	public Dir() {
		super("dir");
	}

	@Override
	public boolean execute() {
		String vPath = System.getProperty("user.home");
		File vFile = new File(vPath);
		File vReps = vFile.getParentFile().getParentFile(); // Permet de remonter au C:\\
		for (File vFileTmp : vReps.listFiles()) { // Parcourir tout le contenu de C, affiche fonction type
			if (vFileTmp.isDirectory()) {
				System.out.println("<DIR>  " + vFileTmp.getName());
			} else if (vFileTmp.isFile()) {
				System.out.println("		" + vFileTmp.getName());
			}
		}
		return true;
	}

	@Override
	public boolean execute(ArrayList<String> args) { // Dir avec un param�tre pour afficher le contenu du rep courant
		String vParam = args.get(0);
		if (vParam.equalsIgnoreCase("pwd")) {
			String vPath = CmdV2.getPwd();
			File vFile = new File(vPath);
			for (File vFileTmp : vFile.listFiles()) {
				if (vFileTmp.isDirectory()) {
					System.out.println("<DIR>  " + vFileTmp.getName());
				} else if (vFileTmp.isFile()) {
					System.out.println("		" + vFileTmp.getName());
				}
			}
		} else {
			return this.execute();
		}
		return true;
	}

	@Override
	public void describe() {
		System.out.println("dir : Affiche les fichiers et repertoires contenus dans C:\\.");
		System.out.println("dir [pwd] : Affiche les fichiers et repertoires contenus dans le repertoire courant");
	}

}
