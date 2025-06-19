package utils;

import jakarta.servlet.http.HttpSession;

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
}
