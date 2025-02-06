package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.xorBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import org.junit.jupiter.api.Test;

public class XorByTest {

    @Test
    void testXorBy() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(3, 4, 5);
        List<Integer> list3 = Arrays.asList(5, 6, 7);

        // Define the iteratee as a simple identity function (no transformation)
        Function<Integer, Integer> iteratee = value -> value;

        // XOR operation between the lists, considering the iteratee
        List<Integer> result = xorBy(iteratee, list1, list2, list3);

        // The result should contain the elements [1, 2, 4, 6, 7]
        // because these are the elements that are exclusive after applying the iteratee.
        assertEquals(Arrays.asList(1, 2, 4, 6, 7), result);
    }

    @Test
    void testXorByWithDifferentIteratee() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(3, 4, 5);
        List<Integer> list3 = Arrays.asList(5, 6, 7);

        // Define an iteratee that takes the absolute value (in case of negative numbers)
        Function<Integer, Integer> iteratee = value -> Math.abs(value);

        // XOR operation between the lists, considering the iteratee
        List<Integer> result = xorBy(iteratee, list1, list2, list3);

        // The result should contain the elements [1, 2, 4, 6, 7]
        // because applying the iteratee removes elements that are considered duplicates based on the absolute value.
        assertEquals(Arrays.asList(1, 2, 4, 6, 7), result);
    }

    @Test
    void testXorByWithEmptyList() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList();

        // Define the iteratee as a simple identity function (no transformation)
        Function<Integer, Integer> iteratee = value -> value;

        // XOR operation between list1 and an empty list should return list1
        List<Integer> result = xorBy(iteratee, list1, list2);

        assertEquals(Arrays.asList(1, 2, 3), result);
    }

    @Test
    void testXorByWithNullList() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = null;

        // Define the iteratee as a simple identity function (no transformation)
        Function<Integer, Integer> iteratee = value -> value;

        // XOR operation between list1 and null should return list1
        List<Integer> result = xorBy(iteratee, list1, list2);

        assertEquals(Arrays.asList(1, 2, 3), result);
    }

    @Test
    void testXorByWithIdenticalLists() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(1, 2, 3);

        // Define the iteratee as a simple identity function (no transformation)
        Function<Integer, Integer> iteratee = value -> value;

        // XOR operation between two identical lists should return an empty list
        List<Integer> result = xorBy(iteratee, list1, list2);

        assertEquals(Arrays.asList(), result);
    }

    @Test
    void testXorByWithMultipleLists() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(3, 4, 5);
        List<Integer> list3 = Arrays.asList(1, 5, 6);

        // Define the iteratee as a simple identity function (no transformation)
        Function<Integer, Integer> iteratee = value -> value;

        // XOR operation between three lists
        List<Integer> result = xorBy(iteratee, list1, list2, list3);

        // The result should contain the elements [2, 4, 6]
        assertEquals(Arrays.asList(2, 4, 6), result);
    }

    @Test
    void testXorByWithAllIdenticalElements() {
        List<Integer> list1 = Arrays.asList(1, 1, 1);
        List<Integer> list2 = Arrays.asList(1, 1);

        // Define the iteratee as a simple identity function (no transformation)
        Function<Integer, Integer> iteratee = value -> value;

        // XOR operation with identical elements should return an empty list
        List<Integer> result = xorBy(iteratee, list1, list2);

        assertEquals(Arrays.asList(), result);
    }

    @Test
    void testXorByWithEmptyLists() {
        List<Integer> list1 = Arrays.asList();
        List<Integer> list2 = Arrays.asList();

        // Define the iteratee as a simple identity function (no transformation)
        Function<Integer, Integer> iteratee = value -> value;

        // XOR operation with two empty lists should return an empty list
        List<Integer> result = xorBy(iteratee, list1, list2);

        assertEquals(Arrays.asList(), result);
    }
}





