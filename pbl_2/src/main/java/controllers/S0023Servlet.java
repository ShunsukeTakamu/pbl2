package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.Account;
import beans.Category;
import beans.SaleDetail;
import services.AccountService;
import services.CategoryService;
import utils.Db;

@WebServlet("/S0023.html")
public class S0023Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public S0023Servlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // sale_id の取得と検証
        String saleIdStr = request.getParameter("sale_id");
        if (saleIdStr == null || saleIdStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "sale_id が指定されていません");
            return;
        }

        int saleId;
        try {
            saleId = Integer.parseInt(saleIdStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "sale_id は整数である必要があります");
            return;
        }

        SaleDetail detail = null;

        try (Connection conn = Db.open()) {
            String sql = """
                SELECT s.sale_id, s.sale_date,
                       s.account_id, a.name AS account_name,
                       s.category_id, c.category_name AS category_name,
                       s.trade_name, s.unit_price,
                       s.sale_number, s.note
                FROM sales s
                JOIN accounts a ON s.account_id = a.account_id
                JOIN categories c ON s.category_id = c.category_id
                WHERE s.sale_id = ?
            """;

            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, saleId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        detail = new SaleDetail();
                        detail.setSaleId(rs.getInt("sale_id"));
                        detail.setSaleDate(rs.getString("sale_date"));
                        detail.setAccountId(rs.getInt("account_id"));
                        detail.setAccountName(rs.getString("account_name"));
                        detail.setCategoryId(rs.getInt("category_id"));
                        detail.setCategoryName(rs.getString("category_name"));
                        detail.setTradeName(rs.getString("trade_name"));
                        detail.setUnitPrice(rs.getInt("unit_price"));
                        detail.setSaleNumber(rs.getInt("sale_number"));
                        detail.setNote(rs.getString("note"));
                    } else {
                        response.sendError(HttpServletResponse.SC_NOT_FOUND, "該当する売上データが見つかりません");
                        return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("売上データの取得に失敗しました", e);
        }

        // ▼ DBからアカウント・カテゴリ情報を取得
        ArrayList<Account> accounts = new AccountService().selectAll();
        ArrayList<Category> categories = new CategoryService().selectAll();

        // JSPに渡す
        request.setAttribute("detail", detail);
        request.setAttribute("accounts", accounts);
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("S0023.jsp").forward(request, response);
    }
}
