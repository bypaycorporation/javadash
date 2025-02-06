package io.javadash.StringUtilsTest;

import static io.javadash.StringUtils.lowerCase;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LowerCaseTest {

    @Test
    void testLowerCaseWithCamelCase() {
        // Test when the string is in camel case
        String input = "helloWorld";
        String expected = "hello world";
        assertEquals(expected, lowerCase(input), "Camel case should be converted to lowercase with space-separated words.");
    }

    @Test
    void testLowerCaseWithPascalCase() {
        // Test when the string is in Pascal case
        String input = "HelloWorld";
        String expected = "hello world";
        assertEquals(expected, lowerCase(input), "Pascal case should be converted to lowercase with space-separated words.");
    }

    @Test
    void testLowerCaseWithSpaces() {
        // Test when the string contains spaces between words
        String input = "hello world";
        String expected = "hello world";
        assertEquals(expected, lowerCase(input), "String with spaces should remain unchanged.");
    }

    @Test
    void testLowerCaseWithSpecialCharacters() {
        // Test when the string contains special characters
        String input = "hello@World#Test";
        String expected = "hello world test";
        assertEquals(expected, lowerCase(input), "Special characters should be removed, and words should be in lowercase with spaces.");
    }

    @Test
    void testLowerCaseWithMultipleUppercase() {
        // Test when the string has multiple uppercase letters
        String input = "helloWorldTest";
        String expected = "hello world test";
        assertEquals(expected, lowerCase(input), "Multiple uppercase letters should be handled properly and converted to lowercase.");
    }

    @Test
    void testLowerCaseWithEmptyString() {
        // Test when the string is empty
        String input = "";
        String expected = "";
        assertEquals(expected, lowerCase(input), "Empty string should return an empty string.");
    }

    @Test
    void testLowerCaseWithNullString() {
        // Test when the string is null
        assertEquals("", lowerCase(null), "Null input should return an empty string.");
    }

    @Test
    void testLowerCaseWithSingleWord() {
        // Test when the string contains only a single word
        String input = "hello";
        String expected = "hello";
        assertEquals(expected, lowerCase(input), "Single word should remain unchanged.");
    }

    @Test
    void testLowerCaseWithMultipleSpaces() {
        // Test when the string contains multiple spaces between words
        String input = "hello    world   test";
        String expected = "hello world test";
        assertEquals(expected, lowerCase(input), "Multiple spaces should be replaced with a single space.");
    }

    @Test
    void testLowerCaseWithNonAlphaNumericCharacters() {
        // Test when the string contains non-alphanumeric characters
        String input = "hello_@world!test";
        String expected = "hello world test";
        assertEquals(expected, lowerCase(input), "Non-alphanumeric characters should be removed and words should be lowercase.");
    }
}

