package com.model;

import java.util.ArrayList;

public class Exit extends Command{

	public Exit() {
		super("exit");
	}

	@Override
	public boolean execute() {
		System.out.println("Au revoir !");
		return false; // return false car c'est la commande pour quitter le programme
	}

	//Camouflage no jutsu !
	@Override
	public boolean execute(ArrayList<String> args) {
		return this.execute();
	}
	
	@Override
	public void describe() {
		System.out.println("Je suis la commande Exit");
		
	}


}
