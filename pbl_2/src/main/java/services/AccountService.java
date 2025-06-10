package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Account;
import utils.Db;

public class AccountService {
	public List<Account> searchAccounts(String name, String email, String[] authorities) {
		List<Account> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder("SELECT * FROM accounts WHERE 1=1");
		List<Object> params = new ArrayList<>();

		if (name != null && !name.isEmpty()) {
			sql.append(" AND name LIKE ?");
			params.add("%" + name + "%");
		}
		if (email != null && !email.isEmpty()) {
			sql.append(" AND mail LIKE ?");
			params.add("%" + email + "%");
		}
		if (authorities != null && authorities.length > 0) {
			sql.append(" AND authority IN (");
			sql.append("?,".repeat(authorities.length));
			sql.setLength(sql.length() - 1);
			sql.append(")");
			for (String auth : authorities) {
				params.add(Integer.parseInt(auth));
			}
		}

		try (Connection con = Db.open();
				PreparedStatement stmt = con.prepareStatement(sql.toString())) {
			for (int i = 0; i < params.size(); i++) {
				stmt.setObject(i + 1, params.get(i));
			}

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Account a = new Account(
						rs.getInt("account_id"),
						rs.getString("name"),
						rs.getString("mail"),
						rs.getString("password"),
						rs.getBytes("authority"));
				list.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

}
