package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import forms.AccountForm;
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

		
		List<String> errors = new ArrayList<>();
		
		if (form.getName() == null || form.getName().isEmpty()) {
			errors.add("氏名を入力してください。");
		} else if (form.getName().getBytes("UTF-8").length >= 21) {
			errors.add("氏名が長すぎます。");
		}
		if (form.getMail() == null || form.getMail().isEmpty()) {
			errors.add("メールアドレスを入力してください。");
		} else if (form.getMail().getBytes("UTF-8").length >= 101) {
			errors.add("メールアドレスが長すぎます。");
		} else if (!form.getMail().matches("^[\\w\\.-]+@[\\w\\.-]+\\.[a-zA-Z]{1,}$")) {
			errors.add("メールアドレスを正しく入力してください。");
		} 
		if (form.getPassword() == null ||form.getPassword().isEmpty()) {
			errors.add("パスワードを入力してください。");
		} else if (form.getPassword().getBytes("UTF-8").length >= 31) {
			errors.add("パスワードが長すぎます。");
		}
		if (form.getConfirmPassword() == null || form.getConfirmPassword().isEmpty()) {
			errors.add("パスワード（確認）を入力してください。");
		}
		if ((form.getPassword() != null && form.getConfirmPassword() != null) && !form.getPassword().equals(form.getConfirmPassword())) {
			errors.add("パスワードとパスワード（確認）の入力内容が異なります。");
		}
		if (form.getAuthorities() == null || form.getAuthorities().length == 0) {
			errors.add("権限を1つ以上選択してください。");
		}

		// 入力エラーがあった場合、入力値とエラーをリクエストにセットして画面に戻る
		if (!errors.isEmpty()) {
			request.setAttribute("errorMsg", errors);
			request.setAttribute("name", form.getName());
		    request.setAttribute("mail", form.getMail());
		    request.setAttribute("password", form.getPassword());
		    request.setAttribute("confirmPassword", form.getConfirmPassword());
		    request.setAttribute("authorities", form.getAuthorities());
			request.getRequestDispatcher("S0030.jsp").forward(request, response);// 入力画面に戻る
			return;
		}
				
		// バリデーション通過時：セッションに入力値を保存し、確認画面へリダイレクト
		HttpSession session = request.getSession();
		SessionUtil.saveAccountForm(session, form);

		response.sendRedirect("S0031.jsp");
	}

}
