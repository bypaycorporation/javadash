package io.javadash.CollectionUtilsTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.javadash.CollectionUtils;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DifferenceTest {

    @Test
    public void testDifference_withNonOverlappingElements() {
        List<Integer> array = Arrays.asList(1, 2, 3);
        List<Integer> exclude = Arrays.asList(4, 5, 6);

        List<Integer> result = CollectionUtils.difference(array, exclude);

        assertEquals(Arrays.asList(1, 2, 3), result, "The result should be the same list as no elements are excluded.");
    }

    @Test
    public void testDifference_withSomeOverlappingElements() {
        List<Integer> array = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> exclude = Arrays.asList(2, 4);

        List<Integer> result = CollectionUtils.difference(array, exclude);

        assertEquals(Arrays.asList(1, 3, 5), result, "The result should exclude 2 and 4 from the array.");
    }

    @Test
    public void testDifference_withMultipleListsToExclude() {
        List<Integer> array = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> exclude1 = Arrays.asList(2, 4);
        List<Integer> exclude2 = Arrays.asList(3, 5);

        List<Integer> result = CollectionUtils.difference(array, exclude1, exclude2);

        assertEquals(Arrays.asList(1), result, "The result should exclude 2, 3, 4, and 5 from the array.");
    }

    @Test
    public void testDifference_withEmptyArray() {
        List<Integer> array = Arrays.asList();
        List<Integer> exclude = Arrays.asList(2, 3);

        List<Integer> result = CollectionUtils.difference(array, exclude);

        assertEquals(Arrays.asList(), result, "The result should be an empty list as the input array is empty.");
    }

    @Test
    public void testDifference_withNullExcludeLists() {
        List<Integer> array = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> result = CollectionUtils.difference(array, (List<Integer>) null);

        assertEquals(Arrays.asList(1, 2, 3, 4, 5), result,
            "The result should be the same list as no lists are provided to exclude.");
    }

    @Test
    public void testDifference_withNullValuesInExcludeLists() {
        List<Integer> array = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> exclude1 = Arrays.asList(2, 4);
        List<Integer> exclude2 = null;  // Null list
        List<Integer> result = CollectionUtils.difference(array, exclude1, exclude2);

        assertEquals(Arrays.asList(1, 3, 5), result,
            "The result should exclude 2 and 4 from the array, and ignore the null list.");
    }

    @Test
    public void testDifference_withNullValuesInArrayLists() {
        List<Integer> array = null;
        List<Integer> exclude1 = Arrays.asList(2, 4);
        List<Integer> exclude2 = null;  // Null list
        List<Integer> result = CollectionUtils.difference(array, exclude1, exclude2);

        assertEquals(Arrays.asList(), result,
            "The result should return empty list");
    }

    @Test
    public void testDifference_withNullValues() {
        List<Integer> array = Arrays.asList(1, 2, null);
        List<Integer> exclude1 = Arrays.asList(2, 4, null);
        List<Integer> exclude2 = null;  // Null list
        List<Integer> result = CollectionUtils.difference(array, exclude1, exclude2);

        assertEquals(Arrays.asList(1), result,
            "The result should return empty list");
    }
}
