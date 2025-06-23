package utils;

import jakarta.servlet.http.HttpSession;

import forms.AccountForm;
import forms.AccountSearchForm;

public class SessionUtil {

    // 検索画面（S0040/S0041）用
    // 検索条件フォームのセッションキー
    private static final String SEARCH_FORM_KEY = "searchAccountForm";

    /**
     * 検索条件をセッションから削除（検索条件クリア時に使用）
     */
    public static void clearSearchAccount(HttpSession session) {
        session.removeAttribute(SEARCH_FORM_KEY);
    }

    /**
     * 検索条件フォームをセッションに保存
     */
    public static void saveSearchAccount(HttpSession session, AccountSearchForm form) {
        session.setAttribute(SEARCH_FORM_KEY, form);
    }

    /**
     * セッションから検索条件フォームを取得
     * セッションに存在しない場合は空のフォームを返す
     */
    public static AccountSearchForm getSearchAccount(HttpSession session) {
        Object obj = session.getAttribute(SEARCH_FORM_KEY);

        if (obj instanceof AccountSearchForm form) {
            session.setAttribute(SEARCH_FORM_KEY, form);
            return form;
        }

        return new AccountSearchForm(); // 初回アクセスやセッション切れ時
    }

    // 登録画面（S0030）用：入力保持
    // 登録フォームの各項目を個別にセッションから削除（登録完了後などに使用）
     
    public static void clearAccountForm(HttpSession session) {
        session.removeAttribute(FORM_NAME);
        session.removeAttribute(FORM_MAIL);
        session.removeAttribute(FORM_PASSWORD);
        session.removeAttribute(FORM_CONFIRM_PASSWORD);
        session.removeAttribute(FORM_AUTHORITIES);
    }

    // 登録フォームの各項目をセッションに保存（バリデーションエラー時など） 
    public static void saveAccountForm(HttpSession session,
                                       String name,
                                       String mail,
                                       String password,
                                       String confirmPassword,
                                       String[] authorities) {
        session.setAttribute(FORM_NAME, name);
        session.setAttribute(FORM_MAIL, mail);
        session.setAttribute(FORM_PASSWORD, password);
        session.setAttribute(FORM_CONFIRM_PASSWORD, confirmPassword);
        session.setAttribute(FORM_AUTHORITIES, authorities);
    }

     // AccountForm を使ってセッションに保存（オーバーロード）     
    public static void saveAccountForm(HttpSession session, AccountForm form) {
        saveAccountForm(session, form.getName(),
                              form.getMail(),
                              form.getPassword(),
                              form.getConfirmPassword(),
                              form.getAuthorities());
    }

    // JSP で使用される属性名（定数）
    public static final String FORM_NAME = "name";
    public static final String FORM_MAIL = "mail";
    public static final String FORM_PASSWORD = "password";
    public static final String FORM_CONFIRM_PASSWORD = "confirmPassword";
    public static final String FORM_AUTHORITIES = "authorities";
}
