package controllers;

import domain.Categorie;

public class DeleteCategorieController {
	public static void deleteProduct(int id) {
		Categorie.deleteCategorie(id);
	}
}
