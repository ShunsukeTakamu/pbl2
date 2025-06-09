package controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.naming.NamingException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import utils.Db;

@WebServlet("/dbtest")
public class DbTestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/plain; charset=UTF-8");
        PrintWriter out = response.getWriter();

        try (Connection con = Db.open()) {
            if (con != null && !con.isClosed()) {
                out.println("✅ mariaDBに接続しました！");
            } else {
                out.println("❌ 接続できましたが、無効です。");
            }
        } catch (NamingException e) {
            out.println("⚠ NamingException（JNDIエラー）:");
            e.printStackTrace(out);
        } catch (Exception e) {
            out.println("❌ DB接続に失敗しました:");
            e.printStackTrace(out);
        }
    }
}
