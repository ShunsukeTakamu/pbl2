package beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
	private int categoryId;
	private String categoryName;
	private int activeFlg;

	// S0023ConfirmServlet用
	public Category(int categoryId, String categoryName) {
	    this.categoryId = categoryId;
	    this.categoryName = categoryName;
	// S0023ConfirmServlet用
	}
}
