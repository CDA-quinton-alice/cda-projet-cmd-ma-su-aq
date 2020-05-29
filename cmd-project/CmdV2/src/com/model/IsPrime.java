package com.model;

import java.util.ArrayList;

public class IsPrime extends Command {

	public IsPrime() {
		super("isprime");
	}

	@Override
	public boolean execute() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean execute(ArrayList<String> args) {
		int vNbaTester = Integer.parseInt(args.get(0));
		int tmp = 0;
		boolean trouve = true;

		if (vNbaTester == 0 || vNbaTester == 1) {
			System.out.println("no");
		} else {
			tmp = vNbaTester / 2;

			for (int i = 2; i <= tmp; i++) {
				if (vNbaTester % i == 0) {
					System.out.println("no");
					trouve = false;
					break;
				}
			}
			if (trouve) {
				System.out.println("yes");
			}
		}
		return true;
	}

	@Override
	public void describe() {
		// TODO Auto-generated method stub

	}

}
