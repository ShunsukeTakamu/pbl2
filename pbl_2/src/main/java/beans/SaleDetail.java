package beans;

public class SaleDetail {
    private int saleId;
    private String saleDate;
    private String accountName;
    private String categoryName;
    private String tradeName;
    private int unitPrice;
    private int saleNumber;
    private String note;

    // --- saleId ---
    public int getSaleId() {
        return saleId;
    }

    public void setSaleId(int saleId) {
        this.saleId = saleId;
    }

    // --- saleDate ---
    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    // --- accountName ---
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    // --- categoryName ---
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    // --- tradeName ---
    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    // --- unitPrice ---
    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    // --- saleNumber ---
    public int getSaleNumber() {
        return saleNumber;
    }

    public void setSaleNumber(int saleNumber) {
        this.saleNumber = saleNumber;
    }

    // --- note ---
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    // --- 小計（unitPrice * saleNumber）を計算するヘルパー ---
    public int getSubtotal() {
        return unitPrice * saleNumber;
    }
}
