package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.zipWith;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class ZipWithTest {

    @Test
    void testZipWithEqualSizedLists() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);

        List<String> result = zipWith(
            group -> group.get(0) + "+" + group.get(1),
            list1, list2
        );

        assertEquals(Arrays.asList("1+4", "2+5", "3+6"), result);
    }

    @Test
    void testZipWithDifferentSizedLists() {
        List<Integer> list1 = Arrays.asList(1, 2);
        List<Integer> list2 = Arrays.asList(3, 4, 5);

        List<String> result = zipWith(
            group -> group.get(0) + "+" + group.get(1),
            list1, list2
        );

        assertEquals(Arrays.asList("1+3", "2+4"), result);
    }

    @Test
    void testZipWithEmptyLists() {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        List<String> result = zipWith(
            group -> group.get(0) + "+" + group.get(1),
            list1, list2
        );

        assertTrue(result.isEmpty());
    }

    @Test
    void testZipWithNullCombiner() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(4, 5, 6);

        assertThrows(NullPointerException.class, () ->
            zipWith(null, list1, list2)
        );
    }

    @Test
    void testZipWithNullLists() {
        List<Integer> list1 = null;
        List<Integer> list2 = Arrays.asList(4, 5, 6);

        List<String> result = zipWith(
            group -> group.get(0) + "+" + group.get(1),
            list1, list2
        );

        assertTrue(result.isEmpty());
    }

    @Test
    void testZipWithSingleList() {
        List<Integer> list = Arrays.asList(1, 2, 3);

        List<Integer> result = zipWith(
            group -> group.get(0) * 2,
            list
        );

        assertEquals(Arrays.asList(), result);
    }

    @Test
    void testZipWithMixedNullAndEmptyLists() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = null;
        List<Integer> list3 = Arrays.asList();

        List<String> result = zipWith(
            group -> group.get(0) + (group.size() > 1 ? "+" + group.get(1) : ""),
            list1, list2, list3
        );

        assertTrue(result.isEmpty());
    }
}





