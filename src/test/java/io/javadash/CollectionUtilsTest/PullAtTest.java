package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.pullAt;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class PullAtTest {

    @Test
    void testPullAtWithValidIndexes() {
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("cherry");
        list.add("date");

        List<Integer> indexes = new ArrayList<>();
        indexes.add(1);
        indexes.add(3);

        List<String> removedElements = pullAt(list, indexes);

        assertEquals(new ArrayList<>(Arrays.asList("banana", "date")), removedElements);
    }

    @Test
    void testPullAtWithNoMatchingIndexes() {
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("cherry");

        List<Integer> indexes = new ArrayList<>();
        indexes.add(5); // An index out of range

        List<String> removedElements = pullAt(list, indexes);

        assertEquals(new ArrayList<>(), removedElements);
        assertEquals(new ArrayList<>(Arrays.asList("apple", "banana", "cherry")), list);
    }

    @Test
    void testPullAtWithEmptyIndexes() {
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("cherry");

        List<Integer> indexes = new ArrayList<>(); // Empty list

        List<String> removedElements = pullAt(list, indexes);

        assertEquals(new ArrayList<>(), removedElements);
        assertEquals(new ArrayList<>(Arrays.asList("apple", "banana", "cherry")), list);
    }

    @Test
    void testPullAtWithNullList() {
        List<String> list = null;

        List<Integer> indexes = new ArrayList<>();
        indexes.add(0);

        List<String> removedElements = pullAt(list, indexes);

        assertEquals(new ArrayList<>(), removedElements);
    }

    @Test
    void testPullAtWithNullIndexes() {
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");

        List<Integer> indexes = null; // Null indexes

        List<String> removedElements = pullAt(list, indexes);

        assertEquals(new ArrayList<>(), removedElements);
        assertEquals(new ArrayList<>(Arrays.asList("apple", "banana")), list);
    }

    @Test
    void testPullAtWithMultipleIndexes() {
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("cherry");
        list.add("date");

        List<Integer> indexes = new ArrayList<>();
        indexes.add(0);
        indexes.add(2);

        List<String> removedElements = pullAt(list, indexes);

        assertEquals(new ArrayList<>(Arrays.asList("apple", "cherry")), removedElements);
    }

    @Test
    void testPullAtWithIndexesInDescendingOrder() {
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("cherry");
        list.add("date");

        List<Integer> indexes = new ArrayList<>();
        indexes.add(3);
        indexes.add(1);

        List<String> removedElements = pullAt(list, indexes);

        assertEquals(new ArrayList<>(Arrays.asList("banana", "date")), removedElements);
    }
}

