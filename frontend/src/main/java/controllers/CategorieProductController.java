package controllers;

import java.util.ArrayList;

import domain.Product;

public class CategorieProductController {

	public static ArrayList<Product> getCategorieProducten(int id) {
		return Product.getCategorieProducten(id);
	}

}
