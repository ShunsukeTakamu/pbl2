package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import utils.Db;

/**
 * Servlet implementation class S0031Servlet
 */
@WebServlet("/S0031.html")
public class S0031Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S0031Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("S0031.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッション取得（登録処理後にクリアする用）
		HttpSession session = request.getSession();
		//入力値取得
        String name = request.getParameter("name");
        String mail = request.getParameter("mail");
        String password = request.getParameter("password");
        String[] authorities = request.getParameterValues("authorities");
        
        byte authority = 0;
        if (authorities != null) {
            for (String role : authorities) {
                try {
                    byte value = Byte.parseByte(role);
                    authority |= value;  // 複数権限を1バイトにまとめる（ビット和）
                } catch (NumberFormatException e) {
                    // 無効な値はスキップ（必要ならログ出力）
                }
            }
        }
        
        try (Connection conn = Db.open()) {
        	//メール重複チェック
            String sql = "SELECT COUNT(*) FROM accounts WHERE mail = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            	stmt.setString(1, mail);
            	 var rs = stmt.executeQuery();
                 if (rs.next() && rs.getInt(1) > 0) {
                	 List<String> errorMsg = new ArrayList<>();
                	 errorMsg.add("このメールアドレスはすでに登録されています。");
                	 request.setAttribute("errorMsg", errorMsg);
                     request.getRequestDispatcher("S0031.jsp").forward(request, response);
                     return;
                 }
            }
            
            // アカウント登録処理
            String insertSql = "INSERT INTO accounts (name, mail, password, authority) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertSql)) {
                stmt.setString(1, name);
                stmt.setString(2, mail);
                stmt.setString(3, password);
                stmt.setByte(4, authority);
                stmt.executeUpdate();
            }
            
        } catch (Exception e) {
            throw new ServletException("アカウント登録に失敗しました", e);
        }
        // セッションの入力情報をクリア
        session.removeAttribute("name");
        session.removeAttribute("mail");
        session.removeAttribute("password");
        session.removeAttribute("authorities");
        
        response.sendRedirect("S0030.html");
	}

}
