package utils;

import org.junit.jupiter.api.Test;

import javax.crypto.SecretKey;

import static org.junit.jupiter.api.Assertions.*;

class UtilConvertersTest {

    @Test
    void stringToSecretKey() {
        try {
            String secretKeyString = "SOKd/1cZIdgSotC62frG1fw8+zG7HrNYFCERcfINLxk=";
            Object returnedKey = UtilConverters.stringToSecretKey(secretKeyString);
            assertNotNull(returnedKey);
            assertTrue(returnedKey instanceof SecretKey);
            System.out.println("String to secret key: PASSED");
        } catch (Exception e) {
            System.out.println("String to secret key: FAILED");
            fail("Exception: " + e);
        }
    }

    @Test
    void secretKeyToString() {
        try {
            String secretKeyString = "SOKd/1cZIdgSotC62frG1fw8+zG7HrNYFCERcfINLxk=";
            SecretKey returnedKey = UtilConverters.stringToSecretKey(secretKeyString);
            assertEquals(secretKeyString, UtilConverters.secretKeyToString(returnedKey));
            System.out.println("Secret key to string: PASSED");
        } catch (Exception e) {
            System.out.println("Secret key to string: FAILED");
            fail("Exception: " + e);
        }
    }

    @Test
    void addIVToCiphertext() {
        try {
            String iv = "446e34bb2d332f2d85163d37000fb8a3";
            String ciphertext = "OSDV+yqT1iemNMUR/v0twZJyKprJtcTV5A2W2t8bZiE=";
            assertEquals((iv + ciphertext), UtilConverters.addIVToCiphertext(iv, ciphertext));
            System.out.println("Add iv to ciphertext: PASSED");
        } catch (Exception e) {
            System.out.println("Add iv to ciphertext: FAILED");
            fail("Exception: " + e);
        }
    }

    @Test
    void stripIVFromCiphertext() {
        try {
            String iv = "446e34bb2d332f2d85163d37000fb8a3";
            String ciphertext = "OSDV+yqT1iemNMUR/v0twZJyKprJtcTV5A2W2t8bZiE=";

            String[] ivAndCiphertext = UtilConverters.stripIVFromCiphertext((iv + ciphertext));
            assertEquals(iv, ivAndCiphertext[0]);
            assertEquals(ciphertext, ivAndCiphertext[1]);
            System.out.println("Strip IV from Ciphertext: PASSED");
        } catch (Exception e) {
            System.out.println("Strip IV from Ciphertext: FAILED");
            fail("Exception: " + e);
        }
    }
}