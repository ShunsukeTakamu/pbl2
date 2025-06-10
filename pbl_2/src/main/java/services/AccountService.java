package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import beans.Account;
import utils.Db;

public class AccountService {

	// アカウント条件検索
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
			boolean containsZero = false;
			int requiredBits = 0;

			for (String auth : authorities) {
				int bit = Integer.parseInt(auth);
				if (bit == 0) {
					containsZero = true;
				} else {
					requiredBits |= bit;
				}
			}

			if (containsZero && requiredBits == 0) {
				sql.append(" AND CAST(authority AS UNSIGNED) = 0");
			} else if (containsZero) {
				sql.append(" AND (CAST(authority AS UNSIGNED) = 0 OR (CAST(authority AS UNSIGNED) & ?) = ?)");
				params.add(requiredBits);
				params.add(requiredBits);
			} else {
				sql.append(" AND (CAST(authority AS UNSIGNED) & ?) = ?");
				params.add(requiredBits);
				params.add(requiredBits);
			}

		}
		System.out.println("実行SQL: " + sql.toString());
		System.out.println("パラメータ: " + params);
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
