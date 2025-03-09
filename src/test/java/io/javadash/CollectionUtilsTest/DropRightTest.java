package io.javadash.CollectionUtilsTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.javadash.CollectionUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;

public class DropRightTest {

    @Test
    public void testDropRight_OneElementDropped() {
        List<Integer> input = Arrays.asList(1, 2, 3);
        List<Integer> result = CollectionUtils.dropRight(input, 1);
        assertEquals(Arrays.asList(1, 2), result, "The last element should be dropped");
    }

    @Test
    public void testDropRight_TwoElementsDropped() {
        List<Integer> input = Arrays.asList(1, 2, 3);
        List<Integer> result = CollectionUtils.dropRight(input, 2);
        assertEquals(Collections.singletonList(1), result, "Two elements should be dropped");
    }

    @Test
    public void testDropRight_MoreThanListSize() {
        List<Integer> input = Arrays.asList(1, 2, 3);
        List<Integer> result = CollectionUtils.dropRight(input, 5);
        assertEquals(Collections.emptyList(), result, "When n > list size, it should return an empty list");
    }

    @Test
    public void testDropRight_ZeroElementsDropped() {
        List<Integer> input = Arrays.asList(1, 2, 3);
        List<Integer> result = CollectionUtils.dropRight(input, 0);
        assertEquals(Arrays.asList(1, 2, 3), result, "When n is 0, the list should remain unchanged");
    }

    @Test
    public void testDropRight_EmptyList() {
        List<Integer> emptyList = Collections.emptyList();
        List<Integer> result = CollectionUtils.dropRight(emptyList, 1);
        assertEquals(Collections.emptyList(), result, "Dropping elements from an empty list should return an empty list");
    }

    @Test
    public void testDropRight_SingleElementList() {
        List<Integer> singleElementList = Collections.singletonList(5);
        List<Integer> result = CollectionUtils.dropRight(singleElementList, 1);
        assertEquals(Collections.emptyList(), result, "Dropping the only element in a list should return an empty list");
    }

    @Test
    public void testDropRight_ListWithNullValues() {
        List<Integer> listWithNull = new ArrayList<>();
        listWithNull.add(1);
        listWithNull.add(null);
        listWithNull.add(3);

        List<Integer> expect = new ArrayList<>();
        expect.add(1);
        expect.add(null);

        List<Integer> result = CollectionUtils.dropRight(listWithNull, 1);

        assertEquals(expect, result, "Dropping the last element should remove it, even if it's null");
    }
}

