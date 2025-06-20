package utils;

public class IdUtil {

    /**
     * String を安全に int に変換する。変換失敗時は -1 を返す。
     * @param str 変換対象の文字列
     * @return 成功時はint値、失敗時は-1
     */
    public static int parseIntSafe(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     * String を安全に int に変換する。変換失敗時に指定されたデフォルト値を返す。
     * @param str 変換対象の文字列
     * @param defaultValue 変換失敗時に返す値
     * @return 成功時はint値、失敗時はdefaultValue
     */
    public static int parseIntSafe(String str, int defaultValue) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }
}
