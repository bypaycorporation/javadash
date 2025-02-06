package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.chunk;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ChunkTest {

    @Test
    void testChunkWithValidSize() {
        List<String> input = Arrays.asList("a", "b", "c", "d");
        int size = 2;

        List<List<String>> result = chunk(input, size);

        assertEquals(2, result.size());
        assertEquals(Arrays.asList("a", "b"), result.get(0));
        assertEquals(Arrays.asList("c", "d"), result.get(1));
    }

    @Test
    void testChunkWithSmallerSize() {
        List<String> input = Arrays.asList("a", "b", "c", "d");
        int size = 3;

        List<List<String>> result = chunk(input, size);

        assertEquals(2, result.size());
        assertEquals(Arrays.asList("a", "b", "c"), result.get(0));
        assertEquals(Collections.singletonList("d"), result.get(1));
    }

    @Test
    void testChunkWithLargerSize() {
        List<String> input = Arrays.asList("a", "b");
        int size = 5;

        List<List<String>> result = chunk(input, size);

        assertEquals(1, result.size());
        assertEquals(Arrays.asList("a", "b"), result.get(0));
    }

    @Test
    void testChunkWithSizeZero() {
        List<String> input = Arrays.asList("a", "b");
        int size = 0;

        List<List<String>> result = chunk(input, size);

        assertTrue(result.isEmpty());
    }

    @Test
    void testChunkWithNegativeSize() {
        List<String> input = Arrays.asList("a", "b");
        int size = -1;

        List<List<String>> result = chunk(input, size);

        assertTrue(result.isEmpty());
    }

    @Test
    void testChunkWithEmptyList() {
        List<String> input = Collections.emptyList();
        int size = 2;

        List<List<String>> result = chunk(input, size);

        assertTrue(result.isEmpty());
    }

    @Test
    void testChunkWithNullList() {
        List<String> input = null;
        int size = 2;

        List<List<String>> result = chunk(input, size);

        assertTrue(result.isEmpty());
    }
}

