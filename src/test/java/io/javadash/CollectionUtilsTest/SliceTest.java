package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.slice;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class SliceTest {

    @Test
    void testSlice_ValidStartIndex() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> result = slice(numbers, 2);
        assertEquals(Arrays.asList(3, 4, 5), result);
    }

    @Test
    void testSlice_StartIndexZero() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> result = slice(numbers, 0);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), result);
    }

    @Test
    void testSlice_StartIndexGreaterThanSize() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> result = slice(numbers, 10);
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testSlice_NegativeStartIndex() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> result = slice(numbers, -3);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), result);
    }

    @Test
    void testSlice_EmptyCollection() {
        List<Integer> emptyList = Collections.emptyList();
        List<Integer> result = slice(emptyList, 2);
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testSlice_NonListCollection() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> result = slice(numbers, 3);
        assertEquals(Arrays.asList(4, 5), result);
    }

    @Test
    void testSlice_InvalidCollection() {
        // Assuming isValidList returns false for null or invalid collections
        List<Integer> result = slice(null, 2);
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testSliceWithValidStartAndEnd() {
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("cherry");
        list.add("date");

        List<String> slicedList = slice(list, 1, 3);

        assertEquals(new ArrayList<>(Arrays.asList("banana", "cherry")), slicedList);
    }

    @Test
    void testSliceWithStartGreaterThanEnd() {
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("cherry");
        list.add("date");

        List<String> slicedList = slice(list, 3, 1);

        assertEquals(new ArrayList<>(), slicedList);  // The result should be an empty list
    }

    @Test
    void testSliceWithOutOfBoundStart() {
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("cherry");
        list.add("date");

        List<String> slicedList = slice(list, -1, 2);

        assertEquals(new ArrayList<>(Arrays.asList("apple", "banana")), slicedList);
    }

    @Test
    void testSliceWithOutOfBoundEnd() {
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("cherry");
        list.add("date");

        List<String> slicedList = slice(list, 1, 10);

        assertEquals(new ArrayList<>(Arrays.asList("banana", "cherry", "date")), slicedList);
    }

    @Test
    void testSliceWithEmptyList() {
        List<String> list = new ArrayList<>();

        List<String> slicedList = slice(list, 0, 2);

        assertEquals(new ArrayList<>(), slicedList);
    }

    @Test
    void testSliceWithSingleElement() {
        List<String> list = new ArrayList<>();
        list.add("apple");

        List<String> slicedList = slice(list, 0, 1);

        assertEquals(new ArrayList<>(Arrays.asList("apple")), slicedList);
    }

    @Test
    void testSliceWithStartEqualEnd() {
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("cherry");
        list.add("date");

        List<String> slicedList = slice(list, 2, 2);

        assertEquals(new ArrayList<>(), slicedList);  // Start and end are the same, should return empty list
    }
}

