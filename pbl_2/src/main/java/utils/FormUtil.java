package utils;

import jakarta.servlet.http.HttpServletRequest;

import forms.AccountEditForm;

public class FormUtil {

    /**
     * アカウント編集フォームの内容をリクエスト属性にセットする
     */
    public static void setAccountFormAttributes(HttpServletRequest request, AccountEditForm form, String accountId) {
        request.setAttribute("accountId", accountId);
        request.setAttribute("name", form.getName());
        request.setAttribute("email", form.getEmail());
        request.setAttribute("password", form.getPassword());
        request.setAttribute("passwordConfirm", form.getPasswordConfirm());
        request.setAttribute("authorities", form.getAuthorities());
    }
}
