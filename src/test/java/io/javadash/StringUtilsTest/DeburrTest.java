package io.javadash.StringUtilsTest;

import static io.javadash.StringUtils.deburr;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DeburrTest {

    @Test
    void testDeburrWithValidString() {
        // Test that accented characters are properly removed
        assertEquals("aeiou", deburr("áéíóú"),
            "Accented characters should be replaced with the corresponding base characters.");
    }

    @Test
    void testDeburrWithEmptyString() {
        // Test that an empty string returns an empty string
        assertEquals("", deburr(""), "Empty string should return an empty string.");
    }

    @Test
    void testDeburrWithNullInput() {
        // Test that a null input returns an empty string
        assertEquals("", deburr(null), "Null input should return an empty string.");
    }

    @Test
    void testDeburrWithStringWithoutAccents() {
        // Test that a string without accents is unchanged
        assertEquals("hello", deburr("hello"), "String without accents should remain unchanged.");
    }

    @Test
    void testDeburrWithSpecialCharacters() {
        // Test that special characters like ñ are correctly handled
        assertEquals("n", deburr("ñ"), "Special characters like ñ should be deburred.");
    }

    @Test
    void testDeburrWithCombiningMarks() {
        // Test that combining marks are removed
        assertEquals("a", deburr("a\u0300"), "Combining marks should be removed.");
    }

    @Test
    void testDeburrWithMultipleAccents() {
        // Test that multiple accented characters are deburred correctly
        assertEquals("cafe", deburr("cafè"), "Multiple accented characters should be deburred properly.");
    }

    @Test
    void testDeburrWithNonAlphabeticCharacters() {
        // Test that non-alphabetic characters are not affected
        assertEquals("1234", deburr("1234"), "Non-alphabetic characters should remain unchanged.");
    }
}

