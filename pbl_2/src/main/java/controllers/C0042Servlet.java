package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import beans.Account;
import services.AccountService;

/**
 * Servlet implementation class C0042Servlet
 */
@WebServlet("/C0042Servlet")
public class C0042Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public C0042Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String idStr = request.getParameter("id");
		if(idStr != null) {
			int id = Integer.parseInt(idStr);
			AccountService service = new AccountService();
			Account account = service.findById(id);
			request.setAttribute("account", account);
		}
		request.getRequestDispatcher("C0042.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Account loginUser = (Account) session.getAttribute("loginUser");
		
		//権限チェック
		if((loginUser.getAuthorityLabel() & 0b10) == 0) {
			request.setAttribute("AccessDenied", true);
			request.getRequestDispatcher("C0042.jsp").forward(request, response);
			return;
		}
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String passwordConfirm = request.getParameter("passwordConfirm");
		String[] authorities = request.getParameterValues("autorities");
	}

}
