package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Aanbieding;
import domain.Product;

public class AanbiedingOracleDaoImpl extends OracleBaseDao implements AanbiedingDao {
	public ArrayList<Aanbieding> getAllAanbiedingen() {
		List<Aanbieding> aanbiedingen = new ArrayList<>();
		try (Connection conn = super.getConnection()) {
			String query = "select p.*, a.id as aid, a.vandatum, a.totdatum, a.reclametext, a.aanbiedingsprijs, a.productid from product p, aanbieding a where a.productid = p.id and SYSDATE > a.vandatum and SYSDATE < a.totdatum";
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Product product = new Product(rs.getInt("id"), rs.getString("naam"), rs.getDouble("prijs"), rs.getBytes("afbeelding"),
						rs.getString("omschrijving"));
				Aanbieding aanbieding = new Aanbieding(rs.getInt("aid"), rs.getDate("vandatum"), rs.getDate("totdatum"),
						rs.getString("reclametext"), rs.getDouble("aanbiedingsprijs"), product);
				aanbiedingen.add(aanbieding);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}

		return (ArrayList<Aanbieding>) aanbiedingen;
	}
}
