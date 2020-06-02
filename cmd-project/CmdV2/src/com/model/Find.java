package com.model;

import java.util.ArrayList;

public class Find extends Command{

	public Find() {
		super("find");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean execute(ArrayList<String> args) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void describe() {
		System.out.println("find [-start] [-end] : recherche parmis le dossier courant les fichiers ");
		
	}

}
