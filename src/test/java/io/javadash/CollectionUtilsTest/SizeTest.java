package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.size;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

class SizeTest {

    @Test
    void testSizeWithCollection() {
        List<String> list = Arrays.asList("Apple", "Banana", "Cherry");
        int result = size(list);
        assertEquals(3, result, "Size of the list should be 3");
    }

    @Test
    void testSizeWithEmptyCollection() {
        List<String> emptyList = Collections.emptyList();
        int result = size(emptyList);
        assertEquals(0, result, "Size of the empty list should be 0");
    }

    @Test
    void testSizeWithMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("Alice", 30);
        map.put("Bob", 25);
        int result = size(map);
        assertEquals(2, result, "Size of the map should be 2");
    }

    @Test
    void testSizeWithEmptyMap() {
        Map<String, Integer> emptyMap = Collections.emptyMap();
        int result = size(emptyMap);
        assertEquals(0, result, "Size of the empty map should be 0");
    }

    @Test
    void testSizeWithString() {
        String text = "Hello";
        int result = size(text);
        assertEquals(5, result, "Size of the string should be 5");
    }

    @Test
    void testSizeWithEmptyString() {
        String emptyString = "";
        int result = size(emptyString);
        assertEquals(0, result, "Size of the empty string should be 0");
    }

    @Test
    void testSizeWithNull() {
        int result = size(null);
        assertEquals(0, result, "Size of null should be 0");
    }

    @Test
    void testSizeWithOtherObject() {
        Integer number = 42;
        int result = size(number);
        assertEquals(0, result, "Size of a non-collection, non-map, non-string object should be 0");
    }
}

