package io.javadash.StringUtilsTest;

import static io.javadash.StringUtils.snakeCase;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SnakeCaseTest {

    @Test
    void testSnakeCase_withValidString() {
        // Test for a standard camelCase string
        String input = "camelCaseString";
        String expected = "camel_case_string";
        assertEquals(expected, snakeCase(input));
    }

    @Test
    void testSnakeCase_withUpperCaseLetters() {
        // Test for a string with upper case letters
        String input = "HelloWorld";
        String expected = "hello_world";
        assertEquals(expected, snakeCase(input));
    }

    @Test
    void testSnakeCase_withEmptyString() {
        // Test for an empty string
        String input = "";
        String expected = "";
        assertEquals(expected, snakeCase(input));
    }

    @Test
    void testSnakeCase_withInvalidString() {
        // Test for an invalid string (this depends on your definition of "invalid")
        String input = null;
        String expected = "";
        assertEquals(expected, snakeCase(input));
    }

    @Test
    void testSnakeCase_withStringContainingSpaces() {
        // Test for a string with spaces
        String input = "Hello World";
        String expected = "hello_world";
        assertEquals(expected, snakeCase(input));
    }

    @Test
    void testSnakeCase_withSingleWord() {
        // Test for a single word in lower case
        String input = "hello";
        String expected = "hello";
        assertEquals(expected, snakeCase(input));
    }
}

