package io.javadash.CollectionUtilsTest;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static io.javadash.CollectionUtils.union;
import static org.junit.jupiter.api.Assertions.*;
public class UnionTest {
    @Test
    void testUnion_SingleCollection() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> result = union(list1);
        assertEquals(Arrays.asList(1, 2, 3), result);
    }

    @Test
    void testUnion_MultipleCollections() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(3, 4, 5);
        List<Integer> list3 = Arrays.asList(5, 6, 7);
        List<Integer> result = union(list1, list2, list3);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6, 7), result);
    }

    @Test
    void testUnion_EmptyCollections() {
        List<Integer> list1 = Collections.emptyList();
        List<Integer> list2 = Collections.emptyList();
        List<Integer> result = union(list1, list2);
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testUnion_WithNullCollection() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = null;
        List<Integer> list3 = Arrays.asList(4, 5);
        List<Integer> result = union(list1, list2, list3);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), result);
    }

    @Test
    void testUnion_AllNullCollections() {
        List<Integer> result = union(null, null, null);
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testUnion_DuplicateElements() {
        List<Integer> list1 = Arrays.asList(1, 2, 2, 3);
        List<Integer> list2 = Arrays.asList(3, 4, 4, 5);
        List<Integer> result = union(list1, list2);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), result);
    }

    @Test
    void testUnion_InvalidCollections() {
        // Assuming isValidRestList returns false for invalid collections
        List<Integer> result = union(null);
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testUnion_MixedTypes() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<String> list2 = Arrays.asList("a", "b", "c");
        List<Number> list3 = Arrays.asList(4.5, 6.7);
        List<? extends Object> result = union(list1, list2, list3);
        assertEquals(Arrays.asList(1, 2, 3, "a", "b", "c", 4.5, 6.7), result);
    }
}
