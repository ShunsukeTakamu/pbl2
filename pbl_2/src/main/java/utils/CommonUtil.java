package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

import jakarta.servlet.http.HttpServletRequest;

import beans.Account;
import services.AccountService;

public class CommonUtil {

    private static final Logger logger = Logger.getLogger(CommonUtil.class.getName());

    // 整数変換ユーティリティ S0042で使用

    public static int parseIntSafe(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public static int parseIntSafe(String str, int defaultValue) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    // ========== byte → unsigned int 変換 ==========

    public static int byteToUnsignedInt(byte b) {
        return b & 0xFF;
    }

    // ========== アカウント情報のリクエスト属性設定 ==========

    public static void setAccountAttributes(HttpServletRequest request) {
        String idStr = request.getParameter("id");
        int id = parseIntSafe(idStr, -1);

        if (id != -1) {
            AccountService service = new AccountService();
            Account account = service.selectById(id);

            if (account != null) {
                request.setAttribute("account", account);

                byte[] authority = account.getAuthority();
                if (authority != null && authority.length > 0) {
                    int authVal = byteToUnsignedInt(authority[0]);
                    request.setAttribute("authVal", authVal);
                }
            }
        } else {
            logger.warning("無効なアカウントID: " + idStr);
        }
    }

    // LocalDate → String 日付フォーマット

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public static String formatLocDateToStr(LocalDate date) {
        return date.format(formatter);
    }
}
