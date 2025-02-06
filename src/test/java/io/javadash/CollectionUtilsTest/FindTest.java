package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.find;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import org.junit.jupiter.api.Test;

public class FindTest {

    @Test
    void testFind_elementFound() {
        List<String> collection = Arrays.asList("apple", "banana", "cherry");
        Predicate<String> predicate = s -> s.equals("banana");

        Optional<String> result = find(collection, predicate);

        assertTrue(result.isPresent());
        assertEquals("banana", result.get());
    }

    @Test
    void testFind_elementNotFound() {
        List<String> collection = Arrays.asList("apple", "banana", "cherry");
        Predicate<String> predicate = s -> s.equals("orange");

        Optional<String> result = find(collection, predicate);

        assertFalse(result.isPresent());
    }

    @Test
    void testFind_emptyCollection() {
        Collection<String> collection = Collections.emptyList();
        Predicate<String> predicate = s -> s.equals("banana");

        Optional<String> result = find(collection, predicate);

        assertFalse(result.isPresent());
    }

    @Test
    void testFind_nullCollection() {
        Collection<String> collection = null;
        Predicate<String> predicate = s -> s.equals("banana");

        Optional<String> result = find(collection, predicate);

        assertFalse(result.isPresent());
    }

    @Test
    void testFind_nullPredicate() {
        Collection<String> collection = Arrays.asList("apple", "banana", "cherry");

        assertThrows(NullPointerException.class, () -> find(collection, null));
    }
}

