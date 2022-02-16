package controllers;

import java.sql.Date;

import domain.Klant;

public class SaveKlantController {
	public static void SaveKlant(String straat, int huisnummer, String toevoeging, String postcode, String plaats, String naam,
			Date opendatum, String username, String password, String role) {
		Klant.saveKlant(straat, huisnummer, toevoeging, postcode, plaats, naam, opendatum, username, password, role);
	}
}
