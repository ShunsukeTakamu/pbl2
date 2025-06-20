package utils;

import jakarta.servlet.http.HttpSession;

import forms.AccountForm;
import forms.AccountSearchForm;

public class SessionUtil {

    // 属性名定数
    private static final String ATTR_SEARCH_FORM = "searchAccountForm";

    // 検索条件のクリア
    public static void clearSearchAccount(HttpSession session) {
        session.removeAttribute(ATTR_SEARCH_FORM);
    }

    // 検索条件の保存（フォームごと保存）
    public static void saveSearchAccount(HttpSession session, AccountSearchForm form) {
        session.setAttribute(ATTR_SEARCH_FORM, form);
    }

    // 検索条件の取得
    public static AccountSearchForm getSearchAccount(HttpSession session) {
        Object obj = session.getAttribute(ATTR_SEARCH_FORM);
        
        if (obj instanceof AccountSearchForm form) {
        	 System.out.println("[SessionUtil] 保存直前: name=" + form.getName() + ", email=" + form.getEmail());
        	    session.setAttribute("searchAccountForm", form);
        	    System.out.println("[SessionUtil] 保存完了: " + session.getAttribute("searchAccountForm"));
            return form;
            
        }
        return new AccountSearchForm(); // セッション切れや初回アクセスにも対応
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
