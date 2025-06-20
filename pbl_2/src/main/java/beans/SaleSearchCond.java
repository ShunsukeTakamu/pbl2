package beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleSearchCond {
	private String dateStart;
	private String dateEnd;
	private int accountId;
	private int categoryId;
	private String tradeName;
	private String note;
	
	public SaleSearchCond(int accountId, int categoryId) {
		this.accountId = accountId;
		this.categoryId = categoryId;
	}
	
}
