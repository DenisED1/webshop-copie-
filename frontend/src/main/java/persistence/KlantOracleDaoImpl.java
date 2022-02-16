package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import domain.Adres;
import domain.Klant;

public class KlantOracleDaoImpl extends OracleBaseDao implements KlantDao {

	@Override
	public void SaveKlant(String straat, int huisnummer, String toevoeging, String postcode, String plaats, String naam,
			Date opendatum, String username, String password, String role) {
			
		try (Connection conn = super.getConnection()) {
			String query = "insert into adres (straat, huisnummer, toevoeging, postcode, plaats) values (?, ?, ?, ?, ?)";
			String generatedColumns[] = { "ID" };
			PreparedStatement pstmt = conn.prepareStatement(query, generatedColumns);
			pstmt.setString(1, straat);
			pstmt.setInt(2, huisnummer);
			pstmt.setString(3, toevoeging);
			pstmt.setString(4, postcode);
			pstmt.setString(5, plaats);
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			int adres_id = rs.getInt(1);
			
			String query2 = "insert into klant (naam, woonadres) values (?, ?)";
			String generatedColumns2[] = { "ID" };
			PreparedStatement pstmt2 = conn.prepareStatement(query2, generatedColumns2);
			pstmt2.setString(1, naam);
			pstmt2.setInt(2, adres_id);
			pstmt2.executeUpdate();
			
			ResultSet rs1 = pstmt2.getGeneratedKeys();
			rs1.next();
			int klant_id = rs1.getInt(1);
			
			String query3 = "insert into account (opendatum, isactief, username, password, role, klantid, factuuradres) values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt3 = conn.prepareStatement(query3);
			pstmt3.setDate(1, opendatum);
			String isactief = "Y";
			pstmt3.setString(2, isactief);
			pstmt3.setString(3, username);
			pstmt3.setString(4, password);
			pstmt3.setString(5, role);
			pstmt3.setInt(6, klant_id);
			pstmt3.setInt(7, adres_id);
			pstmt3.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public Klant getKlantGegevens(String username) {
		Klant klant = null;
		
		try (Connection conn = super.getConnection()) {
			String query = "select k.* from klant k, account a where k.id = a.klantid and a.username = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			
			AdresDao adao = new AdresOracleDaoImpl();
			
			while (rs.next()) {
				int id = rs.getInt("id");
				String naam = rs.getString("naam");
				int adresid = rs.getInt("woonadres");
				
				Adres adres = adao.getAdres(adresid);
				klant = new Klant(id, naam, adres);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return klant;
	}

}
