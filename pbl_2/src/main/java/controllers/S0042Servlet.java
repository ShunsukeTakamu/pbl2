package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import forms.AccountEditForm;
import services.AccountValidation;
import utils.CommonUtil;
import utils.FormUtil;

@WebServlet("/S0042.html")
public class S0042Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		CommonUtil.setAccountAttributes(request);
		request.getRequestDispatcher("S0042.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		String accountId = request.getParameter("accountId");
		AccountEditForm form = new AccountEditForm(request);

		if ("back".equals(action)) {
			FormUtil.setAccountFormAttributes(request, form, accountId);
			request.getRequestDispatcher("S0042.jsp").forward(request, response);
			return;
		}
		Map<String, String> errors = new LinkedHashMap<>(AccountValidation.validateForEdit(form));
		if (!errors.isEmpty()) {
		    List<String> errorList = new ArrayList<>(errors.values());

		    request.setAttribute("errors", errors);
		    request.setAttribute("errorsList", errorList);

		    FormUtil.setAccountFormAttributes(request, form, accountId);
		    request.getRequestDispatcher("S0042.jsp").forward(request, response);
		    return;
		}

		Map<String, Boolean> flags = AccountValidation.resolveAuthorityFlags(form.getAuthorities());
		flags.forEach(request::setAttribute);

		FormUtil.setAccountFormAttributes(request, form, accountId);
		if ("delete".equals(action)) {
			request.getRequestDispatcher("S0044.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("S0043.jsp").forward(request, response);
		}
	}
}
