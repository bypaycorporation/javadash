package io.javadash.StringUtilsTest;

import static io.javadash.StringUtils.kebabCase;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class KebabCaseTest {

    @Test
    void testKebabCaseWithCamelCase() {
        // Test when the string is in camel case
        String input = "helloWorld";
        String expected = "hello-world";
        assertEquals(expected, kebabCase(input), "Camel case should be converted to kebab case.");
    }

    @Test
    void testKebabCaseWithPascalCase() {
        // Test when the string is in Pascal case
        String input = "HelloWorld";
        String expected = "hello-world";
        assertEquals(expected, kebabCase(input), "Pascal case should be converted to kebab case.");
    }

    @Test
    void testKebabCaseWithSpaces() {
        // Test when the string contains spaces between words
        String input = "hello World";
        String expected = "hello-world";
        assertEquals(expected, kebabCase(input), "Spaces between words should be replaced with hyphens.");
    }

    @Test
    void testKebabCaseWithSpecialCharacters() {
        // Test when the string contains special characters
        String input = "hello@World#Test";
        String expected = "hello-world-test";
        assertEquals(expected, kebabCase(input), "Special characters should be removed, and words should be converted to kebab case.");
    }

    @Test
    void testKebabCaseWithMultipleUppercase() {
        // Test when the string has multiple uppercase letters
        String input = "helloWorldTest";
        String expected = "hello-world-test";
        assertEquals(expected, kebabCase(input), "Multiple uppercase letters should be handled properly in kebab case.");
    }

    @Test
    void testKebabCaseWithEmptyString() {
        // Test when the string is empty
        String input = "";
        String expected = "";
        assertEquals(expected, kebabCase(input), "Empty string should return an empty string.");
    }

    @Test
    void testKebabCaseWithNullString() {
        // Test when the string is null
        assertEquals("", kebabCase(null), "Null input should return an empty string.");
    }

    @Test
    void testKebabCaseWithSingleWord() {
        // Test when the string contains only a single word
        String input = "hello";
        String expected = "hello";
        assertEquals(expected, kebabCase(input), "Single word should remain unchanged.");
    }

    @Test
    void testKebabCaseWithMultipleSpaces() {
        // Test when the string contains multiple spaces between words
        String input = "hello    world   test";
        String expected = "hello-world-test";
        assertEquals(expected, kebabCase(input), "Multiple spaces should be replaced with a single hyphen.");
    }

    @Test
    void testKebabCaseWithNonAlphaNumericCharacters() {
        // Test when the string contains non-alphanumeric characters
        String input = "hello_@world!";
        String expected = "hello-world";
        assertEquals(expected, kebabCase(input), "Non-alphanumeric characters should be removed.");
    }
}

