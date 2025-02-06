package io.javadash.StringUtilsTest;

import static io.javadash.StringUtils.escape;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class EscapeTest {

    @Test
    void testEscapeWithValidString() {
        // Test when the string contains HTML special characters
        String input = "<div>Hello & Welcome</div>";
        String expected = "&lt;div&gt;Hello &amp; Welcome&lt;/div&gt;";
        assertEquals(expected, escape(input), "The string should escape HTML special characters correctly.");
    }

    @Test
    void testEscapeWithNoSpecialCharacters() {
        // Test when the string does not contain any HTML special characters
        String input = "Hello World";
        String expected = "Hello World";
        assertEquals(expected, escape(input), "The string should remain unchanged if no special characters are present.");
    }

    @Test
    void testEscapeWithEmptyString() {
        // Test when the string is empty
        String input = "";
        String expected = "";
        assertEquals(expected, escape(input), "The empty string should return an empty string.");
    }

    @Test
    void testEscapeWithNullString() {
        // Test when the string is null
        assertEquals("", escape(null), "Null input should return an empty string.");
    }

    @Test
    void testEscapeWithMultipleSpecialCharacters() {
        // Test when the string contains multiple HTML special characters
        String input = "<div>Hello & <b>World</b></div>";
        String expected = "&lt;div&gt;Hello &amp; &lt;b&gt;World&lt;/b&gt;&lt;/div&gt;";
        assertEquals(expected, escape(input), "The string should correctly escape all special characters.");
    }

    @Test
    void testEscapeWithSpecialCharactersOnly() {
        // Test when the string contains only HTML special characters
        String input = "<>&\"'";
        String expected = "&lt;&gt;&amp;&quot;&#39;";
        assertEquals(expected, escape(input), "The string should escape all special characters.");
    }

    @Test
    void testEscapeWithWhitespaceOnly() {
        // Test when the string contains only whitespace
        String input = "   ";
        String expected = "   ";
        assertEquals(expected, escape(input), "Whitespace should not be altered.");
    }

    @Test
    void testEscapeWithStringContainingLineBreaks() {
        // Test when the string contains line breaks
        String input = "Hello\nWorld";
        String expected = "Hello\nWorld"; // Assuming line breaks are not altered by the escape function
        assertEquals(expected, escape(input), "Line breaks should remain unchanged.");
    }
}

