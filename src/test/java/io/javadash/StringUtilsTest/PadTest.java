package io.javadash.StringUtilsTest;

import static io.javadash.StringUtils.pad;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PadTest {

    @Test
    void testPad_stringShorterThanLength_evenPadding() {
        String result = pad("test", 10, "*");
        assertEquals("***test***", result, "Expected string to be padded equally on both sides");
    }

    @Test
    void testPad_stringShorterThanLength_oddPadding() {
        String result = pad("test", 9, "*");
        assertEquals("**test***", result, "Expected string to be padded with an extra character on the right");
    }

    @Test
    void testPad_stringEqualToLength() {
        String result = pad("test", 4, "*");
        assertEquals("test", result, "Expected string to remain unchanged when its length equals the target length");
    }

    @Test
    void testPad_stringLongerThanLength() {
        String result = pad("test", 3, "*");
        assertEquals("test", result, "Expected string to remain unchanged when its length exceeds the target length");
    }

    @Test
    void testPad_nullString() {
        String result = pad(null, 8, "*");
        assertEquals("********", result, "Expected null string to be treated as an empty string and padded");
    }

    @Test
    void testPad_nullChars() {
        String result = pad("test", 10, null);
        assertEquals("   test   ", result, "Expected default padding with spaces when chars is null");
    }

    @Test
    void testPad_emptyChars() {
        String result = pad("test", 10, "");
        assertEquals("   test   ", result, "Expected default padding with spaces when chars is empty");
    }

    @Test
    void testPad_negativeLength() {
        String result = pad("test", -5, "*");
        assertEquals("test", result, "Expected string to remain unchanged when length is negative");
    }

    @Test
    void testPad_zeroLength() {
        String result = pad("test", 0, "*");
        assertEquals("test", result, "Expected string to remain unchanged when length is zero");
    }

    @Test
    void testPad_specialCharsPadding() {
        String result = pad("test", 10, "!");
        assertEquals("!!!test!!!", result, "Expected string to be padded with special characters");
    }

    @Test
    void testPad_charsLongerThanPadding() {
        String result = pad("test", 12, "ab");
        assertEquals("ababtestabab", result, "Expected string to be padded using the provided chars repeatedly");
    }
}

