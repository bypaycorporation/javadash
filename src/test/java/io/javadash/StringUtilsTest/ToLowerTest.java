package io.javadash.StringUtilsTest;

import static io.javadash.StringUtils.toLower;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ToLowerTest {

    @Test
    void testToLower_withValidString() {
        // Test for a valid string
        String input = "Hello World";
        String expected = "hello world";
        assertEquals(expected, toLower(input));
    }

    @Test
    void testToLower_withMixedCaseString() {
        // Test for a string with mixed uppercase and lowercase letters
        String input = "HeLLo WoRLd";
        String expected = "hello world";
        assertEquals(expected, toLower(input));
    }

    @Test
    void testToLower_withEmptyString() {
        // Test for an empty string
        String input = "";
        String expected = "";
        assertEquals(expected, toLower(input));
    }

    @Test
    void testToLower_withNullString() {
        // Test for a null string (this should depend on your isValidString logic)
        String input = null;
        String expected = "";
        assertEquals(expected, toLower(input));
    }

    @Test
    void testToLower_withStringContainingSpecialCharacters() {
        // Test for a string containing special characters
        String input = "Hello! World#123";
        String expected = "hello! world#123";
        assertEquals(expected, toLower(input));
    }

    @Test
    void testToLower_withStringAlreadyInLowerCase() {
        // Test for a string that is already in lowercase
        String input = "hello world";
        String expected = "hello world";
        assertEquals(expected, toLower(input));
    }

    @Test
    void testToLower_withWhitespace() {
        // Test for a string with leading and trailing whitespace
        String input = "  Hello World  ";
        String expected = "  hello world  ";
        assertEquals(expected, toLower(input));
    }
}

