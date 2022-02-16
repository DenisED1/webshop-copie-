package controllers;

import domain.Categorie;

public class UpdateCategorieController {
	public static void updateCategorie(int id, String naam, String omschrijving) {
		Categorie.updateCategorie(id, naam, omschrijving);
	}
}
