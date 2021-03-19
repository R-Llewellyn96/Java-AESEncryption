package services;

import model.EncryptionModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncryptDecryptServiceTest {

    public EncryptionModel encryptionModel = new EncryptionModel();

    @BeforeEach
    void init() {
        try {
            final String message = "Testing message";
            encryptionModel.setEncryptionAlgorithm("AES/CBC/PKCS5Padding");
            encryptionModel.setKeyString("kFCQeqDuI/FcMCZVN7/BuF8h+GNNeLPCnvB+8HnUUNc=");
            encryptionModel.setIvString("6383f2601f9fdfb95f5be22ea67caa91");
            encryptionModel.setMessage(message);
            System.out.println("Test Model Generation: PASSED");
        } catch (Exception e) {
            System.out.println("Test Model Generation: FAILED");
            fail("Exception: " + e);
        }
    }

    @Test
    void encrypt() {
        try {
            assertEquals("wulpgL817+/oxlbaJvQpCA==" ,EncryptDecryptService.encrypt(encryptionModel));
            System.out.println("Encryption: PASSED");
        } catch (Exception e) {
            System.out.println("Encryption: FAILED");
            fail("Exception: " + e);
        }
    }

    @Test
    void decrypt() {
        try {
            encryptionModel.setMessage("wulpgL817+/oxlbaJvQpCA==");
            assertEquals("Testing message" ,EncryptDecryptService.decrypt(encryptionModel));
            System.out.println("Decryption: PASSED");
        } catch (Exception e) {
            System.out.println("Decryption: FAILED");
            fail("Exception: " + e);
        }
    }
}