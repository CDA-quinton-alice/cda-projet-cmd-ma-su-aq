package com.model;

import java.util.ArrayList;

public class Pwd extends Command{
	public Pwd() {
		super("pwd");
	}

	@Override
	public boolean execute() {
		String pwd = System.getProperty("user.dir");
		System.out.println("Chemin actuel : "+pwd);
		return true;
	}

	
	//Camouflage no jutsu !
	@Override
	public boolean execute(ArrayList<String> args) {
		return this.execute();
	}
	
	
	@Override
	public void describe() {
		System.out.println("Je suis la commande Pwd");
	}

	
}
