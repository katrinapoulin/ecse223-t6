package ca.mcgill.ecse.fmsys.controller;

import ca.mcgill.ecse.fmsys.application.FMSApplication;
import ca.mcgill.ecse.fmsys.model.Airport;
import ca.mcgill.ecse.fmsys.model.FMS;
import ca.mcgill.ecse.fmsys.model.Flight;
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
		} 
		
		else {
			// create the person
			try {
				fms.addPerson(name);
				return "";
				
			} catch(Exception e) {
				return "Error: something went wrong";
			}
		}
	}
	
	public static String createFlight(String fromAirport, String toAirport, String flightNumber) {
		if (fromAirport == null || toAirport == null) {
			return "Error: invalid argument";
		}
		
		Airport fAirport = Airport.getWithCode(fromAirport);
		Airport tAirport = Airport.getWithCode(toAirport);
		
		if (fAirport == null || tAirport == null) {
			return "Error: missing airport";
		} 
		
		else if (fAirport.equals(tAirport)){
			return "Error: fromAirport and toAirport are the same";
		}
		
		if (flightNumber == null || flightNumber.equals("")) {
			return "Error: flight number cannot be empty";
		}
		
		try {
			fms.addFlight(flightNumber, fAirport, tAirport);
			return "";
		} catch(Exception e) {
			return "Something went wrong";
		}
		
	}
	
	public static String bookFlight(String name, String flightNumber) {
		if (name == null || flightNumber == null) {
			return "Error: invalid argument";
		}
		
		// make sure the person exists
		boolean p = Person.hasWithName(name);
		if (!p) {
			return "Error: person does not exist";
		}
		
		// make sure the flight exists
		boolean f = Flight.hasWithFlightNumber(flightNumber);
		if (!f) {
			return "Error: flight does not exist";
		}
		
		try {
			
			Flight flight = Flight.getWithFlightNumber(flightNumber);
			Person person = Person.getWithName(name);
			flight.addPassenger(person);
			return "";
		} catch(Exception e) {
			return "Something went wrong";
		}
	}
	
	
	
	
}
