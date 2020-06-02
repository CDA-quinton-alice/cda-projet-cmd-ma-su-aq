package com.model;

import java.io.File;
import java.util.ArrayList;

import com.manager.CmdV2;

public class Dir extends Command implements INonHistory {

	public Dir() {
		super("dir");
	}

	@Override
	public boolean execute() {
		String vPath = CmdV2.getPwd();
		File vFile = new File(vPath);
		for (File vFileTmp : vFile.listFiles()) { // Parcourir tout le contenu de C, affiche fonction type
			if (vFileTmp.isDirectory()) {
				System.out.println("<DIR>  " + vFileTmp.getName());
			} else if (vFileTmp.isFile()) {
				System.out.println("		" + vFileTmp.getName());
			}
		}
		return true;
	}

	@Override
	public boolean execute(ArrayList<String> args) {
		return this.execute();
	}

	@Override
	public void describe() {
		System.out.println("dir : Affiche les fichiers et répertoires contenus dans C:\\.");
		System.out.println("dir [pwd] : Affiche les fichiers et répertoires contenus dans le répertoire courant");
	}

}
