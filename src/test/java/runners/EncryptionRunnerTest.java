package runners;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncryptionRunnerTest {

    @Test
    void encryptionRunner128() {
        try {
            final String message = "Testing message";
            final int bitLengthChoice = 0;
            EncryptionRunner.encryptionRunner(message, bitLengthChoice);
            System.out.println("Encryption Runner: PASSED");
        } catch (Exception e) {
            System.out.println("Encryption Runner: FAILED");
            fail("Exception: " + e);
        }
    }

    @Test
    void encryptionRunner256() {
        try {
            final String message = "Testing message";
            final int bitLengthChoice = 1;
            EncryptionRunner.encryptionRunner(message, bitLengthChoice);
            System.out.println("Encryption Runner: PASSED");
        } catch (Exception e) {
            System.out.println("Encryption Runner: FAILED");
            fail("Exception: " + e);
        }
    }

    @Test
    void encryptionRunnerWKey128() {
        try {
            final String message = "Testing message";
            final String key = "PqQIlLuOJjsmql3GT5cCUg==";
            assertEquals(56, EncryptionRunner.encryptionRunnerWKey(message, key).length());
            System.out.println("Encryption RunnerWKey128: PASSED");
        } catch (Exception e) {
            System.out.println("Encryption RunnerWKey128: FAILED");
            fail("Exception: " + e);
        }
    }

    @Test
    void encryptionRunnerWKey256() {
        try {
            final String message = "Testing message";
            final String key = "5NkLjkNKcrDYtfRthtnlWCywDW9IH4Tf6pF49YyDdVk=";
            assertEquals(56, EncryptionRunner.encryptionRunnerWKey(message, key).length());
            System.out.println("Encryption RunnerWKey256: PASSED");
        } catch (Exception e) {
            System.out.println("Encryption RunnerWKey256: FAILED");
            fail("Exception: " + e);
        }
    }

    @Test
    void decryptionRunner128() {
        try {
            final String key = "PqQIlLuOJjsmql3GT5cCUg==";
            final String iv = "236e68fe86fb8e87152a7f7eee614a92";
            final String ciphertext = "FmaDCJ3R+NZWfbDBe7qXoQ==";
            EncryptionRunner.decryptionRunner(iv + ciphertext, key);
            System.out.println("Decryption Runner 128 bit: PASSED");
        } catch (Exception e) {
            System.out.println("Decryption Runner 128 bit: FAILED");
            fail("Exception: " + e);
        }
    }

    @Test
    void decryptionRunner256() {
        try {
            final String key = "5NkLjkNKcrDYtfRthtnlWCywDW9IH4Tf6pF49YyDdVk=";
            final String iv = "b6f15ebfae9a32d7790e437013f1604a";
            final String ciphertext = "u61HDcFAxROmnLWn1ZygXg==";
            EncryptionRunner.decryptionRunner(iv + ciphertext, key);
            System.out.println("Decryption Runner 256 bit: PASSED");
        } catch (Exception e) {
            System.out.println("Decryption Runner 256 bit: FAILED");
            fail("Exception: " + e);
        }
    }

    @Test
    void decryptionRunnerWGUI128() {
        try {
            final String key = "PqQIlLuOJjsmql3GT5cCUg==";
            final String iv = "236e68fe86fb8e87152a7f7eee614a92";
            final String ciphertext = "FmaDCJ3R+NZWfbDBe7qXoQ==";
            assertEquals("Testing message", EncryptionRunner.decryptionRunnerWGUI(iv + ciphertext, key));
            System.out.println("Decryption RunnerWGUI 256 bit: PASSED");
        } catch (Exception e) {
            System.out.println("Decryption RunnerWGUI 256 bit: FAILED");
            fail("Exception: " + e);
        }
    }

    @Test
    void decryptionRunnerWGUI256() {
        try {
            final String key = "5NkLjkNKcrDYtfRthtnlWCywDW9IH4Tf6pF49YyDdVk=";
            final String iv = "b6f15ebfae9a32d7790e437013f1604a";
            final String ciphertext = "u61HDcFAxROmnLWn1ZygXg==";
            assertEquals("Testing message", EncryptionRunner.decryptionRunnerWGUI(iv + ciphertext, key));
            System.out.println("Decryption RunnerWGUI 256 bit: PASSED");
        } catch (Exception e) {
            System.out.println("Decryption RunnerWGUI 256 bit: FAILED");
            fail("Exception: " + e);
        }
    }
}