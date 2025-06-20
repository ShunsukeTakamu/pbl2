package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CommonUtil {
	// 2015/01/15 形式のフォーマッター
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	
	// 日付を LcalDate型からString型に変換
	public static String formatLocDateToStr(LocalDate date) {
        return date.format(formatter);
    }
}
