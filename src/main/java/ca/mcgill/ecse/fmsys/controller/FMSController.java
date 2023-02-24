package ca.mcgill.ecse.fmsys.controller;

import ca.mcgill.ecse.fmsys.application.FMSApplication;
import ca.mcgill.ecse.fmsys.model.FMS;
import ca.mcgill.ecse.fmsys.model.Person;

public class FMSController {

	//1. Instance of FMS object
	private static FMS fms = FMSApplication.getFMS();
	
	//2. Controller methods (static)
		// 2.1 get objects from model
		// 2.2 do stuff on objects
	
	public static String createPerson(String name) {
		// input validation
		if (name == null || name.equals("")) {
			return "Error: empty name";
		}
		
		// check if there is someone with that name already
		boolean p = Person.hasWithName(name);
		if (p) {
			return "Error: A person with that name already exists.";
		} else {
			// create the person
			try {
				fms.addPerson(name);
				return "";
			} catch(Exception e) {
				return "Error: something went wrong";
			}
		}
	}
}
