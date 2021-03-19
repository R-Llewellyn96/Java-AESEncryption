package services;

import org.junit.jupiter.api.Test;
import utils.UtilHexConv;

import javax.crypto.spec.IvParameterSpec;

import static org.junit.jupiter.api.Assertions.*;

class GeneratorServiceTest {

    @Test
    void generateKey() {
        try {
            assertEquals(44, GeneratorService.generateKey("AES", 256).length());
            System.out.println("Generate Encryption Key: PASSED");
        } catch (Exception e) {
            System.out.println("Generate Encryption Key: FAILED");
            fail("Exception: " + e);
        }
    }

    @Test
    void generateSalt() {
        try {
            Object salt = GeneratorService.generateSalt();
            assertTrue(salt instanceof String);
            assertEquals(128, salt.toString().length());
            System.out.println("Generate Salt: PASSED");
        } catch (Exception e) {
            System.out.println("Generate Salt: FAILED");
            fail("Exception: " + e);
        }
    }

    @Test
    void getKeyFromPassword() {
        try {
            assertEquals(44, GeneratorService.getKeyFromPassword("My super awesome password", "12345678").length());
            System.out.println("Generate From Password: PASSED");
        } catch (Exception e) {
            System.out.println("Generate From Password: FAILED");
            fail("Exception: " + e);
        }
    }

    @Test
    void generateIV() {
        try {
            assertEquals(32, GeneratorService.generateIV().length());
            System.out.println("Generate IV: PASSED");
        } catch (Exception e) {
            System.out.println("Generate IV: FAILED");
            fail("Exception: " + e);
        }
    }

    @Test
    void generateIvParamSpec() {
        try {
            Object returnedIVParamSpec = GeneratorService.generateIvParamSpec(UtilHexConv.hexStringToByteArray("6f47558b71cb8e88ea049d05a077bb5c"));
            assertNotNull(returnedIVParamSpec);
            assertTrue(returnedIVParamSpec instanceof IvParameterSpec);
            System.out.println("Generate IV Parameter Spec: PASSED");
        } catch (Exception e) {
            System.out.println("Generate IV Parameter Spec: FAILED");
            fail("Exception: " + e);
        }
    }
}