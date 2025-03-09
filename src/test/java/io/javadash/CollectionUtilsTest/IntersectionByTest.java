package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.intersectionBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;
import org.junit.jupiter.api.Test;

public class IntersectionByTest {

    @Test
    public void testIntersectionByWithEvenNumbers() {
        // Define input lists
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4);
        List<Integer> list2 = Arrays.asList(2, 4, 6, 8);
        List<Integer> list3 = Arrays.asList(4, 5, 6);

        // Predicate to check even numbers
        Predicate<Integer> isEven = num -> num % 2 == 0;

        // Call the method and store the result
        List<Integer> result = intersectionBy(list1, isEven, list2, list3);

        // Assert the result
        assertEquals(Collections.singletonList(4), result);
    }

    @Test
    public void testIntersectionByWithStringLengthEven() {
        // Define input lists of strings
        List<String> list1 = Arrays.asList("apple", "banana", "cherry", "date");
        List<String> list2 = Arrays.asList("banana", "cherry", "date", "fig");

        // Predicate to check if string length is even
        Predicate<String> hasEvenLength = str -> str.length() % 2 == 0;

        // Call the method and store the result
        List<String> result = intersectionBy(list1, hasEvenLength, list2);

        // Assert the result
        assertEquals(Arrays.asList("banana", "cherry", "date"), result);
    }

    @Test
    public void testIntersectionByWithNoIntersection() {
        // Define input lists
        List<Integer> list1 = Arrays.asList(1, 3, 5);
        List<Integer> list2 = Arrays.asList(2, 4, 6);

        // Predicate to check even numbers
        Predicate<Integer> isEven = num -> num % 2 == 0;

        // Call the method and store the result
        List<Integer> result = intersectionBy(list1, isEven, list2);

        // Assert the result (No intersection for even numbers)
        assertTrue(result.isEmpty());
    }

    @Test
    public void testIntersectionByWithEmptyList() {
        // Define input lists, one of them being empty
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Collections.emptyList();

        // Predicate to check even numbers
        Predicate<Integer> isEven = num -> num % 2 == 0;

        // Call the method and store the result
        List<Integer> result = intersectionBy(list1, isEven, list2);

        // Assert the result (empty result as the second list is empty)
        assertTrue(result.isEmpty());
    }

    @Test
    public void testIntersectionByWithMultipleArrays() {
        // Define input lists
        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(4, 5, 6, 7);
        List<Integer> list3 = Arrays.asList(3, 4, 5, 8);

        // Predicate to check if number is greater than 2
        Predicate<Integer> greaterThanTwo = num -> num > 2;

        // Call the method and store the result
        List<Integer> result = intersectionBy(list1, greaterThanTwo, list2, list3);

        // Assert the result
        assertEquals(Arrays.asList(4, 5), result);
    }

    @Test
    public void testIntersectionByNullArrays() {
        // Define input lists
        List<Integer> list1 = Arrays.asList(1, 2, 3, null, 4, 5);
        List<Integer> list2 = Arrays.asList(4, 5, 6, 7);
        List<Integer> list3 = Arrays.asList(3, 4, 5, 8);
        List<Integer> list4 = null;

        // Predicate to check if number is greater than 2
        Predicate<Integer> greaterThanTwo = num -> num > 2;

        // Call the method and store the result
        List<Integer> result = intersectionBy(list1, greaterThanTwo, list2, list3, list4);

        // Assert the result
        assertEquals(Collections.emptyList(), result);
    }
}

