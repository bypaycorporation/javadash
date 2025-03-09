package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.intersection;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class IntersectionTest {

    @Test
    public void testIntersection_ValidArrays() {
        List<Integer> array1 = Arrays.asList(2, 1);
        List<Integer> array2 = Arrays.asList(2, 3);
        List<Integer> expected = Collections.singletonList(2);

        List<Integer> result = intersection(array1, array2);

        assertEquals(expected, result, "The intersection should return [2]");
    }

    @Test
    public void testIntersection_NoCommonElements() {
        List<Integer> array1 = Arrays.asList(1, 2, 3);
        List<Integer> array2 = Arrays.asList(4, 5, 6);
        List<Integer> expected = Collections.emptyList();

        List<Integer> result = intersection(array1, array2);

        assertEquals(expected, result, "The intersection should return an empty list");
    }

    @Test
    public void testIntersection_MultipleArrays() {
        List<Integer> array1 = Arrays.asList(1, 2, 3, 4 , null);
        List<Integer> array2 = Arrays.asList(2, 3, 5);
        List<Integer> array3 = Arrays.asList(3, 6);
        List<Integer> expected = Collections.singletonList(3);

        List<Integer> result = intersection(array1, array2, array3);

        assertEquals(expected, result, "The intersection should return [3]");
    }

    @Test
    public void testIntersection_EmptyInput() {
        List<Integer> array1 = Collections.emptyList();
        List<Integer> array2 = Arrays.asList(2, 3);
        List<Integer> expected = Collections.emptyList();

        List<Integer> result = intersection(array1, array2);

        assertEquals(expected, result, "The intersection of an empty array should return an empty list");
    }

    @Test
    public void testIntersection_SingleElement() {
        List<Integer> array1 = Collections.singletonList(5);
        List<Integer> array2 = Collections.singletonList(5);
        List<Integer> expected = Collections.singletonList(5);

        List<Integer> result = intersection(array1, array2);

        assertEquals(expected, result, "The intersection of [5] and [5] should return [5]");
    }

    @Test
    public void testIntersection_NullArray() {
        List<Integer> array1 = null;
        List<Integer> array2 = Arrays.asList(2, null, 3);
        List<Integer> expected = Collections.emptyList();

        List<Integer> result = intersection(array1, array2);

        assertEquals(expected, result);
    }

    @Test
    public void testIntersection_NullArray2() {
        List<Integer> array1 = Arrays.asList(2, null, 3);
        List<Integer> array2 = Arrays.asList(2, null, 3);
        List<Integer> array3 = null;
        List<Integer> expected = Collections.emptyList();

        List<Integer> result = intersection(array1, array2, array3);

        assertEquals(expected, result);
    }
}

