package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.Account;
import beans.Category;
import beans.Sale;
import forms.SaleForm;
import services.AccountService;
import services.CategoryService;
import services.SaleIdParamCheckService;
import services.SaleService;
import services.SaleValidation;
import utils.ReturnPair;

@WebServlet("/S0023.html")
public class S0023Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public S0023Servlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SaleIdParamCheckService paramService = new SaleIdParamCheckService();
        Integer saleId = paramService.check(request, response);
        if (saleId == null) {
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
        request.getRequestDispatcher("/S0023.jsp").forward(request, response);
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // パラメータ取得
        SaleForm saleForm = new SaleForm(request);

        AccountService as = new AccountService();
        CategoryService cs = new CategoryService();

        ReturnPair returnPair = SaleValidation.validate(saleForm);
        Sale sale = returnPair.getSale();
        Map<String, String> errors = returnPair.getErrors();

        // エラーがあれば編集画面に戻す
        if (!errors.isEmpty()) {
            request.setAttribute("errors", errors);
            request.setAttribute("sale", sale);
            request.setAttribute("accounts", as.selectAll());
            request.setAttribute("categories", cs.selectAll());
            request.getRequestDispatcher("/S0023.jsp").forward(request, response);
            return;
        }

        // 確認画面へ進む：IDから名前を取得してセット
        HttpSession session = request.getSession();
        session.setAttribute("sale", sale);
        session.setAttribute("selectedAccount", as.selectById(sale.getAccountId()));
        session.setAttribute("selectedCategory", cs.selectById(sale.getCategoryId()));
        response.sendRedirect("S0024.html");
    }
}
