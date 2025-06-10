package services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.naming.NamingException;

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
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}

		return categories;
	}
}
