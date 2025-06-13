package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import services.SaleService;

/**
 * Servlet implementation class C0020Servlet
 */
@WebServlet("/C0020.html")
public class C0020Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public C0020Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		SaleService saleService = new SaleService();
        Map<String, Integer> salesMap = saleService.getSalesByAccount();

        request.setAttribute("accountNames", new ArrayList<>(salesMap.keySet()));
        request.setAttribute("salesTotals", new ArrayList<>(salesMap.values()));

        request.getRequestDispatcher("/C0020.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
