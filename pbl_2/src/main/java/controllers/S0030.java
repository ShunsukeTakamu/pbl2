package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class S0030
 */
@WebServlet("/S0030.html")
public class S0030 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S0030() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("S0030.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		String mail = request.getParameter("mail");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		String role = request.getParameter("role");

		String errorMsg = null;
		
		if (name == null || name.isEmpty()
				|| mail == null || mail.isEmpty()
				|| password == null || password.isEmpty()
				|| confirmPassword == null || confirmPassword.isEmpty()
				|| role == null || role.isEmpty()) {
			errorMsg = "全ての必須項目を入力してください";
		} else if (!password.equals(confirmPassword)) {
			errorMsg = "パスワードと確認が一致しません";
		} else if (!mail.matches("^[\\w\\.-]+@[\\w\\.-]+$")) {
			errorMsg = "メールアドレスの形式が不正です";
		}

		if (errorMsg != null) {
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("S0030.jsp").forward(request, response);
			return;
		}
		HttpSession session = request.getSession();
		session.setAttribute("name", name);
		session.setAttribute("mail", mail);
		session.setAttribute("password", password);
		session.setAttribute("role", role);

		response.sendRedirect("S0031.jsp");
	}

}
