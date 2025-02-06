package io.javadash.StringUtilsTest;

import static io.javadash.StringUtils.split;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class SplitTest {

    @Test
    void testSplit_withValidStringAndSeparator() {
        // Test for a string with a valid separator
        String input = "apple,banana,orange";
        String separator = ",";
        List<String> expected = Arrays.asList("apple", "banana", "orange");
        assertEquals(expected, split(input, separator));
    }

    @Test
    void testSplit_withNullSeparator() {
        // Test for a string with null separator (entire string as one element)
        String input = "apple,banana,orange";
        String separator = null;
        List<String> expected = Arrays.asList("apple,banana,orange");
        assertEquals(expected, split(input, separator));
    }

    @Test
    void testSplit_withEmptyString() {
        // Test for an empty string
        String input = "";
        String separator = ",";
        List<String> expected = new ArrayList<>();
        assertEquals(expected, split(input, separator));
    }

    @Test
    void testSplit_withInvalidString() {
        // Test for an invalid string (null)
        String input = null;
        String separator = ",";
        List<String> expected = new ArrayList<>();
        assertEquals(expected, split(input, separator));
    }

    @Test
    void testSplit_withSingleElement() {
        // Test for a string without any separators
        String input = "apple";
        String separator = ",";
        List<String> expected = Arrays.asList("apple");
        assertEquals(expected, split(input, separator));
    }

    @Test
    void testSplit_withMultipleConsecutiveSeparators() {
        // Test for a string with multiple consecutive separators
        String input = "apple,,banana,,orange";
        String separator = ",";
        List<String> expected = Arrays.asList("apple", "", "banana", "", "orange");
        assertEquals(expected, split(input, separator));
    }
}

