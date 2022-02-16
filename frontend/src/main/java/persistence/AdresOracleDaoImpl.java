package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Adres;

public class AdresOracleDaoImpl extends OracleBaseDao implements AdresDao{

	@Override
	public Adres getAdres(int adresid) {
		Adres adres = null;
		
		try (Connection conn = super.getConnection()) {
			String query = "select * from adres where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, adresid);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				adres = new Adres(rs.getInt("id"), rs.getString("straat"), rs.getInt("huisnummer"), rs.getString("toevoeging"), rs.getString("postcode"), rs.getString("plaats"));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return adres;
	}

}
