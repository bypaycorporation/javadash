package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.flattenDeep;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class FlattenDeepTest {

    @Test
    public void testFlattenDeep_withNestedList() {
        List<Object> nestedList = Arrays.asList(1, Arrays.asList(2, Arrays.asList(3, Arrays.asList(4)), 5));
        List<Object> expected = Arrays.asList(1, 2, 3, 4, 5);
        List<Object> result = flattenDeep(nestedList);
        assertEquals(expected, result);
    }

    @Test
    public void testFlattenDeep_withEmptyList() {
        List<Object> emptyList = Collections.emptyList();
        List<Object> expected = Collections.emptyList();
        List<Object> result = flattenDeep(emptyList);
        assertEquals(expected, result);
    }

    @Test
    public void testFlattenDeep_withNull() {
        List<Object> result = flattenDeep(null);
        List<Object> expected = Collections.emptyList();
        assertEquals(expected, result);
    }

    @Test
    public void testFlattenDeep_withFlatList() {
        List<Object> flatList = Arrays.asList(1, 2, 3, 4, 5);
        List<Object> expected = Arrays.asList(1, 2, 3, 4, 5);
        List<Object> result = flattenDeep(flatList);
        assertEquals(expected, result);
    }

    @Test
    public void testFlattenDeep_withMixedTypes() {
        List<Object> mixedList = Arrays.asList(1, "string", Arrays.asList(2.5, Arrays.asList(true, null)));
        List<Object> expected = Arrays.asList(1, "string", 2.5, true);
        List<Object> result = flattenDeep(mixedList);
        assertEquals(expected, result);
    }
}

