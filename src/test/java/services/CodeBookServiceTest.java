package services;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CodeBookServiceTest {

    @Test
    void generateKeyArray128() {
        try {
            final int noOfKeysToGen = 30;
            final String[] keyArray = CodeBookService.generateKeyArray(noOfKeysToGen, 128);
            assertEquals(noOfKeysToGen, keyArray.length);
            assertEquals(24, keyArray[0].length());
            System.out.println("Generate Key Array 128 bit: PASSED");
        } catch (Exception e) {
            System.out.println("Generate Key Array 128 bit: FAILED");
            fail("Exception: " + e);
        }
    }

    @Test
    void generateKeyArray256() {
        try {
            final int noOfKeysToGen = 30;
            final String[] keyArray = CodeBookService.generateKeyArray(noOfKeysToGen, 256);
            assertEquals(noOfKeysToGen, keyArray.length);
            assertEquals(44, keyArray[0].length());
            System.out.println("Generate Key Array 256 bit: PASSED");
        } catch (Exception e) {
            System.out.println("Generate Key Array 256 bit: FAILED");
            fail("Exception: " + e);
        }
    }

    @Test
    void daysInMonth() {
        try {
            assertEquals(31, CodeBookService.daysInMonth(1));
            assertEquals(28, CodeBookService.daysInMonth(2));
            assertEquals(31, CodeBookService.daysInMonth(3));
            assertEquals(30, CodeBookService.daysInMonth(4));
            assertEquals(31, CodeBookService.daysInMonth(5));
            assertEquals(30, CodeBookService.daysInMonth(6));
            assertEquals(31, CodeBookService.daysInMonth(7));
            assertEquals(31, CodeBookService.daysInMonth(8));
            assertEquals(30, CodeBookService.daysInMonth(9));
            assertEquals(31, CodeBookService.daysInMonth(10));
            assertEquals(30, CodeBookService.daysInMonth(11));
            assertEquals(31, CodeBookService.daysInMonth(12));
            assertEquals(29, CodeBookService.daysInMonth(13));
            System.out.println("Get Days in Month: PASSED");
        } catch (Exception e) {
            System.out.println("Get Days in Month: FAILED");
            fail("Exception: " + e);
        }
    }

    @Test
    void nameOfMonth() {
        try {
            assertEquals("January",   CodeBookService.nameOfMonth(1));
            assertEquals("February",  CodeBookService.nameOfMonth(2));
            assertEquals("March",     CodeBookService.nameOfMonth(3));
            assertEquals("April",     CodeBookService.nameOfMonth(4));
            assertEquals("May",       CodeBookService.nameOfMonth(5));
            assertEquals("June",      CodeBookService.nameOfMonth(6));
            assertEquals("July",      CodeBookService.nameOfMonth(7));
            assertEquals("August",    CodeBookService.nameOfMonth(8));
            assertEquals("September", CodeBookService.nameOfMonth(9));
            assertEquals("October",   CodeBookService.nameOfMonth(10));
            assertEquals("November",  CodeBookService.nameOfMonth(11));
            assertEquals("December",  CodeBookService.nameOfMonth(12));
            assertEquals("February",  CodeBookService.nameOfMonth(13));
            System.out.println("Get Name of Month: PASSED");
        } catch (Exception e) {
            System.out.println("Get Name of Month: FAILED");
            fail("Exception: " + e);
        }
    }
}