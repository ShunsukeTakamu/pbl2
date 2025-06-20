package utils;

import java.util.Map;

import beans.Sale;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReturnPair {
	private Sale sale;
	private Map<String, String> errors;
}
