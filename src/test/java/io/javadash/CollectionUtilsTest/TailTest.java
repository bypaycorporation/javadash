package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.tail;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class TailTest {

    @Test
    void testTailWithNonEmptyList() {
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");
        list.add("cherry");
        list.add("date");

        List<String> tailList = tail(list);

        assertEquals(new ArrayList<>(Arrays.asList("banana", "cherry", "date")), tailList);
    }

    @Test
    void testTailWithSingleElementList() {
        List<String> list = new ArrayList<>();
        list.add("apple");

        List<String> tailList = tail(list);

        assertEquals(new ArrayList<>(), tailList);  // Should return an empty list since there is only one element
    }

    @Test
    void testTailWithEmptyList() {
        List<String> list = new ArrayList<>();

        List<String> tailList = tail(list);

        assertEquals(new ArrayList<>(), tailList);  // Should return an empty list
    }

    @Test
    void testTailWithNullList() {
        List<String> list = null;

        List<String> tailList = tail(list);

        assertEquals(new ArrayList<>(), tailList);  // Should return an empty list
    }

    @Test
    void testTailWithMultipleIdenticalElements() {
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("apple");
        list.add("apple");
        list.add("apple");

        List<String> tailList = tail(list);

        assertEquals(new ArrayList<>(Arrays.asList("apple", "apple", "apple")), tailList);
    }

    @Test
    void testTailWithListOfSizeTwo() {
        List<String> list = new ArrayList<>();
        list.add("apple");
        list.add("banana");

        List<String> tailList = tail(list);

        assertEquals(new ArrayList<>(Arrays.asList("banana")), tailList);  // Should return a list with the second element
    }
}

