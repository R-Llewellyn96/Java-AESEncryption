package org.openjfx.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    // Checks whether passed value is a valid key or not, returns true or false to caller
    public static boolean isValidKey(String passedKeyString) {

        // Regex pattern to detect invalid AES Key characters
        String regexPattern = "(s+)|(;)|(:)|(')|(\")|(,)|(<)|(\\.)|(\\[)|(\\{)|(])|(})|(-)|(_)|( )";

        // Perform regex pattern matching against given string
        Pattern pattern = Pattern.compile(regexPattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(passedKeyString);
        boolean matchFound = matcher.find();

        // Return the opposite of found matches, i.e. if found return false, if not found return true
        if (matchFound == true) {
            return false;
        } else {
            return true;
        }
    }

}
