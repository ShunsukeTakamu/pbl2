package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.Account;
import beans.Category;
import beans.Sale;
import services.AccountService;
import services.CategoryService;
import services.SaleIdParamCheckService;
import services.SaleService;
import utils.DateUtil;

@WebServlet("/S0025.html")
public class S0025Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SaleIdParamCheckService paramService = new SaleIdParamCheckService();
        Integer saleId = paramService.check(request, response);
        if (saleId == null) {
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
        
		(new SaleService()).delete(saleId);

		// 削除完了後、一覧画面へリダイレクト（検索画面など）
		response.sendRedirect("S0021.html");
	}
}
