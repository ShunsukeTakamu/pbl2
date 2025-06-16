package controllers;

import java.io.IOException;
import java.util.ArrayList;

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
        Sale detail = (new SaleService()).selectById(saleId);

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
