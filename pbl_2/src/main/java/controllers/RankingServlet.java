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

@WebServlet("/RankingServlet")
public class RankingServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SaleService saleService = new SaleService();
        Map<String, Integer> salesMap = saleService.getSalesByAccount();

        request.setAttribute("accountNames", new ArrayList<>(salesMap.keySet()));
        request.setAttribute("salesTotals", new ArrayList<>(salesMap.values()));

        request.getRequestDispatcher("/Ranking.jsp").forward(request, response);
    }
}
