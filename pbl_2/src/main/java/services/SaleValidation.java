package services;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import forms.SaleSearchForm;

public class SaleValidation {

	// 0020
    public static Map<String, String> validate(SaleSearchForm form) {
    	String dateStart = form.getDateStart();
    	String dateEnd = form.getDateEnd();
    	Map<String, String> errors = new HashMap<>();

    	if (dateStart != null && !dateStart.isBlank()) {
        	try {
        		LocalDate.parse(dateStart);
        	} catch (DateTimeParseException e) {
        		errors.put("dateStart", "販売日（検索開始日）を正しく入力してください。");
        	}
        }
        
        if (dateEnd != null && !dateEnd.isBlank()) {
        	try {
        		LocalDate.parse(dateEnd);
        	} catch (DateTimeParseException e) {
        		errors.put("dateEnd", "販売日（検索終了日）を正しく入力してください。");
        	}
        }

        return errors;
    }

}
