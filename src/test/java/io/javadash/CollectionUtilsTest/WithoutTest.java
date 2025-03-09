package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.without;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class WithoutTest {

    @Test
    void testWithout() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        // Remove 2 and 4 from the list
        List<Integer> result = without(list, 2, 4);

        // The result should contain the elements 1, 3, 5
        assertEquals(Arrays.asList(1, 3, 5), result);
    }

    @Test
    void testWithoutEmptyList() {
        List<Integer> list = Collections.emptyList();

        // Remove 2 and 4 from the empty list
        List<Integer> result = without(list, 2, 4);

        // The result should be an empty list
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testWithoutNullList() {
        List<Integer> list = null;

        // Remove 2 and 4 from the null list
        List<Integer> result = without(list, 2, 4);

        // The result should be an empty list since the input is null
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testWithoutValuesNotInList() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        // Remove values not in the list
        List<Integer> result = without(list, 6, 7);

        // The result should be the same as the original list since no values were removed
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), result);
    }

    @Test
    void testWithoutAllValues() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        // Remove all values from the list
        List<Integer> result = without(list, 1, 2, 3, 4, 5);

        // The result should be an empty list
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testWithoutDuplicates() {
        List<Integer> list = Arrays.asList(1, 2, 2, 3, 3, 4);

        // Remove 2 and 3 from the list
        List<Integer> result = without(list, 2, 3);

        // The result should contain [1, 4], removing duplicates as well
        assertEquals(Arrays.asList(1, 4), result);
    }
}






