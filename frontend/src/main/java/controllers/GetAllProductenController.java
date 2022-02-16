package controllers;

import java.util.ArrayList;

import domain.Aanbieding;
import domain.Product;

public class GetAllProductenController {
	public static ArrayList<Aanbieding> getAllProducten() {
		return Product.getAllProducten();
	}
}
