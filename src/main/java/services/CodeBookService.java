package services;

import java.security.NoSuchAlgorithmException;

public class CodeBookService {

    // Function generates an array of keys equal to the length of chosen month
    public static String[] generateKeyArray(int noKeysToGen, int keySize) throws NoSuchAlgorithmException {

        String algorithmInstance = "AES";

        // Declare and initialise string array of secret keys
        String[] keyArr = new String[noKeysToGen];

        // Generate a key for the number of days in month
        for (int i = 0; i < noKeysToGen; i++) {

            // generate a key, convert it to a string and store it in the key array as a string
            keyArr[i] = GeneratorService.generateKey(algorithmInstance, keySize);
        }

        // Return the string array of generated keys to caller
        return keyArr;
    }

    // Returns the number of days in a month, selected by user
    public static Integer daysInMonth (int monthNo) {

        // If statement switching between selected months (replaced switch statement for Java 11 compatibility)
        if (monthNo == 2) {
            return 28; // February assumed as non-Leap Year
        } else if (monthNo == 4 || monthNo == 6 || monthNo == 9 || monthNo == 11 ) {
            return 30;
        } else if (monthNo == 1 || monthNo == 3|| monthNo == 5 || monthNo == 7 || monthNo == 8 || monthNo == 10 || monthNo == 12) {
            return 31;
        } else if (monthNo == 13) {
            return 29; // February assumed as Leap Year
        } else {
            // If outside of range
            throw new IllegalStateException("Unexpected Value:" + monthNo);
        }
    }

    // Returns the name of a user selected month
    public static String nameOfMonth (int monthNo) {

        // Check whether user has input february leap year,
        // if so convert value to just 2 (february)
        if (monthNo == 13) { monthNo = 2; }

        // If statement switching between selected months (replaced switch statement for Java 11 compatibility)
        if (monthNo == 1) { return "January"; }
        else if (monthNo == 2) { return "February"; }
        else if (monthNo == 3) { return "March"; }
        else if (monthNo == 4) { return "April"; }
        else if (monthNo == 5) { return "May"; }
        else if (monthNo == 6) { return "June"; }
        else if (monthNo == 7) { return "July"; }
        else if (monthNo == 8) { return "August"; }
        else if (monthNo == 9) { return "September"; }
        else if (monthNo == 10) { return "October"; }
        else if (monthNo == 11) { return "November"; }
        else if (monthNo == 12) { return "December";
        } else {
            // If outside of range
            throw new IllegalStateException("Unexpected Value:" + monthNo);
        }
    }
}
