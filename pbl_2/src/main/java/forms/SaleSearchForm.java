package forms;

import jakarta.servlet.http.HttpServletRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleSearchForm {
	private String dateStart;
	private String dateEnd;
	private String accountIdStr;
	private String categoryIdStr;
	private String tradeName;
	private String note;
	
	public SaleSearchForm(int accountId, int categoryId) {
		this.accountIdStr = String.valueOf(accountId);
		this.categoryIdStr = String.valueOf(categoryId);
	}

	public SaleSearchForm(HttpServletRequest request) {
		this.dateStart = request.getParameter("dateStart");
		this.dateEnd = request.getParameter("dateEnd");
		this.accountIdStr = request.getParameter("accountId");
		this.categoryIdStr = request.getParameter("categoryId");
		this.tradeName = request.getParameter("tradeName");
		this.note = request.getParameter("note");
	}
	
}
