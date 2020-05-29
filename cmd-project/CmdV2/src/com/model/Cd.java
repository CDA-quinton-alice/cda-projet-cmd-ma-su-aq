package com.model;

import java.io.File;
import java.util.ArrayList;

import com.manager.CmdV2;

public class Cd extends Command{

	public Cd() {
		super("cd");
	}

	@Override
	public boolean execute() {
		this.describe();
		return true;
	}

	@Override
	public boolean execute(ArrayList<String> args) {
		String str = args.get(0);
		String curr = CmdV2.getPwd();
		File f = new File(str);
		if(f.exists()) {
			 if((!isRoot(curr)&&!isPoints(str))||(isRoot(curr)&&!isPoints(str))){
				 CmdV2.setPwd(str);
			 }else if(!isRoot(curr)&&isPoints(str)) {
				 if(isLastBackSlash(curr)) {
					 curr = curr.substring(0, str.length()-1);
				 }
				 curr = curr.substring(0, curr.lastIndexOf("\\"));
				 File f2 = new File(curr);
				 if(f2.exists()) {
					 CmdV2.setPwd(curr);
				 }else {
					 System.out.println("Erreur lors du d�placement en amont des fichiers.");
				 }
				 
			 }else {
				 System.out.println("Vous ne pouvez pas remonter plus haut que le dossier racine !");
			 }
		}else {
			System.out.println("Le chemin plac� en param�tre n'est pas valide.");
		}
		return true;
	}

	@Override
	public void describe() {
		System.out.println("cd [param] : changer le dossier courant pour celui plac� en paramètre.");
	}

	public static boolean isPoints(String s) {
		return s.matches("^\\.\\.");
	}
	
	public static boolean isRoot(String s) {
		s = s.toLowerCase();
		return s.matches("[a-zA-Z]:\\\\")||s.matches("[a-zA-Z]:/");
	}
	
	public static boolean isLastBackSlash(String s) {
		int liob = s.lastIndexOf("\\");
		if(liob == s.length()-1) return true;
		else return false;
	}
}
