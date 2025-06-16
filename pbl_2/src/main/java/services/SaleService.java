package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import beans.Sale;
import utils.Db;

public class SaleService {
	
	// 売上条件検索
	public List<Sale> searchSales(String dateStart, String dateEnd, String accountId, String categoryId, String tradeName, String note) {
		List<Sale> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder("SELECT * FROM sales WHERE 1=1");
		List<Object> params = new ArrayList<>();

		if (dateStart != null && !dateStart.isEmpty()) {
			sql.append(" AND sale_date >= ?");
			params.add(dateStart);
		}
		
		if (dateEnd != null && !dateEnd.isEmpty()) {
			sql.append(" AND sale_date <= ?");
			params.add(dateEnd);
		}

		if (accountId != null && !accountId.isEmpty() && !accountId.equals("0")) {
			sql.append(" AND account_id = ?");
			params.add(accountId);
		}

		if (categoryId != null && !categoryId.isEmpty() && !categoryId.equals("0")) {
			sql.append(" AND category_id = ?");
			params.add(categoryId);
		}
		
		if (tradeName != null && !tradeName.isEmpty()) {
			sql.append(" AND trade_name LIKE ?");
			params.add("%" + tradeName + "%");
		}
		
		if (note != null && !note.isEmpty()) {
			sql.append(" AND note LIKE ?");
			params.add("%" + note + "%");
		}
		
		try (
				Connection con = Db.open();
				PreparedStatement stmt = con.prepareStatement(sql.toString())) {
			for (int i = 0; i < params.size(); i++) {
				stmt.setObject(i + 1, params.get(i));
			}

			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Sale s = new Sale(
						rs.getInt("sale_id"),
						LocalDate.parse(rs.getString("sale_date")),
						rs.getInt("account_id"),
						rs.getInt("category_id"),
						rs.getString("trade_name"),
						rs.getInt("unit_price"),
						rs.getInt("sale_number"),
						rs.getString("note"));
				list.add(s);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public Sale selectById(int id) {
		Sale s = null;
		String sql = "SELECT * FROM sales WHERE sale_id = ?";

		try (
				Connection con = Db.open();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				s = new Sale(
						rs.getInt("sale_id"),
						LocalDate.parse(rs.getString("sale_date")),
						rs.getInt("account_id"),
						rs.getInt("category_id"),
						rs.getString("trade_name"),
						rs.getInt("unit_price"),
						rs.getInt("sale_number"),
						rs.getString("note"));
			}
		} catch (SQLException | NamingException e) {
			e.printStackTrace();
		}

		return s;
	}
	
	public int insert(Sale s) {
		
		String sql = "INSERT INTO sales (sale_date, account_id, category_id, trade_name, unit_price, sale_number, note) VALUES (?, ?, ?, ?, ?, ?, ?)";
		int id = 0;

		try (
				Connection con = Db.open();
				PreparedStatement ps = con.prepareStatement(sql, java.sql.Statement.RETURN_GENERATED_KEYS)) {
			ps.setDate(1, Date.valueOf(s.getSaleDate()));
			ps.setInt(2, s.getAccountId());
			ps.setInt(3, s.getCategoryId());
			ps.setString(4, s.getTradeName());
			ps.setInt(5, s.getUnitPrice());
			ps.setInt(6, s.getSaleNumber());
			ps.setString(7, s.getNote());
			ps.executeUpdate();

			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(NamingException e) {
			e.printStackTrace();
		}
		return id;
	}
	//ランキング用
	public Map<String, Integer> getSalesByAccount() {
	    Map<String, Integer> result = new LinkedHashMap<>();

	    String sql = """
	        SELECT a.name AS account_name,
	               SUM(s.unit_price * s.sale_number) AS total_sales
	        FROM sales s
	        JOIN accounts a ON s.account_id = a.account_id
	        GROUP BY a.name
	        ORDER BY total_sales DESC
	        """;

	    try (Connection con = Db.open();
	         PreparedStatement ps = con.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            String name = rs.getString("account_name");
	            int total = rs.getInt("total_sales");
	            result.put(name, total);
	        }

	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    return result;
	}
	//ランキング用

	// 総売上
	public int getTotalSales() {
		String sql = "select SUM(unit_price * sale_number) from sales";
		int total = 0;

		try (
				Connection con = Db.open();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			if (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(NamingException e) {
			e.printStackTrace();
		}
		return total;
	}
	
	// 商品カテゴリー別売上
		public Map<String, Integer> getSalesByCategory() {
		    Map<String, Integer> result = new LinkedHashMap<>();

		    String sql = """
		        SELECT c.category_name AS category_name,
		               SUM(s.unit_price * s.sale_number) AS total_sales
		        FROM sales s
		        JOIN categories c ON s.category_id = c.category_id
		        GROUP BY c.category_name
		        ORDER BY total_sales DESC
		        """;

		    try (Connection con = Db.open();
		         PreparedStatement ps = con.prepareStatement(sql);
		         ResultSet rs = ps.executeQuery()) {

		        while (rs.next()) {
		            String name = rs.getString("category_name");
		            int total = rs.getInt("total_sales");
		            result.put(name, total);
		        }

		    } catch (Exception e) {
		        e.printStackTrace();
		    }

		    return result;
		}
}
