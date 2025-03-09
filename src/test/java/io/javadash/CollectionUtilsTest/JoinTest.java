package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.join;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class JoinTest {

    @Test
    void testJoinWithValidListAndSeparator() {
        List<String> array = Arrays.asList("apple", "banana", "cherry");
        String separator = "-";

        String result = join(array, separator);

        assertEquals("apple-banana-cherry", result);
    }

    @Test
    void testJoinWithValidListAndNullSeparator() {
        List<String> array = Arrays.asList("apple", "banana", "cherry");

        String result = join(array, null);

        assertEquals("apple,banana,cherry", result);
    }

    @Test
    void testJoinWithValidListAndEmptySeparator() {
        List<String> array = Arrays.asList("apple", "banana", "cherry");

        String result = join(array, "");

        assertEquals("apple,banana,cherry", result);
    }

    @Test
    void testJoinWithNullList() {
        List<String> array = null;
        String separator = "-";

        String result = join(array, separator);

        assertEquals("", result);
    }

    @Test
    void testJoinWithEmptyList() {
        List<String> array = Collections.emptyList();
        String separator = "-";

        String result = join(array, separator);

        assertEquals("", result);
    }

    @Test
    void testJoinWithNullElementsInList() {
        List<String> array = Arrays.asList("apple", null, "cherry");
        String separator = "-";

        String result = join(array, separator);

        assertEquals("apple-cherry", result);
    }

    @Test
    void testJoinWithSingleElementList() {
        List<Integer> array = Arrays.asList(1,2);
        String separator = "-";

        String result = join(array, separator);

        assertEquals("1-2", result);
    }
}

