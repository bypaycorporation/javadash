package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.initial;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class InitialTest {

    @Test
    public void testInitialWithMultipleElements() {
        List<Integer> input = Arrays.asList(1, 2, 3);
        List<Integer> expected = Arrays.asList(1, 2);
        List<Integer> result = initial(input);

        assertEquals(expected, result, "The initial method should return all but the last element.");
    }

    @Test
    public void testInitialWithSingleElement() {
        List<Integer> input = Collections.singletonList(42);
        List<Integer> expected = Collections.emptyList();
        List<Integer> result = initial(input);

        assertEquals(expected, result,
            "The initial method should return an empty list when the input has one element.");
    }

    @Test
    public void testInitialWithEmptyList() {
        List<Integer> input = Collections.emptyList();
        List<Integer> expected = Collections.emptyList();
        List<Integer> result = initial(input);

        assertEquals(expected, result, "The initial method should return an empty list when the input is empty.");
    }

    @Test
    public void testInitialWithNullInput() {
        List<Integer> result = initial(null);

        assertNotNull(result, "The initial method should not return null.");
        assertTrue(result.isEmpty(), "The initial method should return an empty list when the input is null.");
    }

    @Test
    public void testInitialWithStrings() {
        List<String> input = Arrays.asList("apple", "banana", "cherry");
        List<String> expected = Arrays.asList("apple", "banana");
        List<String> result = initial(input);

        assertEquals(expected, result, "The initial method should work correctly with strings.");
    }

    @Test
    public void testInitialWithEmptyInnerList() {
        List<List<Integer>> input = Arrays.asList(Collections.emptyList(), Arrays.asList(1, 2));
        List<List<Integer>> expected = Collections.singletonList(Collections.emptyList());
        List<List<Integer>> result = initial(input);

        assertEquals(expected, result, "The initial method should handle nested lists correctly.");
    }
}

