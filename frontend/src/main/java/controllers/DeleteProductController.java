package controllers;

import domain.Product;

public class DeleteProductController {
	public static void deleteProduct(int id) {
		Product.deleteProduct(id);
	}
}
