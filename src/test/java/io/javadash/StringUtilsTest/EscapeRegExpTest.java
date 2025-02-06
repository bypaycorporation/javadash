package io.javadash.StringUtilsTest;

import static io.javadash.StringUtils.escapeRegExp;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EscapeRegExpTest {

    @Test
    void testEscapeRegExpWithSpecialCharacters() {
        // Test when the string contains regular expression special characters
        String input = "a.b*c+d?";
        String expected = "a\\.b\\*c\\+d\\?";
        assertEquals(expected, escapeRegExp(input), "The string should escape regular expression special characters.");
    }

    @Test
    void testEscapeRegExpWithNoSpecialCharacters() {
        // Test when the string does not contain any regular expression special characters
        String input = "HelloWorld";
        String expected = "HelloWorld";
        assertEquals(expected, escapeRegExp(input), "The string should remain unchanged if no special characters are present.");
    }

    @Test
    void testEscapeRegExpWithEmptyString() {
        // Test when the string is empty
        String input = "";
        String expected = "";
        assertEquals(expected, escapeRegExp(input), "The empty string should return an empty string.");
    }

    @Test
    void testEscapeRegExpWithNullString() {
        // Test when the string is null
        assertEquals("", escapeRegExp(null), "Null input should return an empty string.");
    }

    @Test
    void testEscapeRegExpWithWhitespace() {
        // Test when the string contains whitespace (whitespace should not be escaped)
        String input = "Hello World";
        String expected = "Hello World";
        assertEquals(expected, escapeRegExp(input), "Whitespace should remain unchanged.");
    }

    @Test
    void testEscapeRegExpWithMultipleEscapes() {
        // Test when the string contains multiple regular expression special characters
        String input = "Hello. (world)*";
        String expected = "Hello\\. \\(world\\)\\*";
        assertEquals(expected, escapeRegExp(input), "Multiple special characters should be escaped correctly.");
    }

    @Test
    void testEscapeRegExpWithLeadingAndTrailingSpaces() {
        // Test when the string contains leading and trailing spaces
        String input = " *$";
        String expected = " \\*\\$";
        assertEquals(expected, escapeRegExp(input), "Leading and trailing spaces should not affect the escaping.");
    }

}

