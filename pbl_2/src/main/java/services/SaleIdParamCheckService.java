package services;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SaleIdParamCheckService {

    public Integer check(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String saleIdStr = request.getParameter("saleId");
        if (saleIdStr == null || saleIdStr.isEmpty()) {
            response.sendRedirect(request.getContextPath() + "/accessDenied.jsp");
            return null;
        }

        try {
            return Integer.parseInt(saleIdStr);
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/accessDenied.jsp");
            return null;
        }
    }
}
