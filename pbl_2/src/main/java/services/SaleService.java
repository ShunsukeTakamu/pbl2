package services;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.NamingException;

import beans.Sale;
import utils.Db;

public class SaleService {
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
}
