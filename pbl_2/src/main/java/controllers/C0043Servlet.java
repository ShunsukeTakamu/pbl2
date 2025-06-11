package controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import beans.Account;
import services.AccountService;

/**
 * Servlet implementation class C0043Servlet
 */
@WebServlet("/C0043Servlet")
public class C0043Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public C0043Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");

		try {
			// パラメータ取得
			int accountId = Integer.parseInt(request.getParameter("accountId"));
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String[] authorities = request.getParameterValues("authorities");

			// 権限値の計算（bit演算）
			byte authorityByte = 0;
			if (authorities != null) {
				for (String a : authorities) {
					authorityByte |= Integer.parseInt(a);
				}
			}

			// アカウントオブジェクト作成
			Account updated = new Account();
			updated.setAccountId(accountId);
			updated.setName(name);
			updated.setMail(email);
			updated.setPassword(password); // 必要に応じてハッシュ化
			updated.setAuthority(new byte[] { authorityByte });

			// DB更新処理
			AccountService service = new AccountService();
			service.update(updated);

			// 検索結果画面へリダイレクト
			response.sendRedirect("C0041Servlet");

		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "アカウントの更新に失敗しました。");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
	}

}
