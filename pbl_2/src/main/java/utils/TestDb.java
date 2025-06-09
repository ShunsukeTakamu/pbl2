package utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.NamingException;

public class TestDb {
	public static void main(String[] args) {
		
		try (Connection con = Db.open()){
			System.out.println("mariaDBに接続しました");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
			
	}
}
