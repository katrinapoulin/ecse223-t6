package ca.mcgill.ecse.fmsys.application;

import ca.mcgill.ecse.fmsys.model.FMS;

public class FMSApplication {
	private static FMS fms;
	
	public static void main(String[] args) {
		fms = getFMS();
	}
	
	public static FMS getFMS() {
		if (fms == null) {
			fms = new FMS();
		}
		return fms;
	}
}
