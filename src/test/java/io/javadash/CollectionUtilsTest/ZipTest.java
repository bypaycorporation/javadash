package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.zip;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ZipTest {

    @Test
    void testZipWithEqualLengthLists() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);

        // Zip operation between two lists with equal lengths
        List<List<Integer>> result = zip(list1, list2);

        // The result should group the elements at corresponding indices
        assertEquals(Arrays.asList(Arrays.asList(1, 4), Arrays.asList(2, 5), Arrays.asList(3, 6)), result);
    }

    @Test
    void testZipWithUnequalLengthLists() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5);

        // Zip operation between two lists with unequal lengths
        List<List<Integer>> result = zip(list1, list2);

        // The result should handle shorter lists by adding null for missing elements
        assertEquals(Arrays.asList(Arrays.asList(1, 4), Arrays.asList(2, 5), Arrays.asList(3, null)), result);
    }

    @Test
    void testZipWithEmptyLists() {
        List<Integer> list1 = Arrays.asList();
        List<Integer> list2 = Arrays.asList();

        // Zip operation between two empty lists
        List<List<Integer>> result = zip(list1, list2);

        // The result should be an empty list
        assertEquals(Arrays.asList(), result);
    }

    @Test
    void testZipWithNullAndEmptyLists() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList();

        // Zip operation where one list is empty
        List<List<Integer>> result = zip(list1, list2);

        // The result should handle the empty list and add null for each index
        assertEquals(Arrays.asList(Arrays.asList(1, null), Arrays.asList(2, null), Arrays.asList(3, null)), result);
    }

    @Test
    void testZipWithMultipleLists() {
        List<Integer> list1 = Arrays.asList(1, 2);
        List<Integer> list2 = Arrays.asList(3, 4);
        List<Integer> list3 = Arrays.asList(5, 6);

        // Zip operation with three lists of equal length
        List<List<Integer>> result = zip(list1, list2, list3);

        // The result should group the elements at corresponding indices
        assertEquals(Arrays.asList(Arrays.asList(1, 3, 5), Arrays.asList(2, 4, 6)), result);
    }

    @Test
    void testZipWithNullListInTheMiddle() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = null;
        List<Integer> list3 = Arrays.asList(4, 5, 6);

        // Zip operation with a null list in the middle
        List<List<Integer>> result = zip(list1, list2, list3);

        // The result should handle the null list by adding null for its elements
        assertEquals(Arrays.asList(Arrays.asList(1, null, 4), Arrays.asList(2, null, 5), Arrays.asList(3, null, 6)), result);
    }

    @Test
    void testZipWithNullAndEmpty() {
        List<Integer> list1 = Arrays.asList();
        List<Integer> list2 = null;

        // Zip operation with an empty list and a null list
        List<List<Integer>> result = zip(list1, list2);

        // The result should return an empty list
        assertEquals(Arrays.asList(), result);
    }
}






