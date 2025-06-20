package services;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import beans.Sale;
import forms.SaleForm;
import forms.SaleSearchForm;
import utils.ReturnPair;

public class SaleValidation {

	// 0010
    public static ReturnPair validate(SaleForm form) throws IOException {
    	String saleIdStr = form.getSaleIdStr();
    	String saleDateStr = form.getSaleDate();
        String accountIdStr = form.getAccountIdStr();
        String categoryIdStr = form.getCategoryIdStr();
        String tradeName = form.getTradeName();
        String unitPriceStr = form.getUnitPriceStr();
        String saleNumberStr = form.getSaleNumberStr();
        String note = form.getNote();

    	Map<String, String> errors = new HashMap<>();
        int accountId = 0, categoryId = 0, unitPrice = -1, saleNumber = -1, saleId = 0;
        LocalDate saleDate = null;
        
        // 販売日チェック
        if (saleDateStr == null || saleDateStr.isBlank()) {
            errors.put("saleDate", "販売日を入力してください。");
        } else {
            try {
                saleDate = LocalDate.parse(saleDateStr);
            } catch (DateTimeParseException e) {
                errors.put("saleDate", "販売日を正しく入力してください。");
            }
        }

        // 担当チェック
        try {
            accountId = Integer.parseInt(accountIdStr);
            if (accountId == 0) {
                errors.put("accountId", "担当が未選択です。");
            } else if (!(new AccountService()).existsById(accountId)) {
                errors.put("accountId", "アカウントテーブルに存在しません。");
            }
        } catch (NumberFormatException e) {
            errors.put("accountId", "担当が未選択です。");
        }

        // 商品カテゴリー
        try {
            categoryId = Integer.parseInt(categoryIdStr);
            if (categoryId == 0) {
                errors.put("categoryId", "商品カテゴリーが未選択です。");
            } else if (!(new CategoryService()).existsById(categoryId)) {
                errors.put("categoryId", "商品カテゴリーテーブルに存在しません。");
            }
        } catch (NumberFormatException e) {
            errors.put("categoryId", "商品カテゴリーが未選択です。");
        }

        // 商品名チェック
        if (tradeName == null || tradeName.isBlank()) {
            errors.put("tradeName", "商品名を入力してください。");
        } else if (tradeName.getBytes("UTF-8").length >= 101) {
            errors.put("tradeName", "商品名が長すぎます。");
        }

        // 単価チェック
        if (unitPriceStr == null || unitPriceStr.trim().isEmpty()) {
            errors.put("unitPrice", "単価を入力してください。");
        } else if (unitPriceStr.getBytes("UTF-8").length >= 10) {
            errors.put("unitPrice", "単価が長すぎます。");
        } else {
	        try {
	        	unitPrice = Integer.parseInt(unitPriceStr);
	        	if (unitPrice <= 0) errors.put("unitPrice", "単価を入力してください。");
	        } catch (NumberFormatException e) {
	        	errors.put("unitPrice", "単価を正しく入力してください。");
	        }
        }
        

        // 個数チェック
        if (saleNumberStr == null || saleNumberStr.trim().isEmpty()) {
            errors.put("saleNumber", "個数を入力してください。");
        } else if (saleNumberStr.getBytes("UTF-8").length >= 10) {
            errors.put("saleNumber", "個数が長すぎます。");
        } else {
	        try {
	            saleNumber = Integer.parseInt(saleNumberStr);
	            if (saleNumber <= 0) errors.put("saleNumber", "個数を入力してください。");
	        } catch (NumberFormatException e) {
	            errors.put("saleNumber", "個数を正しく入力してください。");
	        }
        }

        // 空文字ならnoteをnullにする
        if (note.isEmpty()) {
            note = null;
        }
        // 備考チェック
        if (note != null && note.getBytes("UTF-8").length >= 401) {
            errors.put("note", "備考が長すぎます。");
        }
        
        // saleIdチェック
        if (saleIdStr != null && !saleIdStr.isBlank()) {
        	try {
	            saleId = Integer.parseInt(saleIdStr);
	        } catch (NumberFormatException e) {
	        	saleId = 0;
	        }
        }

        Sale sale = new Sale(
                saleId,
                saleDate,
                accountId,
                categoryId,
                tradeName,
                unitPrice,
                saleNumber,
                note);

        ReturnPair returnPair = new ReturnPair(sale, errors);

        return returnPair;
    }
    
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
