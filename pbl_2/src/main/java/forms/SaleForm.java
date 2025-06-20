package forms;

import java.time.LocalDate;

import jakarta.servlet.http.HttpServletRequest;

import lombok.Data;
import utils.CommonUtil;

@Data
public class SaleForm {
	private String saleDate;
	private String accountIdStr;
	private String categoryIdStr;
	private String tradeName;
	private String unitPriceStr;
	private String saleNumberStr;
	private String note;

	public SaleForm(LocalDate saleDate, int accountId, int categoryId, int unitPrice, int saleNumber) {
		this.saleDate = CommonUtil.formatLocDateToStr(saleDate);
		this.accountIdStr = String.valueOf(accountId);
		this.categoryIdStr = String.valueOf(categoryId);
		this.unitPriceStr = String.valueOf(unitPrice);
		this.saleNumberStr = String.valueOf(saleNumber);
	}

	public SaleForm(HttpServletRequest request) {
		this.saleDate = request.getParameter("saleDate");
        this.accountIdStr = request.getParameter("accountId");
        this.categoryIdStr = request.getParameter("categoryId");
        this.tradeName = request.getParameter("tradeName");
        this.unitPriceStr = request.getParameter("unitPrice");
        this.saleNumberStr = request.getParameter("saleNumber");
        this.note = request.getParameter("note");
	}
	
}
