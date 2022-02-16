package domain;

import java.util.ArrayList;

import persistence.CategorieProductDao;
import persistence.CategorieProductOracleDaoImpl;
import persistence.ProductDao;
import persistence.ProductOracleDaoImpl;

public class Product {
	private int id;
	private String naam;
	private double prijs;
	private byte[] afbeelding; // nog onderzoek naar doen
	private String omschrijving;

	public Product(int id, String naam, double prijs, byte[] afbeelding, String omschrijving) {
		super();
		this.id = id;
		this.naam = naam;
		this.prijs = prijs;
		this.afbeelding = afbeelding;
		this.omschrijving = omschrijving;
	}

	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNaam() {
		return naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public double getPrijs() {
		return prijs;
	}

	public void setPrijs(double prijs) {
		this.prijs = prijs;
	}

	public byte[] getAfbeelding() {
		return afbeelding;
	}

	public void setAfbeelding(byte[] afbeelding) {
		this.afbeelding = afbeelding;
	}

	public String getOmschrijving() {
		return omschrijving;
	}

	public void setOmschrijving(String omschrijving) {
		this.omschrijving = omschrijving;
	}
	public static ArrayList<Aanbieding> getAllProducten() {
		ProductDao pDao = new ProductOracleDaoImpl();
		return pDao.getAllProducten();
	}
	
	public static Aanbieding getProduct(int id) {
		ProductDao pDao = new ProductOracleDaoImpl();
		return pDao.getProduct(id);
	}
	public static void saveProduct(String naam, double prijs, String omschrijving) {
		ProductDao pDao = new ProductOracleDaoImpl();
		pDao.saveProduct(naam, prijs, omschrijving);
	}

	public static ArrayList<Product> getCategorieProducten(int id) {
		CategorieProductDao cpDao = new CategorieProductOracleDaoImpl();
		return cpDao.getCategorieProducten(id);
	}
	
	public static void deleteProduct(int id) {
		ProductDao pDao = new ProductOracleDaoImpl();
		pDao.deleteProduct(id);
	}
	
	public static void updateProduct(int id, String naam, double prijs, String omschrijving) {
		ProductDao pDao = new ProductOracleDaoImpl();
		pDao.updateProduct(id, naam, prijs, omschrijving);
	}

}
