package forms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleSearchForm {
	private String dateStart;
	private String dateEnd;
	private int accountId;
	private int categoryId;
	private String tradeName;
	private String note;
	
	public SaleSearchForm(int accountId, int categoryId) {
		this.accountId = accountId;
		this.categoryId = categoryId;
	}
	
}
