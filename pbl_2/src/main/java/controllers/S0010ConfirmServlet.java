package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.Account;
import beans.Category;
import beans.Login;
import beans.Sale;
import utils.Db;

@WebServlet("/S0010Confirm.html")
public class S0010ConfirmServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	// 権限チェック 未ログイン、b'00'、b'10'の場合 ログイン画面へ
    	HttpSession session = request.getSession();
		Login loginAccount = (Login) session.getAttribute("account");
		if (loginAccount == null || loginAccount.getAuthority().equals("b''") || loginAccount.getAuthority().equals("b'10'")) {
			response.sendRedirect("C0010.html");
			return;
		}

        request.setCharacterEncoding("UTF-8");

        // パラメータ取得
        String saleDate = request.getParameter("saleDate");
        String accountIdStr = request.getParameter("accountId");
        String categoryIdStr = request.getParameter("categoryId");
        String tradeName = request.getParameter("tradeName");
        String unitPriceStr = request.getParameter("unitPrice");
        String saleNumberStr = request.getParameter("saleNumber");
        String note = request.getParameter("note");

        List<String> errors = new ArrayList<>();
        int accountId = 0, categoryId = 0, unitPrice = -1, saleNumber = -1;

        // 販売日チェック
        if (saleDate == null || saleDate.isBlank()) {
            errors.add("販売日を入力してください。");
        } else {
            try {
                java.time.LocalDate.parse(saleDate);
            } catch (DateTimeParseException e) {
                errors.add("販売日を正しく入力してください。");
            }
        }

        // 担当チェック
        try {
            accountId = Integer.parseInt(accountIdStr);
            if (accountId == 0) {
                errors.add("担当が未選択です。");
            } else if (!existsAccount(accountId)) {
                errors.add("アカウントテーブルに存在しません。");
            }
        } catch (NumberFormatException e) {
            errors.add("担当が未選択です。");
        }

        // 商品カテゴリー
        try {
            categoryId = Integer.parseInt(categoryIdStr);
            if (categoryId == 0) {
                errors.add("商品カテゴリーが未選択です。");
            } else if (!existsCategory(categoryId)) {
                errors.add("商品カテゴリーテーブルに存在しません。");
            }
        } catch (NumberFormatException e) {
            errors.add("商品カテゴリーが未選択です。");
        }

        // 商品名チェック
        if (tradeName == null || tradeName.isBlank()) {
            errors.add("商品名を入力してください。");
        } else if (tradeName.getBytes("UTF-8").length >= 101) {
            errors.add("商品名が長すぎます。");
        }

        // 単価チェック
        if (unitPriceStr == null || unitPriceStr.trim().isEmpty()) {
            errors.add("単価を入力してください。");
        } else if (unitPriceStr.getBytes("UTF-8").length >= 10) {
            errors.add("単価が長すぎます。");
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
        } else if (saleNumberStr.getBytes("UTF-8").length >= 10) {
            errors.add("個数が長すぎます。");
        } else {
	        try {
	            saleNumber = Integer.parseInt(saleNumberStr);
	            if (saleNumber <= 0) errors.add("個数を入力してください。");
	        } catch (NumberFormatException e) {
	            errors.add("個数を正しく入力してください。");
	        }
        }

        // 備考チェック
        if (note != null && note.getBytes("UTF-8").length >= 400) {
            errors.add("備考が長すぎます。");
        }

        // エラーがあれば戻る
        if (!errors.isEmpty()) {
            Sale sale = new Sale();
            sale.setSaleDate(LocalDate.parse(saleDate));
            sale.setAccountId(accountId);
            sale.setCategoryId(categoryId);
            sale.setTradeName(tradeName);
            sale.setUnitPrice(unitPrice);
            sale.setSaleNumber(saleNumber);
            sale.setNote(note);

            request.setAttribute("errors", errors);
            request.setAttribute("sale", sale);
            request.setAttribute("accounts", getAccountList());
            request.setAttribute("categories", getCategoryList());
            request.getRequestDispatcher("S0010.jsp").forward(request, response);
            return;
        }

        // 正常時 → 確認画面へ
        Sale sale = new Sale();
        sale.setSaleDate(LocalDate.parse(saleDate));
        sale.setAccountId(accountId);
        sale.setCategoryId(categoryId);
        sale.setTradeName(tradeName);
        sale.setUnitPrice(unitPrice);
        sale.setSaleNumber(saleNumber);
        sale.setNote(note);
        
        session.setAttribute("sale", sale);
        response.sendRedirect("S0011.html");
    }

    // アカウント存在チェック
    private boolean existsAccount(int accountId) {
        try (Connection conn = Db.open()) {
            String sql = "SELECT 1 FROM accounts WHERE account_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, accountId);
                ResultSet rs = ps.executeQuery();
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // カテゴリ存在チェック
    private boolean existsCategory(int categoryId) {
        try (Connection conn = Db.open()) {
            String sql = "SELECT 1 FROM categories WHERE category_id = ?";
            try (PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setInt(1, categoryId);
                ResultSet rs = ps.executeQuery();
                return rs.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // アカウント一覧
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

    // カテゴリ一覧
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
}
