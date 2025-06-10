package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class C0040Servlet
 */
@WebServlet("/C0040Servlet")
public class C0040Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public C0040Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.setCharacterEncoding("UTF-8");
		
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String authority = request.getParameter("Authority");
		
		if(name != null && name.getBytes("UTF-8").length >= 21) {
			request.setAttribute("error", "氏名が長すぎます");
			request.getRequestDispatcher("/C0040.jsp").forward(request, response);
		}
		
		if(email != null && email.getBytes("UTF-8").length >= 100) {
			request.setAttribute("error", "メールが長すぎます");
			request.getRequestDispatcher("/C0040.jsp").forward(request, response);
	}
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
