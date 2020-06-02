package com.model;

import java.util.ArrayList;

import com.manager.CmdV2;

public class Pwd extends Command implements INonHistory{
	
	public Pwd() {
		super("pwd");
	}

	@Override
	public boolean execute() {
		System.out.println("Chemin actuel : "+CmdV2.getPwd());
		return true;
	}

	
	//Camouflage no jutsu !
	@Override
	public boolean execute(ArrayList<String> args) {
		return this.execute();
	}
	
	
	@Override
	public void describe() {
		System.out.println("pwd : affiche le répertoire courant.");
	}

	
}
