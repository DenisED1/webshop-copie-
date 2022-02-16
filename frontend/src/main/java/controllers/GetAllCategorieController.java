package controllers;

import java.util.ArrayList;

import domain.Categorie;

public class GetAllCategorieController {

	public static ArrayList<Categorie> getAllCategorieNamen() {
		return Categorie.getAllCategorieNamen();
	}

}
