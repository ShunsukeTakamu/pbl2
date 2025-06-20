package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;

import beans.Sale;
import forms.SaleSearchForm;
import utils.Db;

public class SaleService {

	// 売上条件検索
	public List<Sale> searchSales(SaleSearchForm saleSearchForm) {
		String dateStart = saleSearchForm.getDateStart();
		String dateEnd = saleSearchForm.getDateEnd();
		String accountId = saleSearchForm.getAccountIdStr();
		String categoryId = saleSearchForm.getCategoryIdStr();
		String tradeName = saleSearchForm.getTradeName();
		String note = saleSearchForm.getNote();
		
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

		try (Connection con = Db.open(); PreparedStatement stmt = con.prepareStatement(sql.toString())) {
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

	// IDを指定して1件の売上を取得
	public Sale selectById(int id) {
		Sale s = null;
		String sql = "SELECT * FROM sales WHERE sale_id = ?";

		try (Connection con = Db.open(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}

		return s;
	}

	// 売上の新規登録
	public int insert(Sale s) {
		String sql = "INSERT INTO sales (sale_date, account_id, category_id, trade_name, unit_price, sale_number, note) VALUES (?, ?, ?, ?, ?, ?, ?)";
		int id = 0;

		try (Connection con = Db.open();
				PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		return id;
	}

	// 売上の更新処理
	public void update(Sale s) {
		String sql = """
				UPDATE sales
				SET sale_date = ?, account_id = ?, category_id = ?,
				    trade_name = ?, unit_price = ?, sale_number = ?, note = ?
				WHERE sale_id = ?
				""";

		try (Connection conn = Db.open(); PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setDate(1, Date.valueOf(s.getSaleDate()));
			ps.setInt(2, s.getAccountId());
			ps.setInt(3, s.getCategoryId());
			ps.setString(4, s.getTradeName());
			ps.setInt(5, s.getUnitPrice());
			ps.setInt(6, s.getSaleNumber());
			ps.setString(7, s.getNote());
			ps.setInt(8, s.getSaleId());

			int updated = ps.executeUpdate();
			if (updated == 0) {
				throw new ServletException("更新対象が存在しません (saleId=" + s.getSaleId() + ")");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 売上の削除処理
	public void delete(int id) {
		String sql = "DELETE FROM sales WHERE sale_id = ?";
		try (Connection con = Db.open(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// アカウント別売上集計
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
				result.put(rs.getString("account_name"), rs.getInt("total_sales"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 総売上取得（全期間）
	public int getTotalSales() {
		String sql = "SELECT SUM(unit_price * sale_number) FROM sales";
		int total = 0;
		try (Connection con = Db.open();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			if (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

	// 商品カテゴリ別売上集計
	public Map<String, Integer> getSalesByCategory() {
		Map<String, Integer> result = new LinkedHashMap<>();
		String sql = """
				SELECT c.category_name,
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
				result.put(rs.getString("category_name"), rs.getInt("total_sales"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	// 年別売上推移（年を補完して0の年も入れる）
	public Map<String, Integer> getSalesByYear() {
		Map<String, Integer> yearMap = new LinkedHashMap<>();
		String sql = """
				SELECT DATE_FORMAT(sale_date, '%Y') AS year,
				       SUM(unit_price * sale_number) AS total_sales
				FROM sales
				GROUP BY year
				ORDER BY year
				""";
		try (Connection con = Db.open();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				yearMap.put(rs.getString("year"), rs.getInt("total_sales"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 年の空白を補完
		List<Integer> keys = new ArrayList<>();
		for (String key : yearMap.keySet())
			keys.add(Integer.parseInt(key));
		if (keys.isEmpty())
			return yearMap;
		int min = Collections.min(keys);
		int max = Collections.max(keys);

		Map<String, Integer> filled = new LinkedHashMap<>();
		for (int i = min; i <= max; i++) {
			String yearStr = String.valueOf(i);
			filled.put(yearStr, yearMap.getOrDefault(yearStr, 0));
		}
		return filled;
	}

	// 前年同月の売上取得
	public int getPreviousTotalSales() {
		String sql = "SELECT SUM(unit_price * sale_number) FROM sales WHERE sale_date BETWEEN ? AND ?";
		int total = 0;

		LocalDate now = LocalDate.now();
		LocalDate thisMonthStart = now.withDayOfMonth(1);
		LocalDate lastYearSameMonthStart = thisMonthStart.minusYears(1);
		LocalDate lastYearSameMonthEnd = lastYearSameMonthStart.withDayOfMonth(lastYearSameMonthStart.lengthOfMonth());

		try (Connection con = Db.open(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setDate(1, java.sql.Date.valueOf(lastYearSameMonthStart));
			ps.setDate(2, java.sql.Date.valueOf(lastYearSameMonthEnd));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;
	}

	// 前年の年間売上を取得する
	public int getPreviousYearSales() {
		int total = 0;
		String sql = "SELECT SUM(unit_price * sale_number) FROM sales WHERE YEAR(sale_date) = ?";

		int previousYear = LocalDate.now().getYear() - 1;

		try (Connection con = Db.open(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, previousYear);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return total;
	}

	// 今年の売上取得
	public int getCurrentYearSales() {
		int total = 0;
		String sql = "SELECT SUM(unit_price * sale_number) FROM sales WHERE YEAR(sale_date) = ?";

		int currentYear = LocalDate.now().getYear();

		try (Connection con = Db.open(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, currentYear);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				total = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return total;
	}

	// 日付別売上集計
	public Map<String, Integer> getSalesByDate() {
		Map<String, Integer> dateMap = new LinkedHashMap<>();
		String sql = "SELECT sale_date, SUM(unit_price * sale_number) AS total_sales FROM sales GROUP BY sale_date ORDER BY sale_date";
		try (Connection con = Db.open();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {
			while (rs.next()) {
				dateMap.put(rs.getString("sale_date"), rs.getInt("total_sales"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dateMap;
	}
}
