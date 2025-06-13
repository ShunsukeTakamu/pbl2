package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.Account;
import beans.Category;
import beans.SaleDetail;
import utils.Db;

@WebServlet("/S0023ConfirmServlet")
public class S0023ConfirmServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // パラメータ取得
        String saleIdStr = request.getParameter("sale_id");
        String saleDate = request.getParameter("sale_date");
        String accountIdStr = request.getParameter("account_id");
        String categoryIdStr = request.getParameter("category_id");
        String tradeName = request.getParameter("trade_name");
        String unitPriceStr = request.getParameter("unit_price");
        String saleNumberStr = request.getParameter("sale_number");
        String note = request.getParameter("note");

        List<String> errors = new ArrayList<>();

        int saleId = 0, accountId = 0, categoryId = 0;
        int unitPrice = -1, saleNumber = -1;

        try {
            saleId = Integer.parseInt(saleIdStr);
        } catch (NumberFormatException e) {
            errors.add("不正なsale_idです。");
        }

        if (saleDate == null || saleDate.isBlank()) {
            errors.add("販売日を入力してください。");
        } else {
            try {
                java.time.LocalDate.parse(saleDate); // 形式がISO_LOCAL_DATEであるか確認（例：2025-06-12）
            } catch (java.time.format.DateTimeParseException e) {
                errors.add("販売日を正しく入力して下さい。");
            }
        }


        try {
            accountId = Integer.parseInt(accountIdStr);
            if (!existsAccount(accountId)) {
                errors.add("アカウントテーブルに存在しません。");
            }
        } catch (NumberFormatException e) {
            errors.add("担当が未選択です。");
        }


        try {
            categoryId = Integer.parseInt(categoryIdStr);
            if (!existsCategory(categoryId)) {
                errors.add("商品カテゴリーテーブルに存在しません。");
            }
        } catch (NumberFormatException e) {
            errors.add("商品カテゴリーが未選択です。");
        }

        if (tradeName == null || tradeName.isBlank()) {
            errors.add("商品名を入力してください。");
        } else {
            try {
                if (tradeName.getBytes("UTF-8").length >= 101) {
                    errors.add("商品名が長すぎます。");
                }
            } catch (Exception e) {
                e.printStackTrace(); // 念のため
            }
        }
        
     // 単価チェック
        if (unitPriceStr == null || unitPriceStr.trim().isEmpty()) {
            errors.add("単価を入力してください。");
        } else {

        try {
            unitPrice = Integer.parseInt(unitPriceStr);
            if (unitPrice <= 0) errors.add("単価を入力してください。");
        } catch (NumberFormatException e) {
            errors.add("単価を正しく入力してください。");
        }
        }
        
     // 個数チェック
        if (saleNumberStr == null || saleNumberStr.trim().isEmpty()) {
            errors.add("個数を入力してください。");
        } else {

        try {
            saleNumber = Integer.parseInt(saleNumberStr);
            if (saleNumber <= 0) errors.add("個数を入力してください。");
        } catch (NumberFormatException e) {
            errors.add("個数を正しく入力してください。");
        }
        }

        if (note != null && note.length() > 255) {
            errors.add("備考が長すぎます。");
        }

        // エラーがあれば編集画面に戻す
        if (!errors.isEmpty()) {
            SaleDetail detail = new SaleDetail();
            detail.setSaleId(saleId);
            detail.setSaleDate(saleDate);
            detail.setAccountId(accountId);
            detail.setCategoryId(categoryId);
            detail.setTradeName(tradeName);
            detail.setUnitPrice(unitPrice);
            detail.setSaleNumber(saleNumber);
            detail.setNote(note);

            request.setAttribute("errors", errors);
            request.setAttribute("detail", detail);
            request.setAttribute("accounts", getAccountList());
            request.setAttribute("categories", getCategoryList());
            request.getRequestDispatcher("S0023.jsp").forward(request, response);
            return;
        }

        // 確認画面へ進む：IDから名前を取得してセット
        SaleDetail detail = new SaleDetail();
        detail.setSaleId(saleId);
        detail.setSaleDate(saleDate);
        detail.setAccountId(accountId);
        detail.setCategoryId(categoryId);
        detail.setTradeName(tradeName);
        detail.setUnitPrice(unitPrice);
        detail.setSaleNumber(saleNumber);
        detail.setNote(note);
        detail.setAccountName(getAccountName(accountId));
        detail.setCategoryName(getCategoryName(categoryId));

        request.setAttribute("sale", detail);
        request.setAttribute("account", new Account(accountId, getAccountName(accountId)));
        request.setAttribute("category", new Category(categoryId, getCategoryName(categoryId)));
        request.getRequestDispatcher("S0024.jsp").forward(request, response);
    }

    // アカウント名を取得
    private String getAccountName(int accountId) {
        String name = "";
        try (Connection conn = Db.open()) {
            String sql = "SELECT name FROM accounts WHERE account_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, accountId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    name = rs.getString("name");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    // カテゴリ名を取得
    private String getCategoryName(int categoryId) {
        String name = "";
        try (Connection conn = Db.open()) {
            String sql = "SELECT category_name FROM categories WHERE category_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, categoryId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    name = rs.getString("category_name");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    // アカウント一覧取得（選択肢用）
    private List<Account> getAccountList() {
        List<Account> list = new ArrayList<>();
        try (Connection conn = Db.open()) {
            String sql = "SELECT account_id, name FROM accounts";
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Account a = new Account();
                    a.setAccountId(rs.getInt("account_id"));
                    a.setName(rs.getString("name"));
                    list.add(a);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // カテゴリ一覧取得（選択肢用）
    private List<Category> getCategoryList() {
        List<Category> list = new ArrayList<>();
        try (Connection conn = Db.open()) {
            String sql = "SELECT category_id, category_name FROM categories";
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Category c = new Category();
                    c.setCategoryId(rs.getInt("category_id"));
                    c.setCategoryName(rs.getString("category_name"));
                    list.add(c);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
 // アカウント存在チェック
    private boolean existsAccount(int accountId) {
        try (Connection conn = Db.open()) {
            String sql = "SELECT 1 FROM accounts WHERE account_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, accountId);
                ResultSet rs = ps.executeQuery();
                return rs.next(); // 存在する → true
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // 例外発生時やヒットしない場合
    }
    
 // カテゴリ存在チェック
    private boolean existsCategory(int categoryId) {
        try (Connection conn = Db.open()) {
            String sql = "SELECT 1 FROM categories WHERE category_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, categoryId);
                ResultSet rs = ps.executeQuery();
                return rs.next(); // 存在する → true
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // エラー時または見つからない場合
    }


}
