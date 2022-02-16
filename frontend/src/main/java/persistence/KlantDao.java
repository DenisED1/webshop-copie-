package persistence;

import java.sql.Date;

import domain.Klant;

public interface KlantDao {
	public void SaveKlant(String straat, int huisnummer, String toevoeging, String postcode, String plaats, String naam,
			Date opendatum, String username, String password, String role);

	public Klant getKlantGegevens(String username);
}
