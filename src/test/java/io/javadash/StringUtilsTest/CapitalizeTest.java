package io.javadash.StringUtilsTest;

import static io.javadash.StringUtils.capitalize;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CapitalizeTest {

    @Test
    void testCapitalizeWithValidWord() {
        // Test that the first letter is capitalized and the rest are in lowercase
        assertEquals("Hello", capitalize("hello"), "First letter should be capitalized and others in lowercase.");
    }

    @Test
    void testCapitalizeWithEmptyString() {
        // Test that an empty string returns an empty string
        assertEquals("", capitalize(""), "Empty string should return an empty string.");
    }

    @Test
    void testCapitalizeWithNullInput() {
        // Test that a null input returns an empty string
        assertEquals("", capitalize(null), "Null input should return an empty string.");
    }

    @Test
    void testCapitalizeWithSingleUppercaseLetter() {
        // Test that a single uppercase letter is unchanged
        assertEquals("A", capitalize("A"), "Single uppercase letter should be unchanged.");
    }

    @Test
    void testCapitalizeWithMixedCase() {
        // Test that a word with mixed case is correctly capitalized
        assertEquals("Hello", capitalize("hElLo"), "Mixed case word should be properly capitalized.");
    }

    @Test
    void testCapitalizeWithAllUppercase() {
        // Test that a word with all uppercase letters is properly capitalized
        assertEquals("Hello", capitalize("HELLO"), "All uppercase letters should be converted to proper capitalization.");
    }

    @Test
    void testCapitalizeWithSingleCharacter() {
        // Test that a single character word is properly capitalized
        assertEquals("A", capitalize("a"), "Single character should be capitalized.");
    }

    @Test
    void testCapitalizeWithNonAlphaCharacter() {
        // Test that a non-alphabetic character (like a number or symbol) is not affected
        assertEquals("123", capitalize("123"), "Non-alphabetic characters should remain unchanged.");
    }
}

