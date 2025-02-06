package io.javadash.StringUtilsTest;

import static io.javadash.StringUtils.trimStart;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TrimStartTest {

    @Test
    void testTrimStart_withValidStringAndChars() {
        // Test for trimming the specified characters from the start of the string
        String input = "###hello world";
        String chars = "#";
        String expected = "hello world";
        assertEquals(expected, trimStart(input, chars));
    }

    @Test
    void testTrimStart_withValidStringAndNoChars() {
        // Test for trimming whitespace from the start when no specific characters are provided
        String input = "   hello world";
        String chars = "";
        String expected = "hello world";
        assertEquals(expected, trimStart(input, chars));
    }

    @Test
    void testTrimStart_withNullString() {
        // Test for null string (should return an empty string)
        String input = null;
        String chars = "#";
        String expected = "";
        assertEquals(expected, trimStart(input, chars));
    }

    @Test
    void testTrimStart_withValidStringAndInvalidChars() {
        // Test for trimming a string where the `chars` argument is not valid
        String input = "  hello world";
        String chars = "123";
        String expected = "  hello world";
        assertEquals(expected, trimStart(input, chars));
    }

    @Test
    void testTrimStart_withStringContainingTrimCharactersAtStart() {
        // Test for trimming characters from the start of the string
        String input = "***hello world";
        String chars = "*";
        String expected = "hello world";
        assertEquals(expected, trimStart(input, chars));
    }

    @Test
    void testTrimStart_withStringContainingWhitespaceAtStart() {
        // Test for trimming whitespace characters from the start
        String input = "   hello world";
        String chars = " ";
        String expected = "hello world";
        assertEquals(expected, trimStart(input, chars));
    }

    @Test
    void testTrimStart_withNoTrimCharactersAtStart() {
        // Test for a string that does not start with trim characters
        String input = "hello world";
        String chars = "#";
        String expected = "hello world";
        assertEquals(expected, trimStart(input, chars));
    }

    @Test
    void testTrimStart_withOnlyTrimCharactersAtStart() {
        // Test for a string that only contains trim characters at the start (should return the rest of the string)
        String input = "####hello world";
        String chars = "#";
        String expected = "hello world";
        assertEquals(expected, trimStart(input, chars));
    }

    @Test
    void testTrimStart_withCharsNotAtStart() {
        // Test for a string where trim characters are not at the start (no change should occur)
        String input = "hello world###";
        String chars = "#";
        String expected = "hello world###";
        assertEquals(expected, trimStart(input, chars));
    }

    @Test
    void testTrimStart_withAllCharsTrimmed() {
        // Test for a string that only contains the trim characters at the start (should return an empty string)
        String input = "###";
        String chars = "#";
        String expected = "";
        assertEquals(expected, trimStart(input, chars));
    }

    @Test
    void testTrimStart_withEmptyChars() {
        // Test for an empty `chars` argument (should behave like regular trimStart for whitespace)
        String input = "   hello world";
        String chars = "";
        String expected = "hello world";
        assertEquals(expected, trimStart(input, chars));
    }

}

