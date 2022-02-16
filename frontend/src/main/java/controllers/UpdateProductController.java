package controllers;

import domain.Product;

public class UpdateProductController {
	public static void updateProduct(int id, String naam, double prijs, String omschrijving) {
		Product.updateProduct(id, naam, prijs, omschrijving);
	}
}
