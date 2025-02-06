package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.fill;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class FillTest {

    @Test
    public void testFillWholeList() {
        List<Integer> list = Arrays.asList(1, 2, 3);
        fill(list, 0, 0, list.size());
        assertEquals(Arrays.asList(0, 0, 0), list, "Should fill the entire list with 0");
    }

    @Test
    public void testFillPartialList() {
        List<Integer> list = Arrays.asList(4, 6, 8, 10);
        fill(list, -1, 1, 3);
        assertEquals(Arrays.asList(4, -1, -1, 10), list, "Should fill only the specified range with -1");
    }

    @Test
    public void testFillWithStartGreaterThanEnd() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        fill(list, 9, 3, 2);
        assertEquals(Arrays.asList(1, 2, 3, 4), list, "List should remain unchanged when start > end");
    }

    @Test
    public void testFillWithEmptyList() {
        List<Integer> list = Arrays.asList();
        fill(list, 5, 0, 1);
        assertEquals(Arrays.asList(), list, "Empty list should remain unchanged");
    }

    @Test
    public void testFillWithNullList() {
        List<Integer> list = null;
        List<Integer> result = fill(list, 7, 0, 5);
        assertEquals(Arrays.asList(), result, "Null list should remain empty list");
    }

    @Test
    public void testFillWithStartOutOfBounds() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        fill(list, 5, -2, 3);
        assertEquals(Arrays.asList(5, 5, 5, 4), list, "Should fill with adjusted start index");
    }

    @Test
    public void testFillWithEndOutOfBounds() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        fill(list, 6, 2, 10);
        assertEquals(Arrays.asList(1, 2, 6, 6), list, "Should fill with adjusted end index");
    }

    @Test
    public void testFillWithStartAndEndOutOfBounds() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4);
        fill(list, 8, -5, 10);
        assertEquals(Arrays.asList(8, 8, 8, 8), list, "Should fill the entire list with 8");
    }

    @Test
    public void testFillWithDifferentDataType() {
        List<String> list = Arrays.asList("a", "b", "c", "d");
        fill(list, "z", 1, 3);
        assertEquals(Arrays.asList("a", "z", "z", "d"), list, "Should fill only the specified range with 'z'");
    }

    @Test
    public void testFillSingleElementList() {
        List<Integer> list = Arrays.asList(7);
        fill(list, 3, 0, 1);
        assertEquals(Arrays.asList(3), list, "Should fill a single-element list with the specified value");
    }
}

