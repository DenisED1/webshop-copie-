package controllers;

import domain.Account;

public class CheckCredentialsController {
	public static boolean checkCredentials(String username, String password) {
		return Account.checkCredentials(username, password);
	}
}
