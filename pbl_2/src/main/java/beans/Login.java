package beans;

import java.io.Serializable;

public class Login implements Serializable {

	private int accountId;
    private String name;
    private String email;
    private String password;
    private String authority;
    
     public Login(int accountId, String name, String email, String password, String authority) {
        this.accountId = accountId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.authority = authority;
    }
     public int getAccountId() {
        return accountId;
    }
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getAuthority() {
        return authority;
    }
    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
