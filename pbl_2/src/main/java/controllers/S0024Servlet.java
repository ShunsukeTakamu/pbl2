package controllers;

import java.io.IOException;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.Sale;
import services.SaleService;

@WebServlet("/S0024.html")
public class S0024Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("S0024.jsp").forward(request, response);
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        // フォームから受け取る値を取得
        int saleId = Integer.parseInt(request.getParameter("saleId"));
        String saleDate = request.getParameter("saleDate");
        int accountId = Integer.parseInt(request.getParameter("accountId"));
        int categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String tradeName = request.getParameter("tradeName");
        int unitPrice = Integer.parseInt(request.getParameter("unitPrice"));
        int saleNumber = Integer.parseInt(request.getParameter("saleNumber"));
        String note = request.getParameter("note");
        
        // Saleインスタンスを作成
        Sale s = new Sale(
        		saleId,
        		LocalDate.parse(saleDate),
        		accountId,
        		categoryId,
        		tradeName,
        		unitPrice,
        		saleNumber,
        		note);
        
        // 更新処理
        (new SaleService()).update(s);
        
        // 更新後は一覧画面へリダイレクト
        response.sendRedirect("S0021.html");

    }
}
