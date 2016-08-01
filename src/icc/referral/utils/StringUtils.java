package icc.referral.utils;

public class StringUtils {

	public static String trim(String str) {

		return (str == null ? null : str.trim());
	}

	public static boolean isNotEmpty(String str) {

		return (str != null && str.trim().length() > 0);
	}

	public static boolean isEmpty(String str) {

		return (str == null || str.trim().length() == 0);
	}

	public static boolean equals(String str1, String str2) {

		return (str1 == null ? str2 == null : str1.equals(str2));
	}

	public static boolean equalsIgnoreCase(String str1, String str2) {

		return (str1 == null ? str2 == null : str1.equalsIgnoreCase(str2));
	}

	public static String defaultString(String str) {

		return defaultString(str, "");
	}

	public static String defaultString(String str, String defaultString) {

		return (str == null) ? defaultString : str;
	}
}
