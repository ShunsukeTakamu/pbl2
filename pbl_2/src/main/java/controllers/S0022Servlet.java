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

import beans.Login;
import beans.Sale;
import services.AccountService;
import services.CategoryService;
import services.SaleService;
import utils.DateUtil;

@WebServlet("/S0022.html")
public class S0022Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public S0022Servlet() {
        super();
    }

    /**
     *
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
        String accountName = null;
        String categoryName = null;
        String formattedDate = null;
        if (sale != null) {
        	accountName = (new AccountService()).selectById(sale.getAccountId()).getName();
        	categoryName = (new CategoryService()).selectById(sale.getCategoryId()).getCategoryName();
        	// 日付のフォーマットを整える
            formattedDate = DateUtil.formatLocDateToStr(sale.getSaleDate());
        }

        request.setAttribute("sale", sale);
        request.setAttribute("accountName", accountName);
        request.setAttribute("categoryName", categoryName);
        request.setAttribute("formattedDate", formattedDate);
        request.getRequestDispatcher("/S0022.jsp").forward(request, response);
    }
    
    /**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
        
        String button = request.getParameter("button");
		
		// 権限チェック ログイン済み、権限あり（b'01'、b'11'）の場合 画面遷移
        HttpSession session = request.getSession();
        Login loginAccount = (Login) session.getAttribute("account");
        if (loginAccount != null && ( loginAccount.getAuthority().equals("b'1'") || loginAccount.getAuthority().equals("b'11'"))) {
        	
        	if (button != null && !button.isEmpty()) {
        		// buttonパラメータがない（もしくは空）の場合の処理
        		switch (button) {
        		case "edit":
        			// 編集ボタンが押された時の処理
        			response.sendRedirect("S0023.html?saleId=" + saleId);
                	return;
        			
        		case "delete":
        			// 削除ボタンが押された時の処理
        			response.sendRedirect("S0025.html?saleId=" + saleId);
                	return;
        		}
        	}
        	
        }
        
        List<String> errors = new ArrayList<>();
        errors.add("権限がありません。");
        request.setAttribute("errors", errors);
	    
	    Sale sale = (new SaleService()).selectById(saleId);
	    String accountName = null;
        String categoryName = null;
        String formattedDate = null;
        if (sale != null) {
        	accountName = (new AccountService()).selectById(sale.getAccountId()).getName();
        	categoryName = (new CategoryService()).selectById(sale.getCategoryId()).getCategoryName();
        	// 日付のフォーマットを整える
            formattedDate = DateUtil.formatLocDateToStr(sale.getSaleDate());
        }

        request.setAttribute("sale", sale);
        request.setAttribute("accountName", accountName);
        request.setAttribute("categoryName", categoryName);
        request.setAttribute("formattedDate", formattedDate);
        request.getRequestDispatcher("/S0022.jsp").forward(request, response);
	    
	}
}
