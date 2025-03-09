package io.javadash.CollectionUtilsTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.javadash.CollectionUtils;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ConcatTest {
    @Test
    public void testConcatWithMultipleLists() {
        List<Integer> array = Collections.singletonList(1);
        List<Integer> result = CollectionUtils.concat(array, Collections.singletonList(2), Arrays.asList(3, 4, null),
            Collections.singletonList(5));
        System.out.println(result);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), result);
    }

    @Test
    public void testConcatWithNullArray() {
        List<Integer> result = CollectionUtils.concat(null, Arrays.asList(1, 2, 3));
        assertEquals(Arrays.asList(1, 2, 3), result);
    }

    @Test
    public void testConcatWithNullValues() {
        List<Integer> array = Collections.singletonList(1);
        List<Integer> result = CollectionUtils.concat(array, null, Collections.singletonList(2), null, Collections.singletonList(3));
        assertEquals(Arrays.asList(1, 2, 3), result);
    }

    @Test
    public void testConcatWithEmptyList() {
        List<Integer> array = Collections.singletonList(1);
        List<Integer> result = CollectionUtils.concat(array, Collections.emptyList());
        assertEquals(Collections.singletonList(1), result);
    }

    @Test
    public void testConcatWithNullElementInList() {
        List<Integer> array = Collections.singletonList(1);
        List<Integer> result = CollectionUtils.concat(array, Collections.singletonList(2), null, Collections.singletonList(3));
        assertEquals(Arrays.asList(1, 2, 3), result);
    }

    @Test
    public void testConcatWithEmptyInitialList() {
        List<Integer> result = CollectionUtils.concat(Collections.emptyList(), Arrays.asList(1, 2, 3));
        assertEquals(Arrays.asList(1, 2, 3), result);
    }

    @Test
    public void testConcatWithOnlyNullValues() {
        List<Integer> result = CollectionUtils.concat(Collections.singletonList(1), null, null, null);
        assertEquals(Collections.singletonList(1), result);
    }

    @Test
    public void testConcatWithNullListInMiddle() {
        List<Integer> array = Arrays.asList(1, 2);
        List<Integer> result = CollectionUtils.concat(array, Arrays.asList(3, 4), null, Arrays.asList(5, 6));
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6), result);
    }
}
