package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/S0040.html")
public class S0040Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public S0040Servlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/S0040.jsp").forward(request, response);
	}

	// 検索処理
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		// フォーム入力を受け取る 
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String[] authorities = request.getParameterValues("authorities");

		if (name != null && name.getBytes("UTF-8").length >= 21) {
			request.setAttribute("error", "氏名が長すぎます");
			request.getRequestDispatcher("/S0040.jsp").forward(request, response);
			return;
		}
		if (email != null && email.getBytes("UTF-8").length >= 100) {
			request.setAttribute("error", "メールが長すぎます");
			request.getRequestDispatcher("/S0040.jsp").forward(request, response);
			return;
		}
		
		// セッションに条件を保存、40の初期値として使用
		request.getSession().setAttribute("searchName", name);
		request.getSession().setAttribute("searchEmail", email);
		request.getSession().setAttribute("searchAuthorities", authorities);

		// リダイレクト
		response.sendRedirect("S0041.html");
	}

}
