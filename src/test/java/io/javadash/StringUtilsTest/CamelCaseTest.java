package io.javadash.StringUtilsTest;

import static io.javadash.StringUtils.camelCase;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CamelCaseTest {

    @Test
    void testCamelCaseWithEmptyString() {
        // Test that an empty string returns an empty string
        assertEquals("", camelCase(""), "Empty string should return an empty string.");
    }

    @Test
    void testCamelCaseWithSingleWord() {
        // Test that a single word is unchanged
        assertEquals("hello", camelCase("hello"), "Single word should return itself in camel case.");
    }

    @Test
    void testCamelCaseWithMultipleWords() {
        // Test that multiple words are correctly converted to camel case
        assertEquals("helloWorldExample", camelCase("hello world example"),
            "Multiple words should be converted to camel case.");
    }

    @Test
    void testCamelCaseWithMixedCasing() {
        // Test that mixed casing is handled correctly
        assertEquals("helloWorldExample", camelCase("Hello World Example"),
            "Mixed casing should be converted to camel case.");
    }

    @Test
    void testCamelCaseWithNonAlphanumericCharacters() {
        // Test that non-alphanumeric characters are handled correctly as delimiters
        assertEquals("helloWorldExample", camelCase("hello-world_example"),
            "Non-alphanumeric characters should be treated as delimiters.");
    }

    @Test
    void testCamelCaseWithNumbers() {
        // Test that numbers in the string are preserved
        assertEquals("helloWorld123Example", camelCase("hello world 123 example"),
            "Numbers should be preserved in camel case.");
    }

    @Test
    void testCamelCaseWithLeadingNonAlphabeticCharacter() {
        // Test that a string starting with a non-alphabetic character is handled correctly
        assertEquals("123HelloWorld", camelCase("123 hello world"),
            "Leading non-alphabetic characters should be skipped.");
    }

    @Test
    void testCamelCaseWithNullInput() {
        // Test that a null input returns an empty string
        assertEquals("", camelCase(null), "Null input should return an empty string.");
    }
}

