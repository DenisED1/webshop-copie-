package persistence;

import java.util.ArrayList;

import domain.Aanbieding;

public interface ProductDao {

	Aanbieding getProduct(int id);

	public ArrayList<Aanbieding> getAllProducten();

	public void saveProduct(String naam, double prijs, String omschrijving);
	
	public void deleteProduct(int id);
	
	public void updateProduct(int id, String naam, double prijs, String omschrijving);
}
