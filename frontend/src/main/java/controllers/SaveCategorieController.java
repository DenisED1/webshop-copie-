package controllers;

import domain.Categorie;

public class SaveCategorieController {
	public static void saveCategorie(String naam, String omschrijving) {
		Categorie.saveCategorie(naam, omschrijving);
	}
}
