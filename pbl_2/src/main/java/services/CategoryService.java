package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import beans.Category;
import utils.Db;

public class CategoryService {
	public ArrayList<Category> selectAll() {
		ArrayList<Category> categories = new ArrayList<>();
		String sql = "SELECT * FROM categories";

		try (
				Connection con = Db.open();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				Category c = new Category(
						rs.getInt("category_id"),
						rs.getString("category_name"),
						rs.getInt("active_flg"));
				categories.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return categories;
	}
	
	public Category selectById(int id) {
		Category c = null;
		String sql = "SELECT * FROM categories WHERE category_id = ?";

		try (
				Connection con = Db.open();
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				c = new Category(
						rs.getInt("category_id"),
						rs.getString("category_name"),
						rs.getInt("active_flg"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return c;
	}
	// S0010ConfirmServlet.java用
	public boolean existsById(int id) {
		try (Connection conn = Db.open()) {
			String sql = "SELECT 1 FROM categories WHERE category_id = ?";
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

}
