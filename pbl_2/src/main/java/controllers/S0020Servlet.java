package controllers;

import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import services.SaleService;
import utils.DateUtil;

/**
 * Servlet implementation class S0020Servlet
 */
@WebServlet("/S0020.html")
public class S0020Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S0020Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Sale sale = new Sale();
		sale.setAccountId(0);
		sale.setCategoryId(0);
		request.setAttribute("sale", sale);
		ArrayList<Account> accounts = (new AccountService()).selectAll();
		request.setAttribute("accounts", accounts);
		ArrayList<Category> categories = (new CategoryService()).selectAll();
		request.setAttribute("categories", categories);
		request.setAttribute("dateStart", "");
		request.setAttribute("dateEnd", "");
		request.getRequestDispatcher("/S0020.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");

        // パラメータ取得
        String dateStart = request.getParameter("dateStart");
        String dateEnd = request.getParameter("dateEnd");
        String accountIdStr = request.getParameter("accountId");
        String categoryIdStr = request.getParameter("categoryId");
        String tradeName = request.getParameter("tradeName");
        String note = request.getParameter("note");

        List<String> errors = new ArrayList<>();
        AccountService as = new AccountService();
        CategoryService cs = new CategoryService();
        int accountId = Integer.parseInt(accountIdStr);
        int categoryId = Integer.parseInt(categoryIdStr);

        // 販売日チェック 未入力でない場合
        if (dateStart == null || dateStart.isBlank()) {
        } else {
        	try {
        		java.time.LocalDate.parse(dateStart);
        	} catch (DateTimeParseException e) {
        		errors.add("販売日（検索開始日）を正しく入力してください。");
        	}
        }
        
        if (dateEnd == null || dateEnd.isBlank()) {
        } else {
        	try {
        		java.time.LocalDate.parse(dateStart);
        	} catch (DateTimeParseException e) {
        		errors.add("販売日（検索終了日）を正しく入力してください。");
        	}
        }

        // エラーがあれば戻る
        if (!errors.isEmpty()) {
            Sale sale = new Sale();
            sale.setAccountId(accountId);
            sale.setCategoryId(categoryId);
            sale.setTradeName(tradeName);
            sale.setNote(note);

            request.setAttribute("errors", errors);
            request.setAttribute("sale", sale);
            request.setAttribute("dateStart", dateStart);
    		request.setAttribute("dateEnd", dateEnd);
    		request.setAttribute("accounts", as.selectAll());
            request.setAttribute("categories", cs.selectAll());
            request.getRequestDispatcher("/S0020.jsp").forward(request, response);
            return;
        }
        
        // 件数チェック
        List<Sale> sales = (new SaleService()).searchSales(dateStart, dateEnd, accountIdStr, categoryIdStr, tradeName, note);
        // 0件であれば戻る
        if (sales.isEmpty()) {
        	errors.add("検索結果はありません。");
        	Sale sale = new Sale();
            sale.setAccountId(accountId);
            sale.setCategoryId(categoryId);
            sale.setTradeName(tradeName);
            sale.setNote(note);

            request.setAttribute("errors", errors);
            request.setAttribute("sale", sale);
            request.setAttribute("dateStart", dateStart);
    		request.setAttribute("dateEnd", dateEnd);
    		request.setAttribute("accounts", as.selectAll());
            request.setAttribute("categories", cs.selectAll());
            request.getRequestDispatcher("/S0020.jsp").forward(request, response);
            return;
        }

        // 正常時 → 検索結果画面へ
        HttpSession session = request.getSession();
        session.setAttribute("dateStart", dateStart);
        session.setAttribute("dateEnd", dateEnd);
        session.setAttribute("accountIdStr", accountIdStr);
        session.setAttribute("categoryIdStr", categoryIdStr);
        session.setAttribute("partOfTradeName", tradeName);
        session.setAttribute("patrOfNote", note);
	    
	    // 販売日 2015-01-15 を 2015/01/15 に変更
	    List<String> formattedDates = sales.stream()
	    		.map(sale -> DateUtil.formatLocDateToStr(sale.getSaleDate()))
	    		.collect(Collectors.toList());
	    session.setAttribute("formattedDates", formattedDates);
	    
        response.sendRedirect("S0021.html");
	}

}
