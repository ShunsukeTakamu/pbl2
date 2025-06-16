package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

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
        String accountName = (new AccountService()).selectById(sale.getAccountId()).getName();
        String categoryName = (new CategoryService()).selectById(sale.getCategoryId()).getCategoryName();

        request.setAttribute("sale", sale);
        request.setAttribute("accountName", accountName);
        request.setAttribute("categoryName", categoryName);
        
        // 日付のフォーマットを整える
        String formattedDate = DateUtil.formatLocDateToStr(sale.getSaleDate());
        request.setAttribute("formattedDate", formattedDate);
        request.getRequestDispatcher("S0022.jsp").forward(request, response);
    }
}
