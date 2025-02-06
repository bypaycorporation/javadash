package io.javadash.StringUtilsTest;

import static io.javadash.StringUtils.padStart;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PadStartTest {

    @Test
    void testPadStart_stringShorterThanLength() {
        String result = padStart("test", 10, "*");
        assertEquals("******test", result, "Expected string to be padded on the left with the specified characters");
    }

    @Test
    void testPadStart_stringEqualToLength() {
        String result = padStart("test", 4, "*");
        assertEquals("test", result, "Expected string to remain unchanged when its length equals the target length");
    }

    @Test
    void testPadStart_stringLongerThanLength() {
        String result = padStart("test", 3, "*");
        assertEquals("test", result, "Expected string to remain unchanged when its length exceeds the target length");
    }

    @Test
    void testPadStart_nullString() {
        String result = padStart(null, 8, "*");
        assertEquals("********", result, "Expected null string to be treated as an empty string and padded");
    }

    @Test
    void testPadStart_nullChars() {
        String result = padStart("test", 10, null);
        assertEquals("      test", result, "Expected default padding with spaces when chars is null");
    }

    @Test
    void testPadStart_emptyChars() {
        String result = padStart("test", 10, "");
        assertEquals("      test", result, "Expected default padding with spaces when chars is empty");
    }

    @Test
    void testPadStart_negativeLength() {
        String result = padStart("test", -5, "*");
        assertEquals("test", result, "Expected string to remain unchanged when length is negative");
    }

    @Test
    void testPadStart_zeroLength() {
        String result = padStart("test", 0, "*");
        assertEquals("test", result, "Expected string to remain unchanged when length is zero");
    }

    @Test
    void testPadStart_specialCharsPadding() {
        String result = padStart("test", 10, "!");
        assertEquals("!!!!!!test", result, "Expected string to be padded with special characters");
    }

    @Test
    void testPadStart_charsLongerThanPadding() {
        String result = padStart("test", 12, "ab");
        assertEquals("ababababtest", result, "Expected string to be padded using the provided chars repeatedly");
    }
}

