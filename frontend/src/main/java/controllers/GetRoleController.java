package controllers;

import domain.Account;

public class GetRoleController {
	public static String getRole(String username) {
		return Account.getRole(username);
	}
}
