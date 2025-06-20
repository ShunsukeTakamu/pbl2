package beans;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Sale {
	private int saleId;
	private LocalDate saleDate;
	private int accountId;
	private int categoryId;
	private String tradeName;
	private int unitPrice;
	private int saleNumber;
	private String note;

	public Sale(LocalDate saleDate, int accountId, int categoryId, int unitPrice, int saleNumber) {
		this.saleDate = saleDate;
		this.accountId = accountId;
		this.categoryId = categoryId;
		this.unitPrice = unitPrice;
		this.saleNumber = saleNumber;
	}

	public Sale(int accountId, int categoryId, String tradeName, String note) {
		this.accountId = accountId;
		this.categoryId = categoryId;
		this.tradeName = tradeName;
		this.note = note;
	}

	public Sale(int accountId, int categoryId) {
		this.accountId = accountId;
		this.categoryId = categoryId;
	}

}
