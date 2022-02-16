package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import domain.Aanbieding;
import domain.Product;

public class ProductOracleDaoImpl extends OracleBaseDao implements ProductDao {

	@Override
	public ArrayList<Aanbieding> getAllProducten() {
		ArrayList<Aanbieding> producten = new ArrayList<Aanbieding>();
		try (Connection conn = super.getConnection()) {
			String query = "select p.*, a.id as aid, a.vandatum, a.totdatum, a.reclametext, a.aanbiedingsprijs, a.productid from product p left join aanbieding a on productid = p.id";
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			Date date = Date.valueOf(LocalDate.now());
			while (rs.next()) {
				if (rs.getString("aanbiedingsprijs") != null) {
					if (rs.getDate("vandatum").before(date) && rs.getDate("totdatum").after(date)) {
						Product product = new Product(rs.getInt("id"), rs.getString("naam"), rs.getDouble("prijs"),
								rs.getBytes("afbeelding"), rs.getString("omschrijving"));
						Aanbieding aanbieding = new Aanbieding(rs.getInt("aid"), rs.getDate("vandatum"),
								rs.getDate("totdatum"), rs.getString("reclametext"), rs.getDouble("aanbiedingsprijs"),
								product);
						producten.add(aanbieding);
					} else {
						Product product = new Product(rs.getInt("id"), rs.getString("naam"), rs.getDouble("prijs"),
								rs.getBytes("afbeelding"), rs.getString("omschrijving"));
						Aanbieding aanbieding = new Aanbieding(product);
						producten.add(aanbieding);
					}
				} else {
					Product product = new Product(rs.getInt("id"), rs.getString("naam"), rs.getDouble("prijs"),
							rs.getBytes("afbeelding"), rs.getString("omschrijving"));
					Aanbieding aanbieding = new Aanbieding(product);
					producten.add(aanbieding);
				}
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return producten;
	}

	@Override
	public Aanbieding getProduct(int id) {
		Aanbieding aanbieding = null;
		try (Connection conn = super.getConnection()) {
			String query = "select p.*, a.id as aid, a.vandatum, a.totdatum, a.reclametext, a.aanbiedingsprijs, a.productid from product p left join aanbieding a on productid = p.id where p.id = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			Date date = Date.valueOf(LocalDate.now());
			rs.next();

			if (rs.getString("aanbiedingsprijs") != null) {
				if (rs.getDate("vandatum").before(date) && rs.getDate("totdatum").after(date)) {
					Product product = new Product(rs.getInt("id"), rs.getString("naam"), rs.getDouble("prijs"),
							rs.getBytes("afbeelding"), rs.getString("omschrijving"));
					aanbieding = new Aanbieding(rs.getInt("aid"), rs.getDate("vandatum"), rs.getDate("totdatum"),
							rs.getString("reclametext"), rs.getDouble("aanbiedingsprijs"), product);
				} else {
					Product product = new Product(rs.getInt("id"), rs.getString("naam"), rs.getDouble("prijs"),
							rs.getBytes("afbeelding"), rs.getString("omschrijving"));
					aanbieding = new Aanbieding(product);
				}
			} else {
				Product product = new Product(rs.getInt("id"), rs.getString("naam"), rs.getDouble("prijs"),
						rs.getBytes("afbeelding"), rs.getString("omschrijving"));
				aanbieding = new Aanbieding(product);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return aanbieding;
	}

	@Override
	public void saveProduct(String naam, double prijs, String omschrijving) {
		try (Connection conn = super.getConnection()) {
			String query = "insert into product (naam, prijs, omschrijving) values (?, ?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, naam);
			pstmt.setDouble(2, prijs);
			pstmt.setString(3, omschrijving);
			pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteProduct(int id) {
		try (Connection conn = super.getConnection()) {
			String query = "delete from product where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateProduct(int id, String naam, double prijs, String omschrijving) {
		try (Connection conn = super.getConnection()) {
			String query = "update product set naam = ?, prijs = ?, omschrijving = ? where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, naam);
			pstmt.setDouble(2, prijs);
			pstmt.setString(3, omschrijving);
			pstmt.setInt(4, id);
			pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
