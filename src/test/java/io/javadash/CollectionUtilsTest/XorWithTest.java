package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.xorWith;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import org.junit.jupiter.api.Test;

public class XorWithTest {

    @Test
    void testXorWith() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(3, 4, 5);
        List<Integer> list3 = Arrays.asList(5, 6, 7);

        // Define a comparator that compares elements for equality using the `equals` method
        BiPredicate<Integer, Integer> comparator = (a, b) -> a.equals(b);

        // XOR operation between the lists, considering the comparator
        List<Integer> result = xorWith(comparator, list1, list2, list3);

        // The result should contain the elements [1, 2, 4, 6, 7]
        // because these are the elements that are in only one list after applying the comparator.
        assertEquals(Arrays.asList(1, 2, 4, 6, 7), result);
    }

    @Test
    void testXorWithEmptyList() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList();

        // Define the comparator to compare elements
        BiPredicate<Integer, Integer> comparator = (a, b) -> a.equals(b);

        // XOR operation between list1 and an empty list should return list1
        List<Integer> result = xorWith(comparator, list1, list2);

        assertEquals(Arrays.asList(1, 2, 3), result);
    }

    @Test
    void testXorWithNullList() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = null;

        // Define the comparator to compare elements
        BiPredicate<Integer, Integer> comparator = (a, b) -> a.equals(b);

        // XOR operation between list1 and null should return list1
        List<Integer> result = xorWith(comparator, list1, list2);

        assertEquals(Arrays.asList(1, 2, 3), result);
    }

    @Test
    void testXorWithIdenticalLists() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(1, 2, 3);

        // Define the comparator to compare elements
        BiPredicate<Integer, Integer> comparator = (a, b) -> a.equals(b);

        // XOR operation between two identical lists should return an empty list
        List<Integer> result = xorWith(comparator, list1, list2);

        assertEquals(Arrays.asList(), result);
    }

    @Test
    void testXorWithMultipleLists() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(3, 4, 5);
        List<Integer> list3 = Arrays.asList(1, 5, 6);

        // Define the comparator to compare elements
        BiPredicate<Integer, Integer> comparator = (a, b) -> a.equals(b);

        // XOR operation between three lists
        List<Integer> result = xorWith(comparator, list1, list2, list3);

        // The result should contain the elements [2, 4, 6]
        assertEquals(Arrays.asList(2, 4, 6), result);
    }

    @Test
    void testXorWithCustomComparator() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(3, 4, 5);

        // Define a custom comparator that compares the absolute value of elements
        BiPredicate<Integer, Integer> comparator = (a, b) -> Math.abs(a) == Math.abs(b);

        // XOR operation with the custom comparator
        List<Integer> result = xorWith(comparator, list1, list2);

        // The result should contain the elements [1, 2, 4, 5]
        // because elements with equal absolute values are considered the same
        assertEquals(Arrays.asList(1, 2, 4, 5), result);
    }

    @Test
    void testXorWithEdgeCase() {
        List<Integer> list1 = Arrays.asList(1, 1, 2, 2);
        List<Integer> list2 = Arrays.asList(1, 2);

        // Define the comparator to compare elements
        BiPredicate<Integer, Integer> comparator = (a, b) -> a.equals(b);

        // XOR operation with duplicate values in the lists
        List<Integer> result = xorWith(comparator, list1, list2);

        // The result should contain the elements [1, 2]
        assertEquals(Arrays.asList(), result); // No unique elements after XOR
    }
}






