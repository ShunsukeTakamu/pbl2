package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		HttpSession session = request.getSession(false);
		if (session != null) {
	        session.removeAttribute("name");
	        session.removeAttribute("mail");
	        session.removeAttribute("password");
	        session.removeAttribute("confirmPassword");
	        session.removeAttribute("role");
	    }
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

		List<String> errors = new ArrayList<>();
		
		if (name == null || name.isEmpty()) {
			errors.add("氏名を入力してください。");
		} else if (name.getBytes("UTF-8").length >= 21) {
			errors.add("氏名が長すぎます。");
		}
		if (mail == null || mail.isEmpty()) {
			errors.add("メールアドレスを入力してください。");
		} else if (mail.getBytes("UTF-8").length >= 101) {
			errors.add("メールアドレスが長すぎます。");
		} else if (!mail.matches("^[\\w\\.-]+@[\\w\\.-]+$")) {
			errors.add("メールアドレスを正しく入力してください。");
		} 
		if (password == null || password.isEmpty()) {
			errors.add("パスワードを入力してください。");
		} else if (password.getBytes("UTF-8").length >= 31) {
			errors.add("パスワードが長すぎます。");
		}
		if (confirmPassword == null || confirmPassword.isEmpty()) {
			errors.add("パスワード（確認）を入力してください。");
		}
		if ((password != null && !password.isEmpty()) && (confirmPassword != null && !confirmPassword.isEmpty()) && !password.equals(confirmPassword)) {
			errors.add("パスワードとパスワード（確認）の入力内容が異なります。");
		}

		if (!errors.isEmpty()) {
			request.setAttribute("errorMsg", errors);
			request.setAttribute("name", name);
		    request.setAttribute("mail", mail);
		    request.setAttribute("password", password);
		    request.setAttribute("confirmPassword", confirmPassword);
		    request.setAttribute("role", role);
			request.getRequestDispatcher("S0030.jsp").forward(request, response);
			return;
		}
		HttpSession session = request.getSession();
		session.setAttribute("name", name);
		session.setAttribute("mail", mail);
		session.setAttribute("password", password);
		session.setAttribute("confirmPassword", confirmPassword);
		session.setAttribute("role", role);

		response.sendRedirect("S0031.jsp");
	}

}
