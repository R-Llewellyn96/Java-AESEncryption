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

        // Switch statement switching between selected months
        return switch (monthNo) {
            case 2 -> 28; // February assumed as non-Leap Year
            case 4, 6, 9, 11 -> 30;
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 13 -> 29; // 13 is added as February on Leap Year
            default -> throw new IllegalStateException("Unexpected Value:" + monthNo);
        };
    }

    // Returns the name of a user selected month
    public static String nameOfMonth (int monthNo) {

        // Check whether user has input february leap year,
        // if so convert value to just 2 (february)
        if (monthNo == 13) { monthNo = 2; }

        // Switch statement, returns string of month name selected
        return switch (monthNo) {
            case 1 -> "January";
            case 2 -> "February";
            case 3 -> "March";
            case 4 -> "April";
            case 5 -> "May";
            case 6 -> "June";
            case 7 -> "July";
            case 8 -> "August";
            case 9 -> "September";
            case 10 -> "October";
            case 11 -> "November";
            case 12 -> "December";
            default -> throw new IllegalStateException("Unexpected value: " + monthNo);
        };
    }
}
