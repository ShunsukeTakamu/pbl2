package beans;

import java.io.Serializable;

public class AccountRegistration implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String name;
    private String mail;     
    private String password;
    private String authority; 
    
    public AccountRegistration() {
    }
     public AccountRegistration(String name, String mail, String password, String authority) {
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.authority = authority;
    }
     
     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public String getEmail() {
         return mail;
     }

     public void setEmail(String mail) {
         this.mail = mail;
     }
      public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return authority;
    }

    public void setRole(String authority) {
        this.authority = authority;
    }
}