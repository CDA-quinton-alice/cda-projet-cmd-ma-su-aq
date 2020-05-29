package com.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.manager.CmdV2;

public class History extends Command implements INonHistory{
	private ArrayList<String> history = new ArrayList<>();
	private final static int MAX_LOG = 10;
	
	public History() {
		super("history");
		
	}

	//GETTER
	public ArrayList<String> getHistory() {
		return history;
	}
	
	public static int getMaxLog() {
		return MAX_LOG;
	}

	//SETTER
	public void setHistory(ArrayList<String> history) {
		this.history = history;
	}
	
	
	public void ajouterCommande(String cmd, ArrayList<String> args) {
		Command c = CmdV2.getCommandByName(cmd);
		if(c != null) {
			if(!(c instanceof INonHistory)) {
				SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss dd/MM/yyyy");
				Date curr = Calendar.getInstance().getTime();
				StringBuilder str = new StringBuilder();
				str.append(sdf.format(curr)).append(" : ").append(cmd);
				if(args != null) {
					for (String string : args) {
						str.append(" ").append(string);
					}
				}
				if(history.size() == MAX_LOG) {
					history.remove(0);
				}
				history.add(str.toString());	
			}
		}
	}
	
	@Override
	public boolean execute() {
		history.stream().forEach(System.out::println);
		return true;
	}

	@Override
	public boolean execute(ArrayList<String> args) {
		this.execute();
		return true;
	}

	@Override
	public void describe() {
		System.out.println("history : Affiche la liste des 10 dernières commandes avec les potentiels paramètres et la date d'éxecution.");
	}

}
