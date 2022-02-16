package persistence;

import java.util.ArrayList;

import domain.Bestellingsregel;
public interface BestellingDao {
	public void saveBestelling(int adres_id, String username, ArrayList<Bestellingsregel> bestellingsregels);

}
