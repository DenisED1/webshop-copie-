package domain;

import java.sql.Date;

import persistence.KlantDao;
import persistence.KlantOracleDaoImpl;

public class Klant {
	private int id;
	private String naam;
	private Account account;
	private Adres adres;

	public Klant(int id, String naam, Account account, Adres adres) {
		super();
		this.id = id;
		this.naam = naam;
		this.account = account;
		this.adres = adres;
	}

	public Klant(int id, String naam, Adres adres) {
		this.id = id;
		this.naam = naam;
		this.adres = adres;
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Adres getAdres() {
		return adres;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}

	public static void saveKlant(String straat, int huisnummer, String toevoeging, String postcode, String plaats,
			String naam, Date opendatum, String username, String password, String role) {
		KlantDao kDao = new KlantOracleDaoImpl();
		kDao.SaveKlant(straat, huisnummer, toevoeging, postcode, plaats, naam, opendatum, username, password, role);
	}

	public static Klant getKlantGegevens(String username) {
		KlantDao kdao = new KlantOracleDaoImpl();
		return kdao.getKlantGegevens(username);
	}
}
