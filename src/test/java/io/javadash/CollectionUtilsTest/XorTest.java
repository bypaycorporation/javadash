package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.xor;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class XorTest {

    @Test
    void testXor() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(3, 4, 5);
        List<Integer> list3 = Arrays.asList(5, 6, 7);

        // XOR operation between the lists
        List<Integer> result = xor(list1, list2, list3);

        // The result should contain the elements [1, 2, 4, 6, 7]
        // because they are present in only one list each (exclusive OR).
        assertEquals(Arrays.asList(1, 2, 4, 6, 7), result);
    }

    @Test
    void testXorWithEmptyList() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList();

        // XOR operation between list1 and an empty list should return list1
        List<Integer> result = xor(list1, list2);

        assertEquals(Arrays.asList(1, 2, 3), result);
    }

    @Test
    void testXorWithNullList() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = null;

        // XOR operation between list1 and null should return list1
        List<Integer> result = xor(list1, list2);

        assertEquals(Arrays.asList(1, 2, 3), result);
    }

    @Test
    void testXorWithIdenticalLists() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(1, 2, 3);

        // XOR operation between two identical lists should return an empty list
        List<Integer> result = xor(list1, list2);

        assertEquals(Arrays.asList(), result);
    }

    @Test
    void testXorWithMultipleLists() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(3, 4, 5);
        List<Integer> list3 = Arrays.asList(1, 5, 6);

        // XOR operation between three lists
        List<Integer> result = xor(list1, list2, list3);

        // The result should contain the elements [2, 4, 6]
        assertEquals(Arrays.asList(2, 4, 6), result);
    }

    @Test
    void testXorWithAllIdenticalElements() {
        List<Integer> list1 = Arrays.asList(1, 1, 1);
        List<Integer> list2 = Arrays.asList(1, 1);

        // XOR operation with identical elements should return an empty list
        List<Integer> result = xor(list1, list2);

        assertEquals(Arrays.asList(), result);
    }

    @Test
    void testXorWithEmptyLists() {
        List<Integer> list1 = Arrays.asList();
        List<Integer> list2 = Arrays.asList();

        // XOR operation with two empty lists should return an empty list
        List<Integer> result = xor(list1, list2);

        assertEquals(Arrays.asList(), result);
    }
}





