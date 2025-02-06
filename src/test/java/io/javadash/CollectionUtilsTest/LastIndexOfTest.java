package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.lastIndexOf;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class LastIndexOfTest {


    @Test
    void testLastIndexOfWithValidListAndNonExistingValue() {
        List<String> array = Arrays.asList("apple", "banana", "cherry");

        int result = lastIndexOf(array, "orange", array.size() - 1);

        assertEquals(-1, result);
    }

    @Test
    void testLastIndexOfWithNullValue() {
        List<String> array = Arrays.asList("apple", null, "cherry", null);

        int result = lastIndexOf(array, null, array.size() - 1);

        assertEquals(3, result);
    }


    @Test
    void testLastIndexOfWithFromIndexOutOfBoundsHigh() {
        List<String> array = Arrays.asList("apple", "banana", "cherry");

        int result = lastIndexOf(array, "cherry", 10);

        assertEquals(2, result);
    }

    @Test
    void testLastIndexOfWithFromIndexOutOfBoundsLow() {
        List<String> array = Arrays.asList("apple", "banana", "cherry");

        int result = lastIndexOf(array, "apple", -10);

        assertEquals(0, result);
    }

    @Test
    void testLastIndexOfWithNullList() {
        List<String> array = null;

        int result = lastIndexOf(array, "apple", 0);

        assertEquals(-1, result);
    }

    @Test
    void testLastIndexOfWithEmptyList() {
        List<String> array = Arrays.asList();

        int result = lastIndexOf(array, "apple", 0);

        assertEquals(-1, result);
    }
}

