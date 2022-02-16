package controllers;

import domain.Klant;

public class GetKlantGegevensController {

	public static Klant getKlantGegevens(String username) {
		return Klant.getKlantGegevens(username);
	}

}
