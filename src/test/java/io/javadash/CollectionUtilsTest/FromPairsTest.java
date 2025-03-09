package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.fromPairs;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class FromPairsTest {

    @Test
    void testFromPairsNormalInput() {
        // Given
        List<List<String>> pairs = Arrays.asList(
            Arrays.asList("a", "1"),
            Arrays.asList("b", "2")
        );

        // When
        Map<String, String> result = fromPairs(pairs);

        // Then
        assertEquals(2, result.size());
        assertEquals("1", result.get("a"));
        assertEquals("2", result.get("b"));
    }

    @Test
    void testFromPairsEmptyInput() {
        // Given
        List<List<String>> pairs = Collections.emptyList();

        // When
        Map<String, String> result = fromPairs(pairs);

        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    void testFromPairsNullInput() {
        // Given
        List<List<String>> pairs = null;

        // When
        Map<String, String> result = fromPairs(pairs);

        // Then
        assertTrue(result.isEmpty());
    }

    @Test
    void testFromPairsInvalidPairSize() {
        // Given
        List<List<String>> pairs = Arrays.asList(
            Arrays.asList("a", "1"),
            Collections.singletonList("b")
        );

        // When
        Map<String, String> result = fromPairs(pairs);

        // Then
        assertEquals(1, result.size());
        assertEquals("1", result.get("a"));
    }
}
