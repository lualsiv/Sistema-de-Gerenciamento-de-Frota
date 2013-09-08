package gerenciamentodefrota.util;

public class StringUtil {
	
	public static boolean isNullOrEmpty(String string) {
		return string == null || string.length() == 0;
	}
	
	public static boolean notNullOrEmpty(String string) {
		return !isNullOrEmpty(string);
	}
	
}
