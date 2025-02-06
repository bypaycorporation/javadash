package io.javadash.CollectionUtilsTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import io.javadash.CollectionUtils;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DropRightWhileTest {
    // Additional test cases if needed
    @Test
    public void testDropRightWhileWithEmptyList() {
        List<Integer> emptyList = Arrays.asList();
        List<Integer> result = CollectionUtils.dropRightWhile(emptyList, num -> num > 3);
        assertTrue(result.isEmpty(), "Result should be empty for an empty list");
    }

    @Test
    public void testDropRightWhileWithValidPredicate() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> result = CollectionUtils.dropRightWhile(numbers, num -> num > 3);
        assertEquals(Arrays.asList(1, 2, 3), result, "List should exclude numbers greater than 3");
    }
}
