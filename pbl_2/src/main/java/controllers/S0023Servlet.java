package controllers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
import services.AccountService;
import services.CategoryService;
import services.SaleService;

@WebServlet("/S0023.html")
public class S0023Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public S0023Servlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	// 権限チェック 未ログイン、b'00'、b'10'の場合 ログイン画面へ
    	HttpSession session = request.getSession();
		Login loginAccount = (Login) session.getAttribute("account");
		if (loginAccount == null || loginAccount.getAuthority().equals("b''") || loginAccount.getAuthority().equals("b'10'")) {
			response.sendRedirect("C0010.html");
			return;
		}

        // saleId の取得と検証
        String saleIdStr = request.getParameter("saleId");
        if (saleIdStr == null || saleIdStr.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "saleId が指定されていません");
            return;
        }

        int saleId;
        try {
            saleId = Integer.parseInt(saleIdStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "saleId は整数である必要があります");
            return;
        }

        // 売上データの取得
        Sale sale = (new SaleService()).selectById(saleId);

        // ▼ DBからアカウント・カテゴリ情報を取得
        ArrayList<Account> accounts = new AccountService().selectAll();
        ArrayList<Category> categories = new CategoryService().selectAll();

        // JSPに渡す
        request.setAttribute("sale", sale);
        request.setAttribute("accounts", accounts);
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("S0023.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // パラメータ取得
        String saleIdStr = request.getParameter("saleId");
        String saleDate = request.getParameter("saleDate");
        String accountIdStr = request.getParameter("accountId");
        String categoryIdStr = request.getParameter("categoryId");
        String tradeName = request.getParameter("tradeName");
        String unitPriceStr = request.getParameter("unitPrice");
        String saleNumberStr = request.getParameter("saleNumber");
        String note = request.getParameter("note");

        List<String> errors = new ArrayList<>();
        AccountService as = new AccountService();
        CategoryService cs = new CategoryService();

        int saleId = 0, accountId = 0, categoryId = 0;
        int unitPrice = -1, saleNumber = -1;

        try {
            saleId = Integer.parseInt(saleIdStr);
        } catch (NumberFormatException e) {
            errors.add("不正なsaleIdです。");
        }

        if (saleDate == null || saleDate.isBlank()) {
            errors.add("販売日を入力してください。");
        } else {
            try {
                LocalDate.parse(saleDate); // 形式がISO_LOCAL_DATEであるか確認（例：2025-06-12）
            } catch (DateTimeParseException e) {
                errors.add("販売日を正しく入力して下さい。");
            }
        }


        try {
            accountId = Integer.parseInt(accountIdStr);
            if (!as.existsById(accountId)) {
                errors.add("アカウントテーブルに存在しません。");
            }
        } catch (NumberFormatException e) {
            errors.add("担当が未選択です。");
        }


        try {
            categoryId = Integer.parseInt(categoryIdStr);
            if (!cs.existsById(categoryId)) {
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

        if (note != null && note.getBytes(StandardCharsets.UTF_8).length >= 401) {
            errors.add("備考が長すぎます。");
        }

        // エラーがあれば編集画面に戻す
        if (!errors.isEmpty()) {
            Sale sale = new Sale();
            sale.setSaleId(saleId);
            sale.setSaleDate(LocalDate.parse(saleDate));
            sale.setAccountId(accountId);
            sale.setCategoryId(categoryId);
            sale.setTradeName(tradeName);
            sale.setUnitPrice(unitPrice);
            sale.setSaleNumber(saleNumber);
            sale.setNote(note);

            request.setAttribute("errors", errors);
            request.setAttribute("sale", sale);
            request.setAttribute("accounts", as.selectAll());
            request.setAttribute("categories", cs.selectAll());
            request.getRequestDispatcher("S0023.jsp").forward(request, response);
            return;
        }

        // 確認画面へ進む：IDから名前を取得してセット
        Sale sale = new Sale();
        sale.setSaleId(saleId);
        sale.setSaleDate(LocalDate.parse(saleDate));
        sale.setAccountId(accountId);
        sale.setCategoryId(categoryId);
        sale.setTradeName(tradeName);
        sale.setUnitPrice(unitPrice);
        sale.setSaleNumber(saleNumber);
        sale.setNote(note);

        request.setAttribute("sale", sale);
        request.setAttribute("selectedAccount", as.selectById(accountId));
        request.setAttribute("selectedCategory", cs.selectById(categoryId));
        request.getRequestDispatcher("S0024.jsp").forward(request, response);
    }
}
