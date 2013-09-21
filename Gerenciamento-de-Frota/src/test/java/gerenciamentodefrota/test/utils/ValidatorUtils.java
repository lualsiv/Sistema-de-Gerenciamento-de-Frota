package gerenciamentodefrota.test.utils;

import br.com.caelum.vraptor.Validator;
import br.com.caelum.vraptor.validator.Message;

public class ValidatorUtils {

	public static boolean containsCategory(Validator validator, String category) {
		for (Message message : validator.getErrors()) {
			if (category.equals(message.getCategory()))
				return true;
		}
		return false;
	}
	
}
