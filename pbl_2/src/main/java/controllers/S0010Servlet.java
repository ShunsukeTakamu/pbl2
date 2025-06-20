package controllers;

import java.io.IOException;
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
import beans.Sale;
import services.AccountService;
import services.CategoryService;

/**
 * Servlet implementation class S0010Servlet
 */
@WebServlet("/S0010.html")
public class S0010Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S0010Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LocalDate today = LocalDate.now();
		Sale sale = new Sale(today, 0, 0, -1, -1);
		ArrayList<Account> accounts = (new AccountService()).selectAll();
		ArrayList<Category> categories = (new CategoryService()).selectAll();
		
		request.setAttribute("sale", sale);
		request.setAttribute("accounts", accounts);
		request.setAttribute("categories", categories);
		request.getRequestDispatcher("/S0010.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
        AccountService as = new AccountService();
        CategoryService cs = new CategoryService();
        int accountId = 0, categoryId = 0, unitPrice = -1, saleNumber = -1;
        
        // 販売日チェック
        if (saleDate == null || saleDate.isBlank()) {
            errors.add("販売日を入力してください。");
        } else {
            try {
                LocalDate.parse(saleDate);
            } catch (DateTimeParseException e) {
                errors.add("販売日を正しく入力してください。");
            }
        }

        // 担当チェック
        try {
            accountId = Integer.parseInt(accountIdStr);
            if (accountId == 0) {
                errors.add("担当が未選択です。");
            } else if (!as.existsById(accountId)) {
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
            } else if (!cs.existsById(categoryId)) {
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

        // 空文字ならnoteをnullにする
        if (note.isEmpty()) {
            note = null;
        }
        // 備考チェック
        if (note != null && note.getBytes("UTF-8").length >= 400) {
            errors.add("備考が長すぎます。");
        }

        Sale sale = new Sale(
        		0,
        		LocalDate.parse(saleDate),
        		accountId,
        		categoryId,
        		tradeName,
        		unitPrice,
        		saleNumber,
        		note);
        
        // エラーがあれば戻る
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("sale", sale);
            request.setAttribute("accounts", as.selectAll());
            request.setAttribute("categories", cs.selectAll());
            request.getRequestDispatcher("/S0010.jsp").forward(request, response);
            return;
        }

        // 正常時 → 確認画面へ
        HttpSession session = request.getSession();
        session.setAttribute("sale", sale);
        response.sendRedirect("S0011.html");
	}

}
