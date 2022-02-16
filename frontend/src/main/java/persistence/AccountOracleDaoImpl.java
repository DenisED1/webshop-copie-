package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountOracleDaoImpl extends OracleBaseDao implements AccountDao {

	@Override
	public boolean checkCredentials(String username, String password) {
		boolean uitkomst = false;
		try (Connection conn = super.getConnection()) {
			String query = "select count(id) from account where username =? and password =?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			if (rs.getInt(1) == 1) {
				uitkomst = true;
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uitkomst;
		
	}

	@Override
	public String getRole(String username) {
		String uitkomst = null;
		try (Connection conn = super.getConnection()) {
			String query = "select role from account where username = ?";
			PreparedStatement pstmt = conn.prepareStatement(query);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			uitkomst = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return uitkomst;
	}
	
}
