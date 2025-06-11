package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import utils.Db;

@WebServlet("/S0025Servlet")
public class S0025Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		int saleId = Integer.parseInt(request.getParameter("sale_id"));

		try (Connection conn = Db.open()) {
			String sql = "DELETE FROM sales WHERE sale_id = ?";
			try (PreparedStatement ps = conn.prepareStatement(sql)) {
				ps.setInt(1, saleId);
				int result = ps.executeUpdate();
				if (result == 0) {
					throw new ServletException("削除対象が存在しません (sale_id=" + saleId + ")");
				}
			}
		} catch (Exception e) {
			throw new ServletException("削除処理中にエラーが発生しました", e);
		}

		// 削除完了後、一覧画面へリダイレクト（検索画面など）
		response.sendRedirect("S0021.jsp");
	}
}
