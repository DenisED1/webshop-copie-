package domain;

import java.sql.Date;
import java.util.ArrayList;

import persistence.AanbiedingDao;
import persistence.AanbiedingOracleDaoImpl;

public class Aanbieding {
	private int id;
	private Date vanDatum;
	private Date totDatum;
	private String reclametext;
	private double aanbiedingsprijs;
	private Product product;

	public Aanbieding(int id, Date vanDatum, Date totDatum, String reclametext, double aanbiedingsprijs,
			Product product) {
		super();
		this.id = id;
		this.vanDatum = vanDatum;
		this.totDatum = totDatum;
		this.reclametext = reclametext;
		this.aanbiedingsprijs = aanbiedingsprijs;
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Aanbieding(Product product) {
		super();
		this.product = product;
	}

	public Date getVanDatum() {
		return vanDatum;
	}

	public void setVanDatum(Date vanDatum) {
		this.vanDatum = vanDatum;
	}

	public Date getTotDatum() {
		return totDatum;
	}

	public void setTotDatum(Date totDatum) {
		this.totDatum = totDatum;
	}

	public String getReclametext() {
		return reclametext;
	}

	public void setReclametext(String reclametext) {
		this.reclametext = reclametext;
	}

	public double getAanbiedingsprijs() {
		return aanbiedingsprijs;
	}

	public void setAanbiedingsprijs(double aanbiedingsprijs) {
		this.aanbiedingsprijs = aanbiedingsprijs;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public static ArrayList<Aanbieding> getAllAanbiedingen() {
		AanbiedingDao adao = new AanbiedingOracleDaoImpl();
		return adao.getAllAanbiedingen();
	}
}
