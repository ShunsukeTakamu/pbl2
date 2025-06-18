package controllers;

import java.io.IOException;
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
import utils.DateUtil;

@WebServlet("/S0025.html")
public class S0025Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		// saleId パラメータのチェック
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
		
		Sale sale = (new SaleService()).selectById(saleId);
		Account account = (new AccountService()).selectById(sale.getAccountId());
		Category category = (new CategoryService()).selectById(sale.getCategoryId());
		request.setAttribute("sale", sale);
		request.setAttribute("selectedAccount", account);
		request.setAttribute("selectedCategory", category);
		request.setAttribute("formattedSaleDate", DateUtil.formatLocDateToStr(sale.getSaleDate()));
		request.getRequestDispatcher("/S0025.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		int saleId = Integer.parseInt(request.getParameter("saleId"));
		
		List<String> errors = new ArrayList<>();

		// 権限チェック 未ログイン、権限なし（b'00'、b'10'）の場合
        HttpSession session = request.getSession();
        Login loginAccount = (Login) session.getAttribute("account");
        if (loginAccount == null || loginAccount.getAuthority().equals("b''") || loginAccount.getAuthority().equals("b'10'")) {
        	errors.add("権限がありません。");
        	Sale sale = (new SaleService()).selectById(saleId);
    		Account account = (new AccountService()).selectById(sale.getAccountId());
    		Category category = (new CategoryService()).selectById(sale.getCategoryId());
    		request.setAttribute("sale", sale);
    		request.setAttribute("selectedAccount", account);
    		request.setAttribute("selectedCategory", category);
    		request.setAttribute("formattedSaleDate", DateUtil.formatLocDateToStr(sale.getSaleDate()));
    		request.setAttribute("errors", errors);
    		request.getRequestDispatcher("/S0025.jsp").forward(request, response);
        }
        
		(new SaleService()).delete(saleId);

		// 削除完了後、一覧画面へリダイレクト（検索画面など）
		response.sendRedirect("S0021.html");
	}
}
