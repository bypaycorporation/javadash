package io.javadash.StringUtilsTest;

import static io.javadash.StringUtils.trim;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class TrimTest {

    @Test
    void testTrim_withValidStringAndChars() {
        // Test for trimming a string with specific characters at both ends
        String input = "##hello world##";
        String chars = "#";
        String expected = "hello world";
        assertEquals(expected, trim(input, chars));
    }

    @Test
    void testTrim_withValidStringAndNoChars() {
        // Test for trimming a string with no specific characters (should not change)
        String input = "hello world";
        String chars = "";
        String expected = "hello world";
        assertEquals(expected, trim(input, chars));
    }

    @Test
    void testTrim_withNullString() {
        // Test for null string (should return empty string)
        String input = null;
        String chars = "#";
        String expected = "";
        assertEquals(expected, trim(input, chars));
    }

    @Test
    void testTrim_withValidStringAndInvalidChars() {
        // Test for trimming a string where the `chars` argument is not valid
        String input = "  hello world  ";
        String chars = "123";
        String expected = "  hello world  ";
        assertEquals(expected, trim(input, chars));
    }

    @Test
    void testTrim_withStringContainingTrimCharacters() {
        // Test for a string that contains trim characters at the beginning and end, but no middle
        String input = "***hello world***";
        String chars = "*";
        String expected = "hello world";
        assertEquals(expected, trim(input, chars));
    }

    @Test
    void testTrim_withStringContainingWhitespace() {
        // Test for a string that contains leading and trailing whitespace and trimming whitespace
        String input = "   hello world   ";
        String chars = " ";
        String expected = "hello world";
        assertEquals(expected, trim(input, chars));
    }

    @Test
    void testTrim_withNoTrimCharacters() {
        // Test for a string that has no trim characters (should not change)
        String input = "hello world";
        String chars = "#";
        String expected = "hello world";
        assertEquals(expected, trim(input, chars));
    }

    @Test
    void testTrim_withOnlyTrimCharacters() {
        // Test for a string that only contains the trim characters (should return empty string)
        String input = "####";
        String chars = "#";
        String expected = "";
        assertEquals(expected, trim(input, chars));
    }

    @Test
    void testTrim_withCharsAtBothEnds() {
        // Test for a string with trim characters at both ends
        String input = "abcde";
        String chars = "ab";
        String expected = "cde";
        assertEquals(expected, trim(input, chars));
    }
}

