package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import domain.Categorie;

public class CategorieOracleDaoImpl extends OracleBaseDao implements CategorieDao {

	@Override
	public ArrayList<Categorie> getAllCategorieNaam() {
		ArrayList<Categorie> categorieën = new ArrayList<>();
		try (Connection conn = super.getConnection()) {
			String query = "select id, naam from categorie";
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				Categorie categorie = new Categorie(rs.getInt("id"), rs.getString("naam"));
				categorieën.add(categorie);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return categorieën;
	}

	@Override
	public void saveCategorie(String naam, String omschrijving) {
		try (Connection conn = super.getConnection()) {
			String query = "insert into categorie (naam, omschrijving) values (?, ?)";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, naam);
			pstmt.setString(2, omschrijving);
			pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Categorie getCategorie(int id) {
		Categorie categorie = null;
		
		try (Connection conn = super.getConnection()) {
			String query = "select * from categorie where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				categorie = new Categorie(rs.getInt("id"), rs.getBytes("afbeelding"), rs.getString("naam"), rs.getString("omschrijving"));
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return categorie;
	}

	@Override
	public void deleteCategorie(int id) {
		try (Connection conn = super.getConnection()) {
			String query = "delete from categorie where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateCategorie(int id, String naam, String omschrijving) {
		try (Connection conn = super.getConnection()) {
			String query = "update product set naam = ?, omschrijving = ? where id = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, naam);
			pstmt.setString(2, omschrijving);
			pstmt.setInt(3, id);
			pstmt.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}
