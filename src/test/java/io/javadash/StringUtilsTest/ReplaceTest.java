package io.javadash.StringUtilsTest;

import static io.javadash.StringUtils.replace;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ReplaceTest {

    @Test
    void testReplace_basicReplacement() {
        String result = replace("Hello, world!", "world", "Java");
        assertEquals("Hello, Java!", result, "Expected 'world' to be replaced with 'Java'");
    }

    @Test
    void testReplace_noMatch() {
        String result = replace("Hello, world!", "Python", "Java");
        assertEquals("Hello, world!", result, "Expected string to remain unchanged when pattern is not found");
    }

    @Test
    void testReplace_nullPattern() {
        String result = replace("Hello, world!", null, "Java");
        assertEquals("Hello, world!", result, "Expected string to remain unchanged when pattern is null");
    }

    @Test
    void testReplace_nullReplacement() {
        String result = replace("Hello, world!", "world", null);
        assertEquals("Hello, world!", result, "Expected string to remain unchanged when replacement is null");
    }

    @Test
    void testReplace_emptyString() {
        String result = replace("", "pattern", "replacement");
        assertEquals("", result, "Expected empty string to remain unchanged");
    }

    @Test
    void testReplace_emptyPattern() {
        String result = replace("Hello, world!", "", "Java");
        assertEquals("JavaHello, world!", result, "Expected string to remain unchanged when pattern is empty");
    }

    @Test
    void testReplace_emptyReplacement() {
        String result = replace("Hello, world!", "world", "");
        assertEquals("Hello, !", result, "Expected pattern to be removed when replacement is empty");
    }

    @Test
    void testReplace_multipleOccurrences() {
        String result = replace("banana", "a", "o");
        assertEquals("bonana", result, "Expected all occurrences of 'a' to be replaced with 'o'");
    }
}

