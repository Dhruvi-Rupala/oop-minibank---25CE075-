package util;

import java.util.regex.Pattern;

public class Validator {
    private static final Pattern MOBILE =
            Pattern.compile("^[6-9][0-9]{9}$");

    public static boolean isValidMobile(String mobile) {
        return mobile != null && MOBILE.matcher(mobile).matches();
    }
}