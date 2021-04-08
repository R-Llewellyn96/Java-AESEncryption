package org.openjfx.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    // Checks whether passed value is a valid key or not, returns true or false to caller
    public static boolean isValidKey(String passedKeyString) {

        // Regex pattern to detect invalid AES Key characters
        String regexPattern = "(;)|(:)|(')|(\")|(,)|(<)|(\\.)|(\\[)|(\\{)|(])|(})|(-)|(_)|( )";

        // Perform regex pattern matching against given string
        Pattern pattern = Pattern.compile(regexPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(passedKeyString);

        // Return the opposite of found matches, i.e. if found return false, if not found return true
        return matcher.find();
    }

}
