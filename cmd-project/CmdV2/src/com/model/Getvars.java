package com.model;

import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

public class Getvars extends Command {

	public Getvars() {
		super("getvars");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute() {
		Map<String, String> mapEnv = System.getenv();
		for (Entry<String, String> mapEntry : mapEnv.entrySet()) {
			System.out.println(mapEntry.getKey() + "\t\t\t" + mapEntry.getValue());
		}
		System.out.println("--------------------------------------------------------------");
		Map<Object, Object> mapProp = System.getProperties();
		for (Entry<Object, Object> mapEntry : mapProp.entrySet()) {
			System.out.println(mapEntry.getKey() + "\t\t\t" + mapEntry.getValue());
		}
		return true;
	}

	@Override
	public boolean execute(ArrayList<String> args) {
		String vParam = args.get(0);
		if (vParam.equalsIgnoreCase("-env")) {
			Map<String, String> mapEnv = System.getenv();
			for (Entry<String, String> mapEntry : mapEnv.entrySet()) {
				System.out.println(mapEntry.getKey() + "\t\t\t" + mapEntry.getValue());
			}

		} else if (vParam.equalsIgnoreCase("-prop")) {
			Map<Object, Object> mapProp = System.getProperties();
			for (Entry<Object, Object> mapEntry : mapProp.entrySet()) {
				System.out.println(mapEntry.getKey() + "\t\t\t" + mapEntry.getValue());
			}
		} else {
			System.out.println("Param�tre inconnu");
		}
		return true;
	}

	@Override
	public void describe() {
		System.out.println("getvars : Affiche les variables d'environnement et les propri�t�s de la JVM.");
		System.out.println("getvars [-env] : Affiche les variables d'environnement.");
		System.out.println("getvars [-prop] : Affiche les propri�t�s de la JVM.");
	}

}