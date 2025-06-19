package services;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class AccountValidation {

    public static Map<String, String> validate(String name, String email) {
        Map<String, String> errors = new HashMap<>();

        if (name != null && name.getBytes(StandardCharsets.UTF_8).length >= 21) {
            errors.put("name", "氏名が長すぎます");
        }

        if (email != null && email.getBytes(StandardCharsets.UTF_8).length >= 100) {
            errors.put("email", "メールが長すぎます");
        }

        return errors;
    }
}
