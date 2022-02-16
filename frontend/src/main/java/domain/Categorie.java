package domain;

import java.util.ArrayList;

import persistence.CategorieDao;
import persistence.CategorieOracleDaoImpl;

public class Categorie {
	private int id;
	private byte[] afbeelding;
	private String naam;
	private String omschrijving;
	private ArrayList<Product> producten;

	public Categorie(int id, byte[] afbeelding, String naam, String omschrijving, ArrayList<Product> producten) {
		super();
		this.id = id;
		this.afbeelding = afbeelding;
		this.naam = naam;
		this.omschrijving = omschrijving;
		this.producten = producten;
	}

	public Categorie(int id, String naam) {
		this.id = id;
		this.naam = naam;
	}
	
	public Categorie(int id, byte[] afbeelding, String naam, String omschrijving) {
		this.id = id;
		this.afbeelding = afbeelding;
		this.naam = naam;
		this.omschrijving = omschrijving;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getAfbeelding() {
		return afbeelding;
	}

	public void setAfbeelding(byte[] afbeelding) {
		this.afbeelding = afbeelding;
	}

	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getOmschrijving() {
		return omschrijving;
	}

	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}
	
	public static ArrayList<Categorie> getAllCategorieNamen() {
		CategorieDao cdao = new CategorieOracleDaoImpl();
		return cdao.getAllCategorieNaam();
	}

	public ArrayList<Product> getProducten() {
		return producten;
	}

	public void setProducten(ArrayList<Product> producten) {
		this.producten = producten;
	}
		
	public static void saveCategorie(String naam, String omschrijving) {
		CategorieDao cDao = new CategorieOracleDaoImpl();
		cDao.saveCategorie(naam, omschrijving);
	}

	public static Categorie getCategorie(int id) {
		CategorieDao cdao = new CategorieOracleDaoImpl();
		return cdao.getCategorie(id);
	}
	
	public static void deleteCategorie(int id) {
		CategorieDao cdao = new CategorieOracleDaoImpl();
		cdao.deleteCategorie(id);
	}
	
	public static void updateCategorie(int id, String naam, String omschrijving) {
		CategorieDao cdao = new CategorieOracleDaoImpl();
		cdao.updateCategorie(id, naam, omschrijving);
	}
}
