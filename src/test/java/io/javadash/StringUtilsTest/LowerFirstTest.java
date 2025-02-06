package io.javadash.StringUtilsTest;

import static io.javadash.StringUtils.lowerFirst;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LowerFirstTest {

    @Test
    void testLowerFirstWithUppercaseFirstCharacter() {
        // Test when the string has an uppercase first character
        String input = "Hello";
        String expected = "hello";
        assertEquals(expected, lowerFirst(input), "The first character should be converted to lowercase.");
    }

    @Test
    void testLowerFirstWithLowercaseFirstCharacter() {
        // Test when the string has a lowercase first character
        String input = "hello";
        String expected = "hello";
        assertEquals(expected, lowerFirst(input), "The string should remain unchanged if the first character is already lowercase.");
    }

    @Test
    void testLowerFirstWithSingleCharacter() {
        // Test when the string is a single character
        String input = "A";
        String expected = "a";
        assertEquals(expected, lowerFirst(input), "The single character should be converted to lowercase.");
    }

    @Test
    void testLowerFirstWithEmptyString() {
        // Test when the string is empty
        String input = "";
        String expected = "";
        assertEquals(expected, lowerFirst(input), "An empty string should return an empty string.");
    }

    @Test
    void testLowerFirstWithNullString() {
        // Test when the string is null
        assertEquals("",lowerFirst(null), "Null input should return null.");
    }

    @Test
    void testLowerFirstWithStringContainingNumbers() {
        // Test when the string starts with a number
        String input = "123abc";
        String expected = "123abc";
        assertEquals(expected, lowerFirst(input), "The string should remain unchanged if the first character is not a letter.");
    }

    @Test
    void testLowerFirstWithStringContainingSpecialCharacters() {
        // Test when the string starts with a special character
        String input = "#Hello";
        String expected = "#Hello";
        assertEquals(expected, lowerFirst(input), "The string should remain unchanged if the first character is not a letter.");
    }

    @Test
    void testLowerFirstWithWhitespaceAtStart() {
        // Test when the string starts with whitespace
        String input = " Hello";
        String expected = " Hello";
        assertEquals(expected, lowerFirst(input), "Whitespace at the beginning of the string should not affect the conversion of the first letter.");
    }
}

