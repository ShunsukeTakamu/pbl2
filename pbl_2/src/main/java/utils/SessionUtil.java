package utils;

import jakarta.servlet.http.HttpSession;

import forms.AccountForm;
import forms.AccountSearchForm;

public class SessionUtil {

    // 検索画面（S0040/S0041）用
    // 検索条件フォームを保存するセッション属性名
    private static final String ATTR_SEARCH_FORM = "searchAccountForm";

    /**
     * 検索条件をセッションから削除
     * 使用例：検索条件クリアボタン押下時（S0040Servlet）
     */
    public static void clearSearchAccount(HttpSession session) {
        session.removeAttribute(ATTR_SEARCH_FORM);
    }

    /**
     * 検索条件フォームをセッションに保存
     * 使用例：検索ボタン押下後（S0040Servlet）
     */
    public static void saveSearchAccount(HttpSession session, AccountSearchForm form) {
        session.setAttribute(ATTR_SEARCH_FORM, form);
    }

    /**
     * 検索条件フォームをセッションから取得
     * 使用例：検索結果画面（S0041Servlet）
     * セッション切れや初回アクセス時は空のフォームを返却
     */
    public static AccountSearchForm getSearchAccount(HttpSession session) {
        Object obj = session.getAttribute(ATTR_SEARCH_FORM);

        if (obj instanceof AccountSearchForm form) {
            System.out.println("[SessionUtil] 保存直前: name=" + form.getName() + ", email=" + form.getEmail());
            session.setAttribute("searchAccountForm", form);
            System.out.println("[SessionUtil] 保存完了: " + session.getAttribute("searchAccountForm"));
            return form;
        }

        return new AccountSearchForm(); // 初回アクセスやセッション切れ時のデフォルト
    }

    // 登録画面（S0030）用（入力保持)

    /**
     * 登録フォームの入力値を個別にクリア
     * 使用例：登録完了後などの初期化
     */
    public static void clearAccountForm(HttpSession session) {
        session.removeAttribute("name");
        session.removeAttribute("mail");
        session.removeAttribute("password");
        session.removeAttribute("confirmPassword");
        session.removeAttribute("authorities");
    }

    /**
     * 登録フォームの入力値を個別に保存
     * - 使用例：バリデーションエラー後に入力内容を再表示（S0030Servlet）
     */
    public static void saveAccountForm(HttpSession session, String name, String mail, String password, String confirmPassword, String[] authorities) {
        session.setAttribute("name", name);
        session.setAttribute("mail", mail);
        session.setAttribute("password", password);
        session.setAttribute("confirmPassword", confirmPassword);
        session.setAttribute("authorities", authorities);
    }

    /**
     * 登録フォーム（AccountForm）の内容を保存（オーバーロード）
     * - 使用例：フォームクラスを直接扱うケース
     */
    public static void saveAccountForm(HttpSession session, AccountForm form) {
        saveAccountForm(session, form.getName(), form.getMail(), form.getPassword(), form.getConfirmPassword(), form.getAuthorities());
    }

    // 定数定義（HTMLでの sessionScope アクセス時に使用)
    public static final String ATTR_FORM_NAME = "name";
    public static final String ATTR_FORM_MAIL = "mail";
    public static final String ATTR_FORM_PASSWORD = "password";
    public static final String ATTR_FORM_CONFIRM_PASSWORD = "confirmPassword";
    public static final String ATTR_FORM_AUTHORITIES = "authorities";
}
