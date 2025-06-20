package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.Sale;
import services.AccountService;
import services.CategoryService;
import services.SaleIdParamCheckService;
import services.SaleService;
import utils.CommonUtil;

@WebServlet("/S0022.html")
public class S0022Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public S0022Servlet() {
        super();
    }

    /**
     *s
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SaleIdParamCheckService paramService = new SaleIdParamCheckService();
        Integer saleId = paramService.check(request, response);
        if (saleId == null) {
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
            formattedDate = CommonUtil.formatLocDateToStr(sale.getSaleDate());
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SaleIdParamCheckService paramService = new SaleIdParamCheckService();
        Integer saleId = paramService.check(request, response);
        if (saleId == null) {
            return; 
        }
        
        String button = request.getParameter("button");
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
            formattedDate = CommonUtil.formatLocDateToStr(sale.getSaleDate());
        }

        request.setAttribute("sale", sale);
        request.setAttribute("accountName", accountName);
        request.setAttribute("categoryName", categoryName);
        request.setAttribute("formattedDate", formattedDate);
        request.getRequestDispatcher("/S0022.jsp").forward(request, response);
	    
	}
}
