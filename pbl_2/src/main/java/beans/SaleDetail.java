package beans;

public class SaleDetail {
    private int saleId;
    private String saleDate;
    private int accountId;        // ← 追加
    private String accountName;
    private int categoryId;       // ← 追加
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

    // --- accountId ---（追加）
    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    // --- accountName ---
    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    // --- categoryId ---（追加）
    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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

    // --- subtotal（単価 × 個数）---
    public int getSubtotal() {
        return unitPrice * saleNumber;
    }
}
