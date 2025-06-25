package controllers;
 
import java.io.IOException;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import forms.AccountForm;
import services.AccountValidation;
import utils.SessionUtil;
 
@WebServlet("/S0030.html")
public class S0030 extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public S0030() {
        super();
    }
 
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null) {
	        SessionUtil.clearAccountForm(session);
	    }
		request.getRequestDispatcher("S0030.jsp").forward(request, response);
	}
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		AccountForm form = new AccountForm(request);
 
		
		 Map<String, String> errors = AccountValidation.validateForRegister(form);

		    if (!errors.isEmpty()) {
		        request.setAttribute("errors", errors);
		        request.setAttribute("name", form.getName());
		        request.setAttribute("mail", form.getMail());
		        request.setAttribute("password", form.getPassword());
		        request.setAttribute("confirmPassword", form.getConfirmPassword());
		        request.setAttribute("authorities", form.getAuthorities());
		        request.getRequestDispatcher("S0030.jsp").forward(request, response);
		        return;
		    }
		    
		HttpSession session = request.getSession();
		SessionUtil.saveAccountForm(session, form);
 
		response.sendRedirect("S0031.html");
	}
 
}