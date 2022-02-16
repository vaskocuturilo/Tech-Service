package site.testengineer.techInterview.utils;

import java.util.regex.Pattern;

public class EmailValidation {

    public static boolean isEmailValid(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }
}
