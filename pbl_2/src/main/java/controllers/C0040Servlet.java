package controllers;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.Account;
import services.AccountService;

@WebServlet("/C0040Servlet")
public class C0040Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public C0040Servlet() {
		super();
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    request.getRequestDispatcher("/C0040.jsp").forward(request, response);
	}

	// 検索処理
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    request.setCharacterEncoding("UTF-8");

	    String name = request.getParameter("name");
	    String email = request.getParameter("email");
	    String[] authorities = request.getParameterValues("authority");

	    if (name != null && name.getBytes("UTF-8").length >= 21) {
	        request.setAttribute("error", "氏名が長すぎます");
	        request.getRequestDispatcher("/C0040.jsp").forward(request, response);
	        return;
	    }
	    if (email != null && email.getBytes("UTF-8").length >= 100) {
	        request.setAttribute("error", "メールが長すぎます");
	        request.getRequestDispatcher("/C0040.jsp").forward(request, response);
	        return;
	    }

	    AccountService service = new AccountService();
	    List<Account> accounts = service.searchAccounts(name, email, authorities);

	    request.setAttribute("accounts", accounts);
	    request.getRequestDispatcher("/S0041.jsp").forward(request, response);
	}

}
