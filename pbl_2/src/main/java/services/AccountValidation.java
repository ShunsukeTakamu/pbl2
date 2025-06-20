package services;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import forms.AccountEditForm;
import forms.AccountSearchForm;

public class AccountValidation {

    // 検索バリデーション（0040）
    public static Map<String, String> validate(AccountSearchForm form) {
        return validate(form.getName(), form.getEmail());
    }

    public static Map<String, String> validate(String name, String email) {
        Map<String, String> errors = new HashMap<>();
        if (name != null && name.getBytes(StandardCharsets.UTF_8).length > 20) {
            errors.put("name", "氏名が長すぎます（20バイト以内）");
        }
        if (email != null && email.getBytes(StandardCharsets.UTF_8).length > 100) {
            errors.put("email", "メールが長すぎます（100バイト以内）");
        }
        return errors;
    }

    // 編集バリデーション（0042）
    public static Map<String, String> validateForEdit(AccountEditForm form) {
        return validateForEdit(
            form.getName(),
            form.getEmail(),
            form.getPassword(),
            form.getPasswordConfirm(),
            form.getAuthorities()
        );
    }

    public static Map<String, String> validateForEdit(
        String name,
        String email,
        String password,
        String passwordConfirm,
        String[] authorities
    ) {
        Map<String, String> errors = new HashMap<>();

        if (name == null || name.isBlank()) {
            errors.put("name", "氏名を入力してください。");
        } else if (name.getBytes(StandardCharsets.UTF_8).length > 20) {
            errors.put("name", "氏名が長すぎます（20バイト以内）。");
        }

        if (email == null || email.isBlank()) {
            errors.put("email", "メールアドレスを入力してください。");
        } else if (email.getBytes(StandardCharsets.UTF_8).length > 100) {
            errors.put("email", "メールアドレスが長すぎます（100バイト以内）。");
        }

        if (password == null || password.isBlank()) {
            errors.put("password", "パスワードを入力してください。");
        } else if (password.getBytes(StandardCharsets.UTF_8).length > 30) {
            errors.put("password", "パスワードが長すぎます（30バイト以内）。");
        }

        if (passwordConfirm == null || passwordConfirm.isBlank()) {
            errors.put("passwordConfirm", "パスワード（確認）を入力してください。");
        } else if (!password.equals(passwordConfirm)) {
            errors.put("passwordConfirm", "パスワードが一致していません。");
        }

        if (authorities == null || authorities.length == 0) {
            errors.put("authorities", "権限を選択してください。");
        }

        return errors;
    }

    public static Map<String, Boolean> resolveAuthorityFlags(String[] authorities) {
        Map<String, Boolean> flags = new HashMap<>();
        flags.put("has0", false);
        flags.put("has1", false);
        flags.put("has2", false);

        if (authorities != null) {
            for (String auth : authorities) {
                flags.put("has" + auth, true);
            }
        }

        return flags;
    }
    
 // 検索結果表示用の軽量バリデーション（0041）
    public static Map<String, String> validateForResult(AccountSearchForm form) {
        return validateForResult(form.getName(), form.getEmail(), form.getAuthorities());
    }

    public static Map<String, String> validateForResult(String name, String email, String[] authorities) {
        Map<String, String> errors = new HashMap<>();

        if (name != null && name.getBytes(StandardCharsets.UTF_8).length > 20) {
            errors.put("name", "氏名が長すぎます（20バイト以内）");
        }

        if (email != null && email.getBytes(StandardCharsets.UTF_8).length > 100) {
            errors.put("email", "メールアドレスが長すぎます（100バイト以内）");
        }

        // 権限値の妥当性チェック（0, 1, 2 以外が含まれていたらエラー）
        if (authorities != null) {
            for (String auth : authorities) {
                if (!auth.matches("[0-2]")) {
                    errors.put("authorities", "不正な権限値が含まれています。");
                    break;
                }
            }
        }

        return errors;
    }

    
}
