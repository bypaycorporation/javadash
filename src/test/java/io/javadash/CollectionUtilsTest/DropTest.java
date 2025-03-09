package io.javadash.CollectionUtilsTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.javadash.CollectionUtils;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DropTest {

    @Test
    public void testDropWithValidInput() {
        // Test case 1: Drop 1 element from the start
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> result = CollectionUtils.drop(list, 1);
        assertEquals(Arrays.asList(2, 3, 4, 5), result);

        // Test case 2: Drop 2 elements from the start
        result = CollectionUtils.drop(list, 2);
        assertEquals(Arrays.asList(3, 4, 5), result);

        // Test case 3: Drop 5 elements (entire list)
        result = CollectionUtils.drop(list, 5);
        assertEquals(Collections.emptyList(), result);

        // Test case 4: Drop 0 elements (should return the same list)
        result = CollectionUtils.drop(list, 0);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), result);
    }

    @Test
    public void testDropWithNegativeN() {
        // Test case 5: Negative n value, should treat as 0 (return full list)
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> result = CollectionUtils.drop(list, -1);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), result);
    }

    @Test
    public void testDropWithEmptyList() {
        // Test case 6: Drop from an empty list, should return empty list
        List<Integer> list = Collections.emptyList();
        List<Integer> result = CollectionUtils.drop(list, 1);
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testDropWithNullList() {
        // Test case 7: Null list input, should return empty list
        List<Integer> result = CollectionUtils.drop(null, 1);
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    public void testDropWithNGreaterThanListSize() {
        // Test case 8: Drop more elements than the size of the list, should return empty list
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> result = CollectionUtils.drop(list, 10);
        assertEquals(Collections.emptyList(), result);
    }
}

