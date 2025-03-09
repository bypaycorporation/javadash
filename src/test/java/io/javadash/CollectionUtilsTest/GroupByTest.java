package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.groupBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class GroupByTest {
    @Test
    void testGroupBy_validCollection() {
        List<String> collection = Arrays.asList("apple", "banana", "avocado", "blueberry", "apricot");

        Map<Character, List<String>> result = groupBy(collection, s -> s.charAt(0)); // Group by the first letter

        Map<Character, List<String>> expected = new HashMap<>();
        expected.put('a', Arrays.asList("apple", "avocado", "apricot"));
        expected.put('b', Arrays.asList("banana", "blueberry"));

        assertEquals(expected, result);
    }

    @Test
    void testGroupBy_emptyCollection() {
        List<String> collection = Collections.emptyList();

        Map<Character, List<String>> result = groupBy(collection, s -> s.charAt(0)); // Group by the first letter

        assertEquals(Collections.emptyMap(), result);  // Should return an empty map
    }

    @Test
    void testGroupBy_nullCollection() {
        List<String> collection = null;

        Map<Character, List<String>> result = groupBy(collection, s -> s.charAt(0)); // Group by the first letter

        assertEquals(Collections.emptyMap(), result);  // Should return an empty map
    }

    @Test
    void testGroupBy_collectionWithNullElements() {
        List<String> collection = Arrays.asList("apple", null, "banana", "avocado", null);

        Map<Character, List<String>> result = groupBy(collection, s -> s.charAt(0)); // Group by the first letter

        Map<Character, List<String>> expected = new HashMap<>();
        expected.put('a', Arrays.asList("apple", "avocado"));
        expected.put('b', Collections.singletonList("banana"));

        assertEquals(expected, result);  // Null elements should be ignored
    }

    @Test
    void testGroupBy_groupingByLength() {
        List<String> collection = Arrays.asList("apple", "bat", "cat", "dog", "elephant", "pie");

        Map<Integer, List<String>> result = groupBy(collection, String::length); // Group by the length of the string

        Map<Integer, List<String>> expected = new HashMap<>();
        expected.put(3, Arrays.asList("bat", "cat", "dog", "pie"));
        expected.put(5, Collections.singletonList("apple"));
        expected.put(8, Collections.singletonList("elephant"));

        assertEquals(expected, result);
    }

    @Test
    void testGroupBy_groupingByFirstLetterUpperCase() {
        List<String> collection = Arrays.asList("Apple", "banana", "Avocado", "blueberry", "apricot");

        Map<Character, List<String>> result =
            groupBy(collection, s -> s.toUpperCase().charAt(0)); // Group by first letter in uppercase

        Map<Character, List<String>> expected = new HashMap<>();
        expected.put('A', Arrays.asList("Apple", "Avocado", "apricot"));
        expected.put('B', Arrays.asList("banana", "blueberry"));

        assertEquals(expected, result);
    }
}
