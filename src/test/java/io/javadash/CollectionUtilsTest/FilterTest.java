package io.javadash.CollectionUtilsTest;
import org.junit.jupiter.api.Test;

import java.util.*;

import static io.javadash.CollectionUtils.filter;
import static org.junit.jupiter.api.Assertions.*;
public class FilterTest {
    @Test
    void testFilter_NullPredicate_ShouldThrowException() {
        List<String> list = Arrays.asList("a", "b", "c");
        assertThrows(NullPointerException.class, () -> filter(list, null));
    }

    @Test
    void testFilter_EmptyList_ShouldReturnEmptyList() {
        List<String> list = Collections.emptyList();
        List<String> result = filter(list, s -> s.startsWith("a"));
        assertTrue(result.isEmpty());
    }

    @Test
    void testFilter_NullList_ShouldReturnEmptyList() {
        List<String> result = filter(null, s -> s.startsWith("a"));
        assertTrue(result.isEmpty());
    }

    @Test
    void testFilter_ValidFilter_ShouldReturnFilteredElements() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> expected = Arrays.asList(2, 4, 6);
        List<Integer> result = filter(list, n -> n % 2 == 0);
        assertEquals(expected, result);
    }

    @Test
    void testFilter_WithNullValues_ShouldIgnoreNulls() {
        List<String> list = Arrays.asList("apple", null, "banana", "apricot", null);
        List<String> expected = Arrays.asList("apple", "apricot");
        List<String> result = filter(list, s -> s.startsWith("a"));
        assertEquals(expected, result);
    }

    @Test
    void testFilterMap_NullPredicate_ShouldThrowException() {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        assertThrows(NullPointerException.class, () -> filter(map, null));
    }

    @Test
    void testFilterMap_EmptyMap_ShouldReturnEmptyMap() {
        Map<String, Integer> map = Collections.emptyMap();
        Map<String, Integer> result = filter(map, (k, v) -> v > 1);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFilterMap_NullMap_ShouldReturnEmptyMap() {
        Map<String, Integer> result = filter(null, (k, v) -> v > 1);
        assertTrue(result.isEmpty());
    }

    @Test
    void testFilterMap_ValidFilter_ShouldReturnFilteredMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);

        Map<String, Integer> expected = new HashMap<>();
        expected.put("b", 2);
        expected.put("c", 3);

        Map<String, Integer> result = filter(map, (k, v) -> v > 1);
        assertEquals(expected, result);
    }

    @Test
    void testFilterMap_WithNullKeysOrValues_ShouldIgnoreNullEntries() {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 1);
        map.put(null, 2);
        map.put("c", null);
        map.put("d", 4);

        Map<String, Integer> expected = new HashMap<>();
        expected.put("d", 4);
        Map<String, Integer> result = filter(map, (k, v) -> v > 2);
        assertEquals(expected, result);
    }
}
