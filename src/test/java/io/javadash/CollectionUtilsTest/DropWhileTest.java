package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.dropWhile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import org.junit.jupiter.api.Test;

public class DropWhileTest {

    @Test
    void testDropWhileWithValidPredicate() {
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5);
        Predicate<Integer> predicate = x -> x < 3;

        List<Integer> result = dropWhile(input, predicate);

        assertEquals(Arrays.asList(3, 4, 5), result);
    }

    @Test
    void testDropWhileWithPredicateAlwaysTrue() {
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5);
        Predicate<Integer> predicate = x -> true;

        List<Integer> result = dropWhile(input, predicate);

        assertTrue(result.isEmpty());
    }

    @Test
    void testDropWhileWithPredicateAlwaysFalse() {
        List<Integer> input = Arrays.asList(1, 2, 3, 4, 5);
        Predicate<Integer> predicate = x -> false;

        List<Integer> result = dropWhile(input, predicate);

        assertEquals(input, result);
    }

    @Test
    void testDropWhileWithEmptyList() {
        List<Integer> input = Collections.emptyList();
        Predicate<Integer> predicate = x -> x < 3;

        List<Integer> result = dropWhile(input, predicate);

        assertTrue(result.isEmpty());
    }

    @Test
    void testDropWhileWithNullList() {
        List<Integer> input = null;
        Predicate<Integer> predicate = x -> x < 3;

        List<Integer> result = dropWhile(input, predicate);

        assertTrue(result.isEmpty());
    }

    @Test
    void testDropWhileWithPartialMatching() {
        List<String> input = Arrays.asList("apple", "banana", "carrot", "date");
        Predicate<String> predicate = x -> x.startsWith("a");

        List<String> result = dropWhile(input, predicate);

        assertEquals(Arrays.asList("banana", "carrot", "date"), result);
    }

    @Test
    void testDropWhileWithPartialMatchingNull() {
        List<String> input = Arrays.asList("apple", "banana", null, "carrot", "date");
        Predicate<String> predicate = x -> x.startsWith("a");

        List<String> result = dropWhile(input, predicate);

        assertEquals(Arrays.asList("banana", null, "carrot", "date"), result);
    }
}

