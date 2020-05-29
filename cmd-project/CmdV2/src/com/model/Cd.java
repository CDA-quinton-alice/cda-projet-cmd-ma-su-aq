package com.model;

import java.util.ArrayList;

public class Cd extends Command{

	public Cd() {
		super("cd");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean execute() {
		this.describe();
		return true;
	}

	@Override
	public boolean execute(ArrayList<String> args) {
		String str = args.get(0);
		if(str.equals("..")) {
			
		}
		return false;
	}

	@Override
	public void describe() {
		// TODO Auto-generated method stub
		
	}

}
