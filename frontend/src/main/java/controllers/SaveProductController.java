package controllers;

import domain.Product;

public class SaveProductController {
	public static void saveProduct(String naam, double prijs, String omschrijving) {
		Product.saveProduct(naam, prijs, omschrijving);
	}
}
