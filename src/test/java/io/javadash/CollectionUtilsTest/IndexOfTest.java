package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.indexOf;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class IndexOfTest {
    @Test
    public void testIndexOf_foundFromStart() {
        List<Integer> array = Arrays.asList(1, 2, 1, 2);
        int result = indexOf(array, 2, 0);
        assertEquals(1, result, "Expected to find 2 at index 1.");
    }

    @Test
    public void testIndexOf_foundFromMiddle() {
        List<Integer> array = Arrays.asList(1, 2, 1, 2);
        int result = indexOf(array, 2, 2);
        assertEquals(3, result, "Expected to find 2 at index 3 when starting from index 2.");
    }

    @Test
    public void testIndexOf_notFound() {
        List<Integer> array = Arrays.asList(1, 2, 1, 2);
        int result = indexOf(array, 3, 0);
        assertEquals(-1, result, "Expected to return -1 when value is not found.");
    }

    @Test
    public void testIndexOf_emptyList() {
        List<Integer> array = Collections.emptyList();
        int result = indexOf(array, 1, 0);
        assertEquals(-1, result, "Expected to return -1 for an empty list.");
    }

    @Test
    public void testIndexOf_nullList() {
        int result = indexOf(null, 1, 0);
        assertEquals(-1, result, "Expected to return -1 for a null list.");
    }

    @Test
    public void testIndexOf_negativeFromIndex() {
        List<Integer> array = Arrays.asList(1, 2, 1, 2);
        int result = indexOf(array, 1, -3);
        assertEquals(0, result, "Expected to return 0");
    }

    @Test
    public void testIndexOf_startIndexOutOfBounds() {
        List<Integer> array = Arrays.asList(1, 2, 3, 4);
        int result = indexOf(array, 2, 10);
        assertEquals(-1, result, "Expected to return -1 when starting index is out of bounds.");
    }

    @Test
    public void testIndexOf_valueIsNull() {
        List<Integer> array = Arrays.asList(1, null, 3, null);
        int result = indexOf(array, null, 0);
        assertEquals(1, result, "Expected to find null at index 1.");
    }

    @Test
    public void testIndexOf_startIndexMatches() {
        List<Integer> array = Arrays.asList(1, 2, 3, 4);
        int result = indexOf(array, 2, 1);
        assertEquals(1, result, "Expected to find 2 at the exact start index 1.");
    }

    @Test
    public void testIndexOf_largeNegativeFromIndex() {
        List<Integer> array = Arrays.asList(1, 2, 3, 4);
        int result = indexOf(array, 1, -10);
        assertEquals(0, result, "Expected to find 1 at index 0 when starting index is much lower than array size.");
    }
}
