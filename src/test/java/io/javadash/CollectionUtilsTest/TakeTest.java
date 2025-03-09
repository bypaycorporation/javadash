package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.take;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class TakeTest {

    @Test
    void testTakeWithValidList() {
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("cherry");
        list.add("date");

        List<String> takenList = take(list, 2);  // Using the take method directly

        assertEquals(new ArrayList<>(Arrays.asList("apple", "banana")), takenList); // Should return the first 2 elements
    }

    @Test
    void testTakeWithSingleElementList() {
        List<String> list = new ArrayList<>();
        list.add("apple");

        List<String> takenList = take(list, 3);  // Using the take method directly

        assertEquals(Collections.singletonList("apple"), takenList);  // Should return the only element in the list
    }

    @Test
    void testTakeWithEmptyList() {
        List<String> list = new ArrayList<>();

        List<String> takenList = take(list, 3);  // Using the take method directly

        assertEquals(new ArrayList<>(), takenList);  // Should return an empty list
    }

    @Test
    void testTakeWithNullList() {
        List<String> list = null;

        List<String> takenList = take(list, 2);  // Using the take method directly

        assertEquals(new ArrayList<>(), takenList);  // Should return an empty list
    }

    @Test
    void testTakeWithNegativeN() {
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("cherry");

        List<String> takenList = take(list, -1);  // Using the take method directly

        assertEquals(new ArrayList<>(), takenList);  // Should return an empty list when n is negative
    }

    @Test
    void testTakeWithNGreaterThanListSize() {
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");

        List<String> takenList = take(list, 5);  // Using the take method directly

        assertEquals(new ArrayList<>(Arrays.asList("apple", "banana")), takenList);  // Should return the whole list as n exceeds the list size
    }
}

