package beans;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

	private int accountId;
	private String name;
	private String mail;
	private String password;
	private byte[] authority;

	public String getAuthorityLabel() {
	    if (authority == null || authority.length == 0)
	        return "不明";
	    int val = authority[0];
	    if (val == 0)
	        return "権限なし";

	    List<String> labels = new ArrayList<>();
	    if ((val & 1) != 0)
	        labels.add("売上登録");
	    if ((val & 2) != 0)
	        labels.add("アカウント登録");

	    return String.join("・", labels);
	}

}
