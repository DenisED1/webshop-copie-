package persistence;

import java.util.ArrayList;

import domain.Categorie;

public interface CategorieDao {

	public ArrayList<Categorie> getAllCategorieNaam();
	public void saveCategorie(String naam, String omschrijving);
	public Categorie getCategorie(int id);
	public void deleteCategorie(int id);
	public void updateCategorie(int id, String naam, String omschrijving);
}
