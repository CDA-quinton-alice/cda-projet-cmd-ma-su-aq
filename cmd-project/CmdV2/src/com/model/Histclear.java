package com.model;

import java.util.ArrayList;

import com.manager.CmdV2;

public class Histclear extends Command implements INonHistory{

	public Histclear() {
		super("histclear");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute() {
		((History)CmdV2.getCommandByName("history")).setHistory(new ArrayList<>());
		return true;
	}

	@Override
	public boolean execute(ArrayList<String> args) {
		this.execute();
		return true;
	}

	@Override
	public void describe() {
		System.out.println("histclear : Efface l'historique des commandes conservé.");
		
	}

}
