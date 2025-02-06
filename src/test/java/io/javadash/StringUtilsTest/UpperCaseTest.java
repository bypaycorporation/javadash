package io.javadash.StringUtilsTest;

import static io.javadash.StringUtils.upperCase;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UpperCaseTest {

    @Test
    void testUpperCase_withValidString() {
        // Test for converting space-separated words to upper case
        String input = "hello world java";
        String expected = "HELLO WORLD JAVA";
        assertEquals(expected, upperCase(input));
    }

    @Test
    void testUpperCase_withSingleWord() {
        // Test for a string with only one word
        String input = "hello";
        String expected = "HELLO";
        assertEquals(expected, upperCase(input));
    }

    @Test
    void testUpperCase_withEmptyString() {
        // Test for an empty string (should return an empty string)
        String input = "";
        String expected = "";
        assertEquals(expected, upperCase(input));
    }

    @Test
    void testUpperCase_withNullString() {
        String input = null;
        String expected = "";
        assertEquals(expected, upperCase(input));
    }

    @Test
    void testUpperCase_withStringContainingNumbers() {
        // Test for a string containing numbers (should convert words and numbers to upper case)
        String input = "hello world 123";
        String expected = "HELLO WORLD 123";
        assertEquals(expected, upperCase(input));
    }

    @Test
    void testUpperCase_withMultipleSpaces() {
        // Test for a string with multiple spaces between words
        String input = "hello   world   java";
        String expected = "HELLO WORLD JAVA";
        assertEquals(expected, upperCase(input));
    }

    @Test
    void testUpperCase_withLeadingAndTrailingSpaces() {
        // Test for a string with leading and trailing spaces
        String input = "   hello world java   ";
        String expected = "HELLO WORLD JAVA";
        assertEquals(expected, upperCase(input));
    }

    @Test
    void testUpperCase_withMixedCaseWords() {
        // Test for a string with mixed case words
        String input = "HeLLo WoRLd JaVa";
        String expected = "HELLO WORLD JAVA";
        assertEquals(expected, upperCase(input));
    }

    @Test
    void testUpperCase_withStringContainingSpecialCharacters() {
        // Test for a string containing special characters (they should be ignored)
        String input = "hello world! @java";
        String expected = "HELLO WORLD JAVA";
        assertEquals(expected, upperCase(input));
    }

    @Test
    void testUpperCase_withOnlySpecialCharacters() {
        // Test for a string with only special characters (should return an empty string)
        String input = "!@#$$%^";
        String expected = "";
        assertEquals(expected, upperCase(input));
    }
}

