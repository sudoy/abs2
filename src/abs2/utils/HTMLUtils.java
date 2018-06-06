package abs2.utils;

public class HTMLUtils {
	public static String checkInOut(String param, String value) {
		if (param.equals("") && value.equals("1")) {
			return "checked";
		} else if (param.equals(value)) {
			return "checked";
		} else {
			return "";
		}
	}
	
	public static String checkCategory(String param, String value) {
		if (param.equals("") && value.equals("0")) {
			return "selected";
		} else if (param.equals(value)) {
			return "selected";
		}else {
			return "";
		}
	}
}
