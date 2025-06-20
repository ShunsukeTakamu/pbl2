package utils;

import jakarta.servlet.http.HttpSession;

import forms.AccountForm;
import forms.AccountSearchForm;

public class SessionUtil {

    // 属性名定数
    public static final String ATTR_SEARCH_NAME = "searchName";
    public static final String ATTR_SEARCH_EMAIL = "searchEmail";
    public static final String ATTR_SEARCH_AUTHORITIES = "searchAuthorities";

    // 検索条件のクリア
    public static void clearSearchAccount(HttpSession session) {
        session.removeAttribute(ATTR_SEARCH_NAME);
        session.removeAttribute(ATTR_SEARCH_EMAIL);
        session.removeAttribute(ATTR_SEARCH_AUTHORITIES);
    }

    // 検索条件の保存（個別値）
    public static void saveSearchAccount(HttpSession session, String name, String email, String[] authorities) {
        session.setAttribute(ATTR_SEARCH_NAME, name);
        session.setAttribute(ATTR_SEARCH_EMAIL, email);
        session.setAttribute(ATTR_SEARCH_AUTHORITIES, authorities);
    }

    // 検索条件の保存（フォーム型）
    public static void saveSearchAccount(HttpSession session, AccountSearchForm form) {
        saveSearchAccount(session, form.getName(), form.getEmail(), form.getAuthorities());
    }
    
    // 登録入力のクリア
    public static void clearAccountForm(HttpSession session) {
    	session.removeAttribute("name");
    	session.removeAttribute("mail");
    	session.removeAttribute("password");
    	session.removeAttribute("confirmPassword");
    	session.removeAttribute("authorities");
    }

    // 登録入力の保存（個別）
    public static void saveAccountForm(HttpSession session, String name, String mail, String password, String confirmPassword, String[] authorities) {
    	session.setAttribute("name", name);
    	session.setAttribute("mail", mail);
    	session.setAttribute("password", password);
    	session.setAttribute("confirmPassword", confirmPassword);
    	session.setAttribute("authorities", authorities);
    }

    // 登録入力の保存（フォーム型）
    public static void saveAccountForm(HttpSession session, AccountForm form) {
    	saveAccountForm(session, form.getName(), form.getMail(), form.getPassword(), form.getConfirmPassword(), form.getAuthorities());
    }
    
    // アカウント登録用（フォーム入力一時保存）
    public static final String ATTR_FORM_NAME = "name";
    public static final String ATTR_FORM_MAIL = "mail";
    public static final String ATTR_FORM_PASSWORD = "password";
    public static final String ATTR_FORM_CONFIRM_PASSWORD = "confirmPassword";
    public static final String ATTR_FORM_AUTHORITIES = "authorities";

    
}
