package controllers;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import forms.AccountEditForm;
import services.AccountValidation;
import utils.CommonUtil;
import utils.FormUtil;

@WebServlet("/S0042.html")
public class S0042Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // アカウント情報の取得とリクエスト属性への設定
        CommonUtil.setAccountAttributes(request);

        // 編集画面にフォワード
        request.getRequestDispatcher("S0042.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String accountId = request.getParameter("accountId");
        AccountEditForm form = new AccountEditForm(request);

        // 戻るボタン（バリデーション不要）
        if ("back".equals(action)) {
            FormUtil.setAccountFormAttributes(request, form, accountId);
            request.getRequestDispatcher("S0042.jsp").forward(request, response);
            return;
        }

        // バリデーション処理
        Map<String, String> errors = AccountValidation.validateForEdit(form);
        

        // 権限フラグのセット
        Map<String, Boolean> flags = AccountValidation.resolveAuthorityFlags(form.getAuthorities());
        flags.forEach(request::setAttribute);

        // 確認画面への引き継ぎ
        FormUtil.setAccountFormAttributes(request, form, accountId);

        // 分岐して遷移
        if ("delete".equals(action)) {
            request.getRequestDispatcher("S0044.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("S0043.jsp").forward(request, response);
        }
    }
}
