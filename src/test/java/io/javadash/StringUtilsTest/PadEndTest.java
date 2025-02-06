package io.javadash.StringUtilsTest;

import static io.javadash.StringUtils.padEnd;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PadEndTest {

    @Test
    void testPadEnd_stringShorterThanLength() {
        String result = padEnd("test", 10, "*");
        assertEquals("test******", result, "Expected string to be padded on the right with the specified characters");
    }

    @Test
    void testPadEnd_stringEqualToLength() {
        String result = padEnd("test", 4, "*");
        assertEquals("test", result, "Expected string to remain unchanged when its length equals the target length");
    }

    @Test
    void testPadEnd_stringLongerThanLength() {
        String result = padEnd("test", 3, "*");
        assertEquals("test", result, "Expected string to remain unchanged when its length exceeds the target length");
    }

    @Test
    void testPadEnd_nullString() {
        String result = padEnd(null, 8, "*");
        assertEquals("********", result, "Expected null string to be treated as an empty string and padded");
    }

    @Test
    void testPadEnd_nullChars() {
        String result = padEnd("test", 10, null);
        assertEquals("test      ", result, "Expected default padding with spaces when chars is null");
    }

    @Test
    void testPadEnd_emptyChars() {
        String result = padEnd("test", 10, "");
        assertEquals("test      ", result, "Expected default padding with spaces when chars is empty");
    }

    @Test
    void testPadEnd_negativeLength() {
        String result = padEnd("test", -5, "*");
        assertEquals("test", result, "Expected string to remain unchanged when length is negative");
    }

    @Test
    void testPadEnd_zeroLength() {
        String result = padEnd("test", 0, "*");
        assertEquals("test", result, "Expected string to remain unchanged when length is zero");
    }

    @Test
    void testPadEnd_specialCharsPadding() {
        String result = padEnd("test", 10, "!");
        assertEquals("test!!!!!!", result, "Expected string to be padded with special characters");
    }

    @Test
    void testPadEnd_charsLongerThanPadding() {
        String result = padEnd("test", 12, "ab");
        assertEquals("testabababab", result, "Expected string to be padded using the provided chars repeatedly");
    }
}

