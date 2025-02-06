package io.javadash.CollectionUtilsTest;


import static io.javadash.CollectionUtils.flatten;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class FlattenTest {

    @Test
    public void testFlattenWithNestedLists() {
        List<Object> input = Arrays.asList(1, Arrays.asList(2, Arrays.asList(3, Arrays.asList(4)), 5));
        List<Object> expected = Arrays.asList(1, 2, Arrays.asList(3, Arrays.asList(4)), 5);
        assertEquals(expected, flatten(input));
    }

    @Test
    public void testFlattenWithEmptyList() {
        List<Object> input = Collections.emptyList();
        List<Object> expected = Collections.emptyList();
        assertEquals(expected, flatten(input));
    }

    @Test
    public void testFlattenWithNull() {
        List<Object> input = null;
        List<Object> expected = Collections.emptyList();
        assertEquals(expected, flatten(input));
    }

    @Test
    public void testFlattenWithSingleLevelList() {
        List<Object> input = Arrays.asList(1, 2, 3, 4, 5);
        List<Object> expected = Arrays.asList(1, 2, 3, 4, 5);
        assertEquals(expected, flatten(input));
    }

    @Test
    public void testFlattenWithMixedContent() {
        List<Object> input = Arrays.asList(1, Arrays.asList(2, 3), 4, Arrays.asList(5, 6));
        List<Object> expected = Arrays.asList(1, 2, 3, 4, 5, 6);
        assertEquals(expected, flatten(input));
    }

    @Test
    public void testFlattenWithNullValue() {
        List<Object> input = Arrays.asList(1, Arrays.asList(2, 3, null), 4, null, Arrays.asList(5, 6));
        List<Object> expected = Arrays.asList(1, 2, 3, null, 4, null, 5, 6);
        assertEquals(expected, flatten(input));
    }
}

