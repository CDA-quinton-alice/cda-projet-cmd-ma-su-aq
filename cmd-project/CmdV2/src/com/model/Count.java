package com.model;

import java.io.File;
import java.util.ArrayList;

import com.manager.CmdV2;

public class Count extends Command {

	public Count() {
		super("count");
	}

	@Override
	public boolean execute() {
		File curr = new File(CmdV2.getPwd());
		String[] fichiers = curr.list();
		int nbF = 0;
		int nbD = 0;
		for (int k = 0; k < fichiers.length; k++) {
			File tmp = new File(fichiers[k]);
			if (tmp.isFile()) {
				nbF++;
			} else {
				nbD++;
			}
		}

		System.out.println(nbF + " fichiers");
		System.out.println(nbD + " dossiers");
		return true;
	}

	@Override
	public boolean execute(ArrayList<String> args) {
		ArrayList<String> files = new ArrayList<>();
		return true;
	}

	@Override
	public void describe() {
		System.out.println("count [-r");
	}

}
