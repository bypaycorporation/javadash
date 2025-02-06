package io.javadash.StringUtilsTest;

import static io.javadash.StringUtils.toUpper;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ToUpperTest {

    @Test
    void testToUpper_withValidString() {
        // Test for a valid string
        String input = "hello world";
        String expected = "HELLO WORLD";
        assertEquals(expected, toUpper(input));
    }

    @Test
    void testToUpper_withMixedCaseString() {
        // Test for a string with mixed uppercase and lowercase letters
        String input = "HeLLo WoRLd";
        String expected = "HELLO WORLD";
        assertEquals(expected, toUpper(input));
    }

    @Test
    void testToUpper_withEmptyString() {
        // Test for an empty string
        String input = "";
        String expected = "";
        assertEquals(expected, toUpper(input));
    }

    @Test
    void testToUpper_withNullString() {
        // Test for a null string (this should depend on your isValidString logic)
        String input = null;
        String expected = "";
        assertEquals(expected, toUpper(input));
    }

    @Test
    void testToUpper_withStringContainingSpecialCharacters() {
        // Test for a string containing special characters
        String input = "hello! world#123";
        String expected = "HELLO! WORLD#123";
        assertEquals(expected, toUpper(input));
    }

    @Test
    void testToUpper_withStringAlreadyInUpperCase() {
        // Test for a string that is already in uppercase
        String input = "HELLO WORLD";
        String expected = "HELLO WORLD";
        assertEquals(expected, toUpper(input));
    }

    @Test
    void testToUpper_withWhitespace() {
        // Test for a string with leading and trailing whitespace
        String input = "  hello world  ";
        String expected = "  HELLO WORLD  ";
        assertEquals(expected, toUpper(input));
    }
}

