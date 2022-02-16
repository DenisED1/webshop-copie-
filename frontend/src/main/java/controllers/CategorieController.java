package controllers;

import java.util.ArrayList;

import domain.Categorie;

public class CategorieController {
	public static Categorie getCategorie(int id) {
		return Categorie.getCategorie(id);
	}
}
