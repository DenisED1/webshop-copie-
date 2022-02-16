package domain;

import java.util.ArrayList;

import persistence.BestellingDao;
import persistence.BestellingOracleDaoImpl;

public class Bestelling {
	private int id;
	private Bestellingsregel bestellingsregel;
	private Adres adres;

	public Bestelling(int id, Bestellingsregel bestellingsregel, Adres adres) {
		super();
		this.id = id;
		this.bestellingsregel = bestellingsregel;
		this.adres = adres;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Bestellingsregel getBestellingsregel() {
		return bestellingsregel;
	}

	public void setBestellingsregel(Bestellingsregel bestellingsregel) {
		this.bestellingsregel = bestellingsregel;
	}

	public Adres getAdres() {
		return adres;
	}

	public void setAdres(Adres adres) {
		this.adres = adres;
	}
	
	public static void saveBestelling(int adres_id, String username, ArrayList<Bestellingsregel> bestellingsregels) {
		BestellingDao bDao = new BestellingOracleDaoImpl();
		bDao.saveBestelling(adres_id, username, bestellingsregels);
	}

}
