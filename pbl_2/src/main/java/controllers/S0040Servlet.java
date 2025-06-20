package controllers;
import java.io.IOException;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import forms.AccountSearchForm;
import services.AccountValidation;
import utils.SessionUtil;

@WebServlet("/S0040.html")
public class S0040Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SessionUtil.clearSearchAccount(request.getSession());
        request.getRequestDispatcher("/S0040.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String action = request.getParameter("action");

        if ("clear".equals(action)) {
            SessionUtil.clearSearchAccount(request.getSession());
            request.getRequestDispatcher("/S0040.jsp").forward(request, response);
            return;
        }

        AccountSearchForm form = new AccountSearchForm(request);
        Map<String, String> errors = AccountValidation.validate(form);

        if (!errors.isEmpty()) {
            request.setAttribute("error", String.join("/", errors.values()));
            request.getRequestDispatcher("/S0040.jsp").forward(request, response);
            return;
        }

        // フォーム全体をセッションに保存
        SessionUtil.saveSearchAccount(request.getSession(), form);
        response.sendRedirect("S0041.html");
    }
}
