package domain;

import java.sql.Date;
import java.util.ArrayList;

import persistence.AccountDao;
import persistence.AccountOracleDaoImpl;

public class Account {
	private int id;
	private Date openDatum;
	private boolean isActief;
	private String username;
	private String password;
	private String role;
	private Adres adres;
	private ArrayList<Bestelling> bestellingen;

	public Account(int id, Date openDatum, boolean isActief, String username, String password, String role, Adres adres,
			ArrayList<Bestelling> bestellingen) {
		super();
		this.id = id;
		this.openDatum = openDatum;
		this.isActief = isActief;
		this.username = username;
		this.password = password;
		this.role = role;
		this.adres = adres;
		this.bestellingen = bestellingen;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getOpenDatum() {
		return openDatum;
	}

	public void setOpenDatum(Date openDatum) {
		this.openDatum = openDatum;
	}

	public boolean isActief() {
		return isActief;
	}

	public void setActief(boolean isActief) {
		this.isActief = isActief;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Adres getAdres() {
		return adres;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}

	public ArrayList<Bestelling> getBestellingen() {
		return bestellingen;
	}

	public void setBestellingen(ArrayList<Bestelling> bestellingen) {
		this.bestellingen = bestellingen;
	}
	
	public static boolean checkCredentials(String username, String password) {
		AccountDao aDao = new AccountOracleDaoImpl();
		return aDao.checkCredentials(username, password);
	}

	public static String getRole(String username) {
		AccountDao aDao = new AccountOracleDaoImpl();
		return aDao.getRole(username);
	}

}
