package io.javadash.StringUtilsTest;

import static io.javadash.StringUtils.repeat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RepeatTest {

    @Test
    void testRepeat_positiveRepetition() {
        String result = repeat("abc", 3);
        assertEquals("abcabcabc", result, "Expected string to be repeated 3 times");
    }

    @Test
    void testRepeat_zeroRepetition() {
        String result = repeat("abc", 0);
        assertEquals("", result, "Expected an empty string when repetition count is zero");
    }

    @Test
    void testRepeat_negativeRepetition() {
        String result = repeat("abc", -5);
        assertEquals("", result, "Expected an empty string when repetition count is negative");
    }

    @Test
    void testRepeat_nullString() {
        String result = repeat(null, 4);
        assertEquals("", result, "Expected null string to be treated as an empty string and repeated as empty");
    }

    @Test
    void testRepeat_emptyString() {
        String result = repeat("", 5);
        assertEquals("", result, "Expected empty string to remain empty regardless of repetition count");
    }

    @Test
    void testRepeat_singleRepetition() {
        String result = repeat("abc", 1);
        assertEquals("abc", result, "Expected the original string when repetition count is 1");
    }

    @Test
    void testRepeat_largeRepetition() {
        String result = repeat("x", 1000);
        assertEquals(repeatManually("x", 1000), result, "Expected the string to be repeated 1000 times");
    }
    private static String repeatManually(String str, int times) {
        if (str == null || times <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(str.length() * times);
        for (int i = 0; i < times; i++) {
            sb.append(str);
        }
        return sb.toString();
    }
}

