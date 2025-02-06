package io.javadash.StringUtilsTest;

import static io.javadash.StringUtils.startCase;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class StartCaseTest {

    @Test
    void testStartCase_withValidString() {
        // Test for a standard sentence with multiple words
        String input = "hello world";
        String expected = "Hello World";
        assertEquals(expected, startCase(input));
    }

    @Test
    void testStartCase_withStringContainingNonAlphabets() {
        // Test for a string containing non-alphabet characters (punctuation or digits)
        String input = "hello, world! 123";
        String expected = "Hello World 123";
        assertEquals(expected, startCase(input));
    }

    @Test
    void testStartCase_withEmptyString() {
        // Test for an empty string
        String input = "";
        String expected = "";
        assertEquals(expected, startCase(input));
    }

    @Test
    void testStartCase_withInvalidString() {
        // Test for an invalid string (null)
        String input = null;
        String expected = "";
        assertEquals(expected, startCase(input));
    }

    @Test
    void testStartCase_withSingleWord() {
        // Test for a string with a single word
        String input = "hello";
        String expected = "Hello";
        assertEquals(expected, startCase(input));
    }

    @Test
    void testStartCase_withMultipleSpaces() {
        // Test for a string with extra spaces between words
        String input = "   hello   world   ";
        String expected = "Hello World";
        assertEquals(expected, startCase(input));
    }

    @Test
    void testStartCase_1() {
        // Test for a string with extra spaces between words
        String input = "--foo-bar--";
        String expected = "Foo Bar";
        assertEquals(expected, startCase(input));
    }
}

