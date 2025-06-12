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

/**
 * Servlet implementation class C0041Servlet
 */
@WebServlet("/C0041Servlet")
public class C0041Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public C0041Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		
		String name = (String) request.getSession().getAttribute("searchName");
		String email = (String) request.getSession().getAttribute("searchEmail");
		String[] authorities = (String[]) request.getSession().getAttribute("searchAuthorities");

//		System.out.println("セッションから取得: name=" + name);
//		System.out.println("セッションから取得: email=" + email);
//		System.out.println("セッションから取得: authorities=" + java.util.Arrays.toString(authorities));

		
	    AccountService service = new AccountService();
	    List<Account> accounts = service.searchAccounts(name, email, authorities);

	    request.setAttribute("accounts", accounts);
	    
	    request.setAttribute("name", name);
	    request.setAttribute("email", email);
	    request.setAttribute("authorities", authorities);
	    
	    request.getRequestDispatcher("C0041.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
