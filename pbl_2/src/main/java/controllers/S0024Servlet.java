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

@WebServlet("/S0024.html")
public class S0024Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        try {
            // フォームから受け取る値を取得
            int saleId = Integer.parseInt(request.getParameter("sale_id"));
            String saleDate = request.getParameter("sale_date");
            int accountId = Integer.parseInt(request.getParameter("account_id"));
            int categoryId = Integer.parseInt(request.getParameter("category_id"));
            String tradeName = request.getParameter("trade_name");
            int unitPrice = Integer.parseInt(request.getParameter("unit_price"));
            int saleNumber = Integer.parseInt(request.getParameter("sale_number"));
            String note = request.getParameter("note");

            // DB接続と更新処理
            try (Connection conn = Db.open()) {
                String sql = """
                    UPDATE sales
                    SET sale_date = ?, account_id = ?, category_id = ?,
                        trade_name = ?, unit_price = ?, sale_number = ?, note = ?
                    WHERE sale_id = ?
                """;

                try (PreparedStatement ps = conn.prepareStatement(sql)) {
                    ps.setString(1, saleDate);
                    ps.setInt(2, accountId);
                    ps.setInt(3, categoryId);
                    ps.setString(4, tradeName);
                    ps.setInt(5, unitPrice);
                    ps.setInt(6, saleNumber);
                    ps.setString(7, note);
                    ps.setInt(8, saleId);

                    int updated = ps.executeUpdate();
                    if (updated == 0) {
                        throw new ServletException("更新対象が存在しません (sale_id=" + saleId + ")");
                    }
                }
            }

            // 更新後は詳細表示画面へリダイレクト
            response.sendRedirect("S0022.html?sale_id=" + saleId);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("売上情報の更新に失敗しました", e);
        }
    }
}
