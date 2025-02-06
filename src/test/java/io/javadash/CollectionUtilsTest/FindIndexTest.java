package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.findIndex;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class FindIndexTest {

    @Test
    public void testFindIndexWithValidInput() {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        // Test case 1: Find index of "Bob"
        int index1 = findIndex(names, name -> "Bob".equals(name), 0);
        assertEquals(1, index1);

        // Test case 2: Find index of "Charlie" starting from index 2
        int index2 = findIndex(names, name -> "Charlie".equals(name), 2);
        assertEquals(2, index2);

        // Test case 3: Find index of a non-existing element
        int index3 = findIndex(names, name -> "Eve".equals(name), 0);
        assertEquals(-1, index3);
    }

    @Test
    public void testFindIndexWithNegativeFromIndex() {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        // Test case 4: Find index of "Charlie" with a negative fromIndex
        int index4 = findIndex(names, name -> "Charlie".equals(name), -2);
        assertEquals(2, index4);
    }

    @Test
    public void testFindIndexWithNullArray() {
        // Test case 6: Null array should return -1
        int index6 = findIndex(null, name -> "Alice".equals(name), 0);
        assertEquals(-1, index6);
    }

    @Test
    public void testFindIndexWithEmptyArray() {
        List<String> emptyList = Arrays.asList();

        // Test case 7: Empty array should return -1
        int index7 = findIndex(emptyList, name -> "Alice".equals(name), 0);
        assertEquals(-1, index7);
    }

    @Test
    public void testFindIndexWithFromIndexGreaterThanSize() {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");

        // Test case 8: fromIndex greater than array size should return -1
        int index8 = findIndex(names, name -> "Alice".equals(name), 10);
        assertEquals(-1, index8);
    }
}

