package utils;

import jakarta.servlet.http.HttpSession;

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
}
