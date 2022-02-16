package controllers;

import java.util.ArrayList;

import domain.Bestelling;
import domain.Bestellingsregel;

public class SaveBestellingController {
	public static void saveBestelling(int adres_id, String username, ArrayList<Bestellingsregel> bestellingsregels) {
		Bestelling.saveBestelling(adres_id, username, bestellingsregels);
	}
}
