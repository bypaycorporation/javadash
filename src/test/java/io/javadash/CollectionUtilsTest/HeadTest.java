package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.head;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;

public class HeadTest {

    @Test
    void testHeadWithNonEmptyList() {
        List<Integer> input = Arrays.asList(1, 2, 3);

        Optional<Integer> result = head(input);

        assertTrue(result.isPresent());
        assertEquals(1, result.get());
    }

    @Test
    void testHeadWithSingleElementList() {
        List<String> input = Collections.singletonList("single");

        Optional<String> result = head(input);

        assertTrue(result.isPresent());
        assertEquals("single", result.get());
    }

    @Test
    void testHeadWithEmptyList() {
        List<Integer> input = Collections.emptyList();

        Optional<Integer> result = head(input);

        assertFalse(result.isPresent());
    }

    @Test
    void testHeadWithNullList() {
        List<Integer> input = null;

        Optional<Integer> result = head(input);

        assertFalse(result.isPresent());
    }

    @Test
    void testHeadWithNullElements() {
        List<String> input = Arrays.asList(null, "second");

        Optional<String> result = head(input);
        System.out.println(result);
        assertFalse(result.isPresent());
    }
}

