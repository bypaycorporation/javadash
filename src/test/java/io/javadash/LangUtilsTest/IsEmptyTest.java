package io.javadash.LangUtilsTest;

import static io.javadash.LangUtils.isEmpty;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;

class IsEmptyTest {

    @Test
    void testIsEmptyWithNull() {
        // Test that null is considered empty
        assertTrue(isEmpty(null), "Null value should be considered empty.");
    }

    @Test
    void testIsEmptyWithEmptyString() {
        // Test that an empty string is considered empty
        assertTrue(isEmpty(""), "Empty string should be considered empty.");
    }

    @Test
    void testIsEmptyWithNonEmptyString() {
        // Test that a non-empty string is not empty
        assertFalse(isEmpty("Hello"), "Non-empty string should not be considered empty.");
    }

    @Test
    void testIsEmptyWithEmptyList() {
        // Test that an empty List is considered empty
        List<String> emptyList = new ArrayList<>();
        assertTrue(isEmpty(emptyList), "Empty list should be considered empty.");
    }

    @Test
    void testIsEmptyWithNonEmptyList() {
        // Test that a non-empty List is not empty
        List<String> nonEmptyList = new ArrayList<>();
        nonEmptyList.add("Item");
        assertFalse(isEmpty(nonEmptyList), "Non-empty list should not be considered empty.");
    }

    @Test
    void testIsEmptyWithEmptyMap() {
        // Test that an empty Map is considered empty
        Map<String, String> emptyMap = new HashMap<>();
        assertTrue(isEmpty(emptyMap), "Empty map should be considered empty.");
    }

    @Test
    void testIsEmptyWithNonEmptyMap() {
        // Test that a non-empty Map is not empty
        Map<String, String> nonEmptyMap = new HashMap<>();
        nonEmptyMap.put("Key", "Value");
        assertFalse(isEmpty(nonEmptyMap), "Non-empty map should not be considered empty.");
    }

    @Test
    void testIsEmptyWithEmptySet() {
        // Test that an empty Set is considered empty
        Set<String> emptySet = new HashSet<>();
        assertTrue(isEmpty(emptySet), "Empty set should be considered empty.");
    }

    @Test
    void testIsEmptyWithNonEmptySet() {
        // Test that a non-empty Set is not empty
        Set<String> nonEmptySet = new HashSet<>();
        nonEmptySet.add("Element");
        assertFalse(isEmpty(nonEmptySet), "Non-empty set should not be considered empty.");
    }

    @Test
    void testIsEmptyWithEmptyArray() {
        // Test that an empty array is considered empty
        String[] emptyArray = new String[0];
        assertTrue(isEmpty(emptyArray), "Empty array should be considered empty.");
    }

    @Test
    void testIsEmptyWithNonEmptyArray() {
        // Test that a non-empty array is not empty
        String[] nonEmptyArray = new String[]{"Item"};
        assertFalse(isEmpty(nonEmptyArray), "Non-empty array should not be considered empty.");
    }

    @Test
    void testIsEmptyWithCustomObjectWithFields() {
        // Test that an object with fields is not considered empty
        class NonEmptyObject {
            private String name;
        }
        NonEmptyObject nonEmptyObject = new NonEmptyObject();
        assertFalse(isEmpty(nonEmptyObject), "Object with fields should not be considered empty.");
    }
}

