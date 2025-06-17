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

import jakarta.servlet.ServletException;

import beans.Sale;
import utils.Db;

public class SaleService {

	// 売上条件検索
	public List<Sale> searchSales(String dateStart, String dateEnd, String accountId, String categoryId,
			String tradeName, String note) {
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
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return id;
	}

	public void update(Sale s) {
		String sql = """
				    UPDATE sales
				    SET sale_date = ?, account_id = ?, category_id = ?,
				        trade_name = ?, unit_price = ?, sale_number = ?, note = ?
				    WHERE sale_id = ?
				""";

		// DB接続と更新処理
		try (
				Connection conn = Db.open();
				PreparedStatement ps = conn.prepareStatement(sql)) {
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

	public void delete(int id) {
		String sql = "DELETE FROM sales WHERE sale_id = ?";
		try (
				Connection con = Db.open();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// アカウントごとの売上合計を取得するメソッド
	public Map<String, Integer> getSalesByAccount() {
		// 結果を格納するマップ（順序保持）
		Map<String, Integer> result = new LinkedHashMap<>();

		// SQL：アカウントごとの売上（単価 × 数量）の合計を集計
		String sql = """
				SELECT a.name AS account_name,
				       SUM(s.unit_price * s.sale_number) AS total_sales
				FROM sales s
				JOIN accounts a ON s.account_id = a.account_id
				GROUP BY a.name
				ORDER BY total_sales DESC
				""";

		try (
				// DB接続・クエリ実行
				Connection con = Db.open();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			// 結果セットからアカウント名と売上をマップに格納
			while (rs.next()) {
				String name = rs.getString("account_name");
				int total = rs.getInt("total_sales");
				result.put(name, total);
			}

		} catch (Exception e) {
			// エラー発生時にスタックトレース出力
			e.printStackTrace();
		}

		// アカウント別売上マップを返す
		return result;
	}

	// 総売上（全期間）の合計金額を取得するメソッド
	public int getTotalSales() {
		// SQL文：すべての売上（単価 × 数量）を合計
		String sql = "SELECT SUM(unit_price * sale_number) FROM sales";
		int total = 0;

		try (
				// データベース接続とクエリ実行
				Connection con = Db.open();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {

			// 結果が存在すれば、合計値を取得
			if (rs.next()) {
				total = rs.getInt(1);
			}

		} catch (SQLException e) {
			// SQL関連の例外処理
			e.printStackTrace();
		} catch (NamingException e) {
			// JNDI関連の例外処理
			e.printStackTrace();
		}

		// 総売上を返す
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

	// 年別売上推移を取得するメソッド（年ごとの売上合計をMapで返す）
	public Map<String, Integer> getSalesByYear() {
		// 年ごとの売上金額を格納するマップ（キー：年、値：売上合計）
		Map<String, Integer> yearMap = new LinkedHashMap<>();

		// SQL文：売上日を年単位でグルーピングし、売上金額を集計
		String sql = """
				SELECT DATE_FORMAT(sale_date, '%Y') AS year,
				       SUM(unit_price * sale_number) AS total_sales
				FROM sales
				GROUP BY year
				ORDER BY year
				""";

		try (
				// DB接続とSQL実行
				Connection con = Db.open();
				PreparedStatement ps = con.prepareStatement(sql);
				ResultSet rs = ps.executeQuery()) {

			// 結果をマップに格納（キー：年、値：合計売上）
			while (rs.next()) {
				yearMap.put(rs.getString("year"), rs.getInt("total_sales"));
			}

		} catch (Exception e) {
			// エラー発生時のログ出力
			e.printStackTrace();
		}

		// 年別売上データを返す
		return yearMap;
	}

	// 前年同月の総売上を取得するメソッド
	public int getPreviousTotalSales() {
		// 前年同月の売上合計を取得するSQL（期間をBETWEENで指定）
		String sql = "SELECT SUM(unit_price * sale_number) FROM sales WHERE sale_date BETWEEN ? AND ?";
		int total = 0;

		// 現在の日付
		LocalDate now = LocalDate.now();

		// 今年の今月の1日（例：2025-06-01）
		LocalDate thisMonthStart = now.withDayOfMonth(1);

		// 昨年同月の1日（例：2024-06-01）
		LocalDate lastYearSameMonthStart = thisMonthStart.minusYears(1);

		// 昨年同月の末日（例：2024-06-30）
		LocalDate lastYearSameMonthEnd = lastYearSameMonthStart.withDayOfMonth(lastYearSameMonthStart.lengthOfMonth());

		try (
				// DB接続とSQL実行
				Connection con = Db.open();
				PreparedStatement ps = con.prepareStatement(sql)) {

			// パラメータに日付範囲を設定（前年同月の開始〜終了）
			ps.setDate(1, java.sql.Date.valueOf(lastYearSameMonthStart));
			ps.setDate(2, java.sql.Date.valueOf(lastYearSameMonthEnd));

			// クエリ実行
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				total = rs.getInt(1); // 売上合計を取得
			}

		} catch (Exception e) {
			// エラー発生時のログ出力
			e.printStackTrace();
		}

		// 前年同月の売上合計を返す
		return total;
	}

	// 日付別売上
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
