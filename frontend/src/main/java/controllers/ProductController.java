package controllers;

import domain.Aanbieding;
import domain.Product;

public class ProductController {
	public static Aanbieding getProduct(int id) {
		return Product.getProduct(id);
	}
}
