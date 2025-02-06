package io.javadash.StringUtilsTest;

import static io.javadash.StringUtils.trimEnd;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TrimEndTest {

    @Test
    void testTrimEnd_withValidStringAndChars() {
        // Test for trimming the specified characters from the end of the string
        String input = "hello world###";
        String chars = "#";
        String expected = "hello world";
        assertEquals(expected, trimEnd(input, chars));
    }

    @Test
    void testTrimEnd_withValidStringAndNoChars() {
        // Test for trimming whitespace from the end when no specific characters are provided
        String input = "hello world   ";
        String chars = "";
        String expected = "hello world";
        assertEquals(expected, trimEnd(input, chars));
    }

    @Test
    void testTrimEnd_withNullString() {
        // Test for null string (should return an empty string)
        String input = null;
        String chars = "#";
        String expected = "";
        assertEquals(expected, trimEnd(input, chars));
    }

    @Test
    void testTrimEnd_withValidStringAndInvalidChars() {
        // Test for trimming a string where the `chars` argument is not valid
        String input = "hello world 123";
        String chars = "xyz";
        String expected = "hello world 123";
        assertEquals(expected, trimEnd(input, chars));
    }

    @Test
    void testTrimEnd_withStringContainingTrimCharactersAtEnd() {
        // Test for trimming characters from the end of the string
        String input = "hello world###";
        String chars = "#";
        String expected = "hello world";
        assertEquals(expected, trimEnd(input, chars));
    }

    @Test
    void testTrimEnd_withStringContainingWhitespaceAtEnd() {
        // Test for trimming whitespace characters from the end
        String input = "hello world   ";
        String chars = " ";
        String expected = "hello world";
        assertEquals(expected, trimEnd(input, chars));
    }

    @Test
    void testTrimEnd_withNoTrimCharactersAtEnd() {
        // Test for a string that does not end with trim characters
        String input = "hello world";
        String chars = "#";
        String expected = "hello world";
        assertEquals(expected, trimEnd(input, chars));
    }

    @Test
    void testTrimEnd_withOnlyTrimCharactersAtEnd() {
        // Test for a string that only contains the trim characters at the end (should return the rest of the string)
        String input = "hello world####";
        String chars = "#";
        String expected = "hello world";
        assertEquals(expected, trimEnd(input, chars));
    }

    @Test
    void testTrimEnd_withCharsNotAtEnd() {
        // Test for a string where trim characters are not at the end (no change should occur)
        String input = "###hello world";
        String chars = "#";
        String expected = "###hello world";
        assertEquals(expected, trimEnd(input, chars));
    }

    @Test
    void testTrimEnd_withAllCharsTrimmed() {
        // Test for a string that only contains the trim characters at the end (should return an empty string)
        String input = "hello world###";
        String chars = "###";
        String expected = "hello world";
        assertEquals(expected, trimEnd(input, chars));
    }

    @Test
    void testTrimEnd_withEmptyChars() {
        // Test for an empty `chars` argument (should behave like regular trimEnd for whitespace)
        String input = "hello world   ";
        String chars = "";
        String expected = "hello world";
        assertEquals(expected, trimEnd(input, chars));
    }

    @Test
    void testTrimEnd_withEmptyChars1() {
        String input = "hello";
        String chars = "";
        String expected = "hello";
        assertEquals(expected, trimEnd(input, chars));
    }

}

