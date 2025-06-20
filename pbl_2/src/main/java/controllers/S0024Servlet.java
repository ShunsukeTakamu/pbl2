package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.Sale;
import services.SaleService;

@WebServlet("/S0024.html")
public class S0024Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/S0024.jsp").forward(request, response);
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    	HttpSession session = request.getSession();
        request.setCharacterEncoding("UTF-8");
        
        // Saleインスタンスを作成
        Sale s = (Sale) session.getAttribute("sale");
        
        if (s != null) {
        	// 更新処理
        	(new SaleService()).update(s);
        	
        	session.removeAttribute("sale");
        	session.removeAttribute("selectedAccount");
        	session.removeAttribute("selectedCategory");
        }
        
        // 更新後は一覧画面へリダイレクト
        response.sendRedirect("S0021.html");

    }
}
