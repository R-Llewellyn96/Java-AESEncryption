package utils;

/*
 * Project Java-AESEncryption
 * File: UtilConverters.java
 * @author Ryan Llewellyn
 * Date: 09/01/2021
 * Purpose: Handles value conversions.
 */

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class UtilConverters {

    // Take string and convert to secret key object
    public static SecretKey stringToSecretKey(String keyString) {

        // Decode the base 64 encoded key string and
        // Return secret key string as a secret key object to method caller
        return new SecretKeySpec((Base64.getDecoder().decode(keyString)), 0, (Base64.getDecoder().decode(keyString)).length, "AES");
    }

    // Take secret key object and convert to string
    public static String secretKeyToString(SecretKey key) {

        // Return secret key as a string to method caller
        return Base64.getEncoder().encodeToString(key.getEncoded());
    }

    // Append IV to Ciphertext for sending
    public static String addIVToCiphertext(String iv, String ciphertext) {

        // Return iv and ciphertext appended
        return iv + ciphertext;
    }

    // Strip IV from read ciphertext, NOTE Array[0] = IV and Array[1] = Ciphertext!
    public static String[] stripIVFromCiphertext(String combinedIvAndCiphertext) {

        // Define character length of IV and get length of combined IV and Ciphertext input
        int ivLength = 32;
        int stringLength = combinedIvAndCiphertext.length();

        // Split string into IV and Ciphertext, NOTE String starts at zero
        // Return both as Strings in array including separated IV and Ciphertext
        return new String[]{(combinedIvAndCiphertext.substring(0, ivLength)), (combinedIvAndCiphertext.substring(ivLength, stringLength))};
    }
}
