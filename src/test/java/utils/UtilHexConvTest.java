package utils;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

class UtilHexConvTest {

    @Test
    void testToHex() {
        try {
            final byte[] testByteArray = new byte[]{68, 110, 52, -69, 45, 51, 47, 45, -123, 22, 61, 55, 0, 15, -72, -93};
            final String ivString = "446e34bb2d332f2d85163d37000fb8a3";
            assertEquals(ivString, UtilHexConv.toHex(testByteArray));
            System.out.println("Byte Array to Hex String: PASSED");
        } catch (Exception e) {
            System.out.println("Byte Array to Hex String: FAILED");
            fail("Exception: " + e);
        }
    }

    @Test
    void toByteArray() {
        try {
            final String hexString = "SOKd/1cZIdgSotC62frG1fw8+zG7HrNYFCERcfINLxk=";
            assertArrayEquals(hexString.getBytes(StandardCharsets.UTF_8), UtilHexConv.toByteArray(hexString));
            System.out.println("String to Byte Array: PASSED");
        } catch (Exception e) {
            System.out.println("String to Byte Array: FAILED");
            fail("Exception: " + e);
        }
    }

    @Test
    void hexStringToByteArray() {
        try {
            final String ivString = "446e34bb2d332f2d85163d37000fb8a3";
            final byte[] testByteArray = new byte[]{68, 110, 52, -69, 45, 51, 47, 45, -123, 22, 61, 55, 0, 15, -72, -93};
            assertArrayEquals(testByteArray, UtilHexConv.hexStringToByteArray(ivString));
            System.out.println("Hex String to Byte Array: PASSED");
        } catch (Exception e) {
            System.out.println("Hex String to Byte Array: FAILED");
            fail("Exception: " + e);
        }
    }
}