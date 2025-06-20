package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import beans.Account;
import utils.Db;

public class AccountService {
	
	// ログイン時
	public Account findValidByMail(String mail) {
	    String sql = "SELECT * FROM accounts WHERE mail = ?";
	    try (Connection con = Db.open();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setString(1, mail);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            if (!rs.getBoolean("is_valid")) {
	                return null; // 論理削除済みアカウントはログイン不可
	            }
	            return new Account(
	                    rs.getInt("account_id"),
	                    rs.getString("name"),
	                    rs.getString("mail"),
	                    rs.getString("password"),
	                    rs.getBytes("authority")
	            );
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}


	// アカウント条件検索
	public List<Account> searchAccounts(String name, String email, String[] authorities) {
		List<Account> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder("SELECT * FROM accounts WHERE is_valid = true");
		List<Object> params = new ArrayList<>();

		if (name != null && !name.isEmpty()) {
			sql.append(" AND name LIKE ?");
			params.add("%" + name + "%");
		}

		if (email != null && !email.isEmpty()) {
			sql.append(" AND mail LIKE ?");
			params.add("%" + email + "%");
		}

		// 
		if (authorities != null && authorities.length > 0) {
			boolean containsZero = false; // 権限０が含まれているか
			int requiredBits = 0; // ビットを合成する。

			for (String auth : authorities) {
				int bit = Integer.parseInt(auth);
				if (bit == 0) {
					containsZero = true; // ORで０が消えるため
				} else {
					requiredBits |= bit; // OR|=で権限の合成
				}
			}

			if (containsZero && requiredBits == 0) {
				sql.append(" AND CAST(authority AS UNSIGNED) = 0"); // 権限なしのみ
			} else if (containsZero) { // 権限なし＋ほかの権限
				sql.append(" AND (CAST(authority AS UNSIGNED) = 0 OR (CAST(authority AS UNSIGNED) & ?) = ?)");
				params.add(requiredBits);
				params.add(requiredBits);
			} else { // 権限なしは選択なし>> ビットAND検索
				sql.append(" AND (CAST(authority AS UNSIGNED) & ?) = ?");
				params.add(requiredBits);
				params.add(requiredBits);
			}

		}
//		System.out.println("実行SQL: " + sql.toString());
//		System.out.println("パラメータ: " + params);
		
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

	public ArrayList<Account> selectAll() {
		ArrayList<Account> accounts = new ArrayList<>();
		String sql = "SELECT * FROM accounts WHERE is_valid = TRUE;";

		try (
				Connection con = Db.open();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				Account a = new Account(
						rs.getInt("account_id"),
						rs.getString("name"),
						rs.getString("mail"),
						rs.getString("password"),
						rs.getBytes("authority"));
				accounts.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return accounts;
	}
	public ArrayList<Account> selectValid() {
		ArrayList<Account> accounts = new ArrayList<>();
		String sql = "SELECT * FROM accounts WHERE 1=1;";

		try (
				Connection con = Db.open();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				Account a = new Account(
						rs.getInt("account_id"),
						rs.getString("name"),
						rs.getString("mail"),
						rs.getString("password"),
						rs.getBytes("authority"));
				accounts.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return accounts;
	}

	public Account selectById(int id) {
		Account a = null;
		String sql = "SELECT * FROM accounts WHERE account_id = ?";

		try (
				Connection con = Db.open();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				a = new Account(
						rs.getInt("account_id"),
						rs.getString("name"),
						rs.getString("mail"),
						rs.getString("password"),
						rs.getBytes("authority"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return a;
	}

	public void update(Account updated) {
		String sql = "UPDATE accounts SET name = ?, mail = ?, password = ?, authority = ? WHERE account_id = ?";

		try (
				Connection con = Db.open();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, updated.getName());
			ps.setString(2, updated.getMail());
			ps.setString(3, updated.getPassword()); // ハッシュ化しますか？
			ps.setBytes(4, updated.getAuthority());
			ps.setInt(5, updated.getAccountId());

			ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteAccount(int id) {
	    String sql = "UPDATE accounts SET is_valid = false WHERE account_id = ?";
	    try (Connection con = Db.open();
	         PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setInt(1, id);
	        ps.executeUpdate();
	    } catch (Exception e) {
	        e.printStackTrace();
	        throw new RuntimeException("アカウントの論理削除に失敗しました。");
	    }
	}

	// S0010ConfirmServlet.java用
	public boolean existsById(int id) {
		try (Connection conn = Db.open()) {
			String sql = "SELECT 1 FROM accounts WHERE account_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			return rs.next();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	// S0010ConfirmServlet.java用
	}
	
	
	public int getAccountCount() {
	    String sql = "SELECT COUNT(*) FROM accounts WHERE is_valid = TRUE;";
	    try (Connection con = Db.open();
	         PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {
	        if (rs.next()) {
	            return rs.getInt(1);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return 0;
	}


}