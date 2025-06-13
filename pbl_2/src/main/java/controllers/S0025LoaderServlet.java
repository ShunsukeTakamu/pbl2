package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.SaleDetail;
import utils.Db;

@WebServlet("/S0025Loader.html")
public class S0025LoaderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		int saleId = Integer.parseInt(request.getParameter("sale_id"));

		try (Connection con = Db.open()) {

			String sql = "SELECT s.sale_id, s.sale_date, a.name AS account_name, c.category_name, "
					   + "s.trade_name, s.unit_price, s.sale_number, s.note "
					   + "FROM sales s "
					   + "JOIN accounts a ON s.account_id = a.account_id "
					   + "JOIN categories c ON s.category_id = c.category_id "
					   + "WHERE s.sale_id = ?";

			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, saleId);
			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				SaleDetail detail = new SaleDetail();
				detail.setSaleId(rs.getInt("sale_id"));
				detail.setSaleDate(rs.getString("sale_date"));
				detail.setAccountName(rs.getString("account_name"));
				detail.setCategoryName(rs.getString("category_name"));
				detail.setTradeName(rs.getString("trade_name"));
				detail.setUnitPrice(rs.getInt("unit_price"));
				detail.setSaleNumber(rs.getInt("sale_number"));
				detail.setNote(rs.getString("note"));

				request.setAttribute("detail", detail);
				request.getRequestDispatcher("S0025.jsp").forward(request, response);
			} else {
				// 該当データが存在しない場合
				request.setAttribute("error", "指定された売上情報が見つかりませんでした。");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}

		} catch (Exception e) {
			throw new ServletException("削除確認情報の取得に失敗しました", e);
		}
	}
}
