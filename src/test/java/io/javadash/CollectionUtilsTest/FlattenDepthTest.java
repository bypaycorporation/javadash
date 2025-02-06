package io.javadash.CollectionUtilsTest;


import static io.javadash.CollectionUtils.flattenDepth;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;


public class FlattenDepthTest {

    @Test
    public void testFlattenDepthWithDepth1() {
        List<Object> input = Arrays.asList(1, Arrays.asList(2, Arrays.asList(3, Arrays.asList(4)), 5));
        List<Object> expected = Arrays.asList(1, 2, Arrays.asList(3, Arrays.asList(4)), 5);
        assertEquals(expected, flattenDepth(input, 1));
    }

    @Test
    public void testFlattenDepthWithDepth2() {
        List<Object> input = Arrays.asList(1, Arrays.asList(2, Arrays.asList(3, Arrays.asList(4)), 5));
        List<Object> expected = Arrays.asList(1, 2, 3, Arrays.asList(4), 5);
        assertEquals(expected, flattenDepth(input, 2));
    }

    @Test
    public void testFlattenDepthWithDepth3() {
        List<Object> input = Arrays.asList(1, Arrays.asList(2, Arrays.asList(3, Arrays.asList(4)), 5));
        List<Object> expected = Arrays.asList(1, 2, 3, 4, 5);
        assertEquals(expected, flattenDepth(input, 3));
    }

    @Test
    public void testFlattenDepthWithZeroDepth() {
        List<Object> input = Arrays.asList(1, Arrays.asList(2, Arrays.asList(3, Arrays.asList(4)), 5));
        List<Object> expected = input;
        assertEquals(expected, flattenDepth(input, 0));
    }

    @Test
    public void testFlattenDepthWithNegativeDepth() {
        List<Object> input = Arrays.asList(1, Arrays.asList(2, Arrays.asList(3, Arrays.asList(4)), 5));
        List<Object> expected = input;
        assertEquals(expected, flattenDepth(input, -1));
    }

    @Test
    public void testFlattenDepthWithEmptyList() {
        List<Object> input = Collections.emptyList();
        List<Object> expected = Collections.emptyList();
        assertEquals(expected, flattenDepth(input, 2));
    }

    @Test
    public void testFlattenDepthWithNullList() {
        List<Object> input = null;
        List<Object> expected = Collections.emptyList();
        assertEquals(expected, flattenDepth(input, 2));
    }
}

