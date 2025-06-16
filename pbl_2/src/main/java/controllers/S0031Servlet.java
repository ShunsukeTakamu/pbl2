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
        String role = request.getParameter("role");
        
        byte authority;
        switch(role) {
        	case "none":
            authority = 0;  
            break;
        case "view":
            authority = 1;  
            break;
        case "update":
            authority = 2;  
            break;
        default:
            authority = 0;  // デフォルトは権限なし
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
        session.removeAttribute("role");
        
        response.sendRedirect("S0030.jsp");
	}

}
