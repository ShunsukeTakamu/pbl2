package controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class S0031Servlet
 */
@WebServlet("/S0031.html")
public class S0031Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static final String DB_URL = "jdbc:mariadb://192.168.5.172:3306/PBL";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "root";
    
    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new ServletException("JDBCドライバの読み込みに失敗しました", e);
        }
    }
    
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
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
        
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
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
            
            String insertSql = "INSERT INTO accounts (name, mail, password, authority) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertSql)) {
                stmt.setString(1, name);
                stmt.setString(2, mail);
                stmt.setString(3, password);
                stmt.setByte(4, authority);
                stmt.executeUpdate();
            }
            
        } catch (SQLException e) {
            throw new ServletException("アカウント登録に失敗しました", e);
        }
        session.removeAttribute("name");
        session.removeAttribute("mail");
        session.removeAttribute("password");
        session.removeAttribute("authorities");
        
        response.sendRedirect("S0030.jsp");
	}

}
