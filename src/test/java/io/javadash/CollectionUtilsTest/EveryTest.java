package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.every;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import org.junit.jupiter.api.Test;

public class EveryTest {
    @Test
    void testEveryList_allElementsSatisfyPredicate() {
        List<Integer> list = Arrays.asList(2, 4, 6, 8);
        Predicate<Integer> isEven = x -> x % 2 == 0;

        boolean result = every(list, isEven);

        assertTrue(result, "Expected all elements to satisfy the predicate");
    }

    @Test
    void testEveryList_someElementsDoNotSatisfyPredicate() {
        List<Integer> list = Arrays.asList(2, 3, 6, 8);
        Predicate<Integer> isEven = x -> x % 2 == 0;

        boolean result = every(list, isEven);

        assertFalse(result, "Expected not all elements to satisfy the predicate");
    }

    @Test
    void testEveryList_emptyList() {
        List<Integer> list = new ArrayList<>();
        Predicate<Integer> isEven = x -> x % 2 == 0;

        boolean result = every(list, isEven);

        assertFalse(result, "Expected an empty list to return false");
    }

    @Test
    void testEveryList_nullList() {
        Predicate<Integer> isEven = x -> x % 2 == 0;

        boolean result = every(null, isEven);

        assertFalse(result, "Expected a null list to return false");
    }

    @Test
    void testEveryList_nullPredicate() {
        List<Integer> list = Arrays.asList(1, 2, 3);

        assertThrows(NullPointerException.class, () -> every(list, null));
    }

    @Test
    void testEveryMap_allEntriesSatisfyBiPredicate() {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 2);
        map.put("B", 4);

        BiPredicate<String, Integer> keyValueCondition = (key, value) -> value % 2 == 0;

        boolean result = every(map, keyValueCondition);

        assertTrue(result, "Expected all map entries to satisfy the BiPredicate");
    }

    @Test
    void testEveryMap_someEntriesDoNotSatisfyBiPredicate() {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 2);
        map.put("B", 3);

        BiPredicate<String, Integer> keyValueCondition = (key, value) -> value % 2 == 0;

        boolean result = every(map, keyValueCondition);

        assertFalse(result, "Expected not all map entries to satisfy the BiPredicate");
    }

    @Test
    void testEveryMap_emptyMap() {
        Map<String, Integer> map = new HashMap<>();
        BiPredicate<String, Integer> keyValueCondition = (key, value) -> value % 2 == 0;

        boolean result = every(map, keyValueCondition);

        assertFalse(result, "Expected an empty map to return false");
    }

    @Test
    void testEveryMap_nullMap() {
        BiPredicate<String, Integer> keyValueCondition = (key, value) -> value % 2 == 0;

        boolean result = every(null, keyValueCondition);

        assertFalse(result, "Expected a null map to return false");
    }

    @Test
    void testEveryMap_nullBiPredicate() {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        assertThrows(NullPointerException.class, () -> every(map, null));
    }
}
