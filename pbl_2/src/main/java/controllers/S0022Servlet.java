package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.SaleDetail;
import utils.DateUtil;
import utils.Db;

@WebServlet("/S0022Servlet")
public class S0022Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public S0022Servlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // saleId パラメータのチェック
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
        // 売上データ取得処理
        try (Connection conn = Db.open()) {
            String sql = """
                SELECT s.sale_id, s.sale_date,
                       a.name AS account_name,
                       c.category_name AS category_name,
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
                        detail.setAccountName(rs.getString("account_name"));
                        detail.setCategoryName(rs.getString("category_name"));
                        detail.setTradeName(rs.getString("trade_name"));
                        detail.setUnitPrice(rs.getInt("unit_price"));
                        detail.setSaleNumber(rs.getInt("sale_number"));
                        detail.setNote(rs.getString("note"));

                        // 確認用ログ
                        System.out.println("取得成功: sale_id=" + detail.getSaleId());
                    } else {
                        System.out.println("該当する売上データが見つかりませんでした: sale_id=" + saleId);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("売上詳細の取得に失敗しました", e);
        }

        request.setAttribute("detail", detail);
        
        // 日付のフォーマットを整える
        String formattedDate = DateUtil.formatLocDateToStr(LocalDate.parse(detail.getSaleDate()));
        request.setAttribute("formattedDate", formattedDate);
        request.getRequestDispatcher("S0022.jsp").forward(request, response);
    }
}
