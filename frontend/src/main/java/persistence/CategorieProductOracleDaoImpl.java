package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.Product;

public class CategorieProductOracleDaoImpl extends OracleBaseDao implements CategorieProductDao{

	@Override
	public ArrayList<Product> getCategorieProducten(int id) {
		ArrayList<Product> categorieProducten = new ArrayList<>();
		try (Connection conn = super.getConnection()) {
			String query = "select p.* from product p, product_categorie pc where p.id = pc.productid and pc.categorieid = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Product product = new Product(rs.getInt("id"), rs.getString("naam"), rs.getDouble("prijs"),
						rs.getBytes("afbeelding"), rs.getString("omschrijving"));
				categorieProducten.add(product);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return categorieProducten;
	}

}
