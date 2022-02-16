package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.Bestellingsregel;

public class BestellingOracleDaoImpl extends OracleBaseDao implements BestellingDao{

	@Override
	public void saveBestelling(int adres_id, String username, ArrayList<Bestellingsregel> bestellingsregels) {
		try (Connection conn = super.getConnection()) {
			
			String query = "insert into bestelling (adresid, accountid) values (?, (select id from account where username = ?))";
			String generatedColumns[] = { "ID" };
			PreparedStatement pstmt = conn.prepareStatement(query, generatedColumns);
			pstmt.setInt(1, adres_id);
			pstmt.setString(2, username);
			pstmt.executeUpdate();
			
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			int bestelling_id = rs.getInt(1);
			
			for (Bestellingsregel b : bestellingsregels) {
				String query1 = "insert into bestellingsregel (aantal, prijs, productid, bestellingid) values (?, ?, ?, ?)";
				PreparedStatement pstmt1 = conn.prepareStatement(query1);
				pstmt1.setInt(1, b.getAantal());
				pstmt1.setDouble(2, b.getPrijs());
				pstmt1.setInt(3, b.getProduct().getId());
				pstmt1.setInt(4, bestelling_id);
				pstmt1.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
