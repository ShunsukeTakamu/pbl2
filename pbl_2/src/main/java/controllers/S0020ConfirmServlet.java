package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
import services.SaleService;
import utils.DateUtil;
import utils.Db;

/**
 * Servlet implementation class S0020ConfirmServlet
 */
@WebServlet("/S0020ConfirmServlet")
public class S0020ConfirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S0020ConfirmServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
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
    		request.setAttribute("accounts", getAccountList());
            request.setAttribute("categories", getCategoryList());
            request.getRequestDispatcher("S0020.jsp").forward(request, response);
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
    		request.setAttribute("accounts", getAccountList());
            request.setAttribute("categories", getCategoryList());
            request.getRequestDispatcher("S0020.jsp").forward(request, response);
            return;
        }

        // 正常時 → 検索結果画面へ
        HttpSession session = request.getSession();
	    session.setAttribute("saleList", sales);
	    
	    // 販売日 2015-01-15 を 2015/01/15 に変更
	    List<String> formattedDates = sales.stream()
	    		.map(sale -> DateUtil.formatLocDateToStr(sale.getSaleDate()))
	    		.collect(Collectors.toList());
	    session.setAttribute("formattedDates", formattedDates);
	    
        response.sendRedirect("S0021Servlet");
	}
	
	// アカウント一覧
    private List<Account> getAccountList() {
        List<Account> list = new ArrayList<>();
        try (Connection conn = Db.open()) {
            String sql = "SELECT account_id, name FROM accounts";
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Account a = new Account();
                    a.setAccountId(rs.getInt("account_id"));
                    a.setName(rs.getString("name"));
                    list.add(a);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // カテゴリ一覧
    private List<Category> getCategoryList() {
        List<Category> list = new ArrayList<>();
        try (Connection conn = Db.open()) {
            String sql = "SELECT category_id, category_name FROM categories";
            try (PreparedStatement ps = conn.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Category c = new Category();
                    c.setCategoryId(rs.getInt("category_id"));
                    c.setCategoryName(rs.getString("category_name"));
                    list.add(c);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
