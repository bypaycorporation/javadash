package io.javadash.StringUtilsTest;

import static io.javadash.StringUtils.upperFirst;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UpperFirstTest {

    @Test
    void testUpperFirst_withValidString() {
        // Test for converting the first character of the string to upper case
        String input = "hello";
        String expected = "Hello";
        assertEquals(expected, upperFirst(input));
    }

    @Test
    void testUpperFirst_withSingleCharacter() {
        // Test for a single character string
        String input = "h";
        String expected = "H";
        assertEquals(expected, upperFirst(input));
    }

    @Test
    void testUpperFirst_withEmptyString() {
        // Test for an empty string (should return the empty string)
        String input = "";
        String expected = "";
        assertEquals(expected, upperFirst(input));
    }

    @Test
    void testUpperFirst_withNullString() {
        // Test for a null string (should return null)
        String input = null;
        String expected = "";
        assertEquals(expected, upperFirst(input));
    }

    @Test
    void testUpperFirst_withAlreadyUppercaseFirstCharacter() {
        // Test for a string where the first character is already upper case
        String input = "Hello";
        String expected = "Hello";
        assertEquals(expected, upperFirst(input));
    }

    @Test
    void testUpperFirst_withMixedCaseString() {
        // Test for a string where only the first character needs to be changed
        String input = "hELLO";
        String expected = "HELLO";
        assertEquals(expected, upperFirst(input));
    }

    @Test
    void testUpperFirst_withFirstNonAlphabetCharacter() {
        // Test for a string starting with a non-alphabet character
        String input = "1hello";
        String expected = "1hello";
        assertEquals(expected, upperFirst(input));
    }

    @Test
    void testUpperFirst_withFirstCharacterAlreadyUpperCaseAndRestLowerCase() {
        // Test for a string where the first character is upper case and the rest is lower case
        String input = "Hello";
        String expected = "Hello";
        assertEquals(expected, upperFirst(input));
    }
}

