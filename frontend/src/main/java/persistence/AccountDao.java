package persistence;

public interface AccountDao {
	public boolean checkCredentials(String username, String password);

	public String getRole(String username);
}
