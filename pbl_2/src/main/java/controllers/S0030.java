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
 
/**
* Servlet implementation class S0030
*/
@WebServlet("/S0030.html")
public class S0030 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S0030() {
        super();
        // TODO Auto-generated constructor stub
    }
 
	/** GETリクエスト処理
	  * 初回アクセス or 戻るボタン押下時に、セッションの入力データをクリアして入力フォームを表示
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// セッションを取得（既存のセッションがなければ null を返す）
		HttpSession session = request.getSession(false);
		// 入力内容をクリア
		if (session != null) {
	        SessionUtil.clearAccountForm(session);
	    }
		request.getRequestDispatcher("S0030.jsp").forward(request, response);
	}
 
	/**
	 * 入力内容の検証を行い、エラーがあれば入力画面へ戻す。成功時は確認画面に遷移
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		// フォームからの入力値を取得
		AccountForm form = new AccountForm(request);
 
		
		 Map<String, String> errors = AccountValidation.validateForRegister(form);

		    // エラーあり：入力値をセットして戻す
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
		// バリデーション通過時：セッションに入力値を保存し、確認画面へリダイレクト
		HttpSession session = request.getSession();
		SessionUtil.saveAccountForm(session, form);
 
		response.sendRedirect("S0031.html");
	}
 
}