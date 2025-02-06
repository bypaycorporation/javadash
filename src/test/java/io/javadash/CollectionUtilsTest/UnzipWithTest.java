package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.unzipWith;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import org.junit.jupiter.api.Test;

public class UnzipWithTest {

    @Test
    void testUnzipWith() {
        List<List<Integer>> grouped = new ArrayList<>();
        grouped.add(new ArrayList<>(Arrays.asList(1, 2, 3)));
        grouped.add(new ArrayList<>(Arrays.asList(4, 5, 6)));
        grouped.add(new ArrayList<>(Arrays.asList(7, 8, 9)));

        // Combine function: sum each group
        Function<List<Integer>, Integer> combineFunction = list -> list.stream().mapToInt(Integer::intValue).sum();

        List<Integer> result = unzipWith(grouped, combineFunction);

        // The result should contain the sum of each group:
        // [1 + 4 + 7, 2 + 5 + 8, 3 + 6 + 9] => [12, 15, 18]
        assertEquals(Arrays.asList(12, 15, 18), result);
    }

    @Test
    void testUnzipWithEmptyList() {
        List<List<Integer>> grouped = new ArrayList<>();

        // Combine function: sum each group
        Function<List<Integer>, Integer> combineFunction = list -> list.stream().mapToInt(Integer::intValue).sum();

        List<Integer> result = unzipWith(grouped, combineFunction);

        // The result should be an empty list because the input is empty
        assertEquals(new ArrayList<>(), result);
    }

    @Test
    void testUnzipWithNullList() {
        List<List<Integer>> grouped = null;

        // Combine function: sum each group
        Function<List<Integer>, Integer> combineFunction = list -> list.stream().mapToInt(Integer::intValue).sum();

        List<Integer> result = unzipWith(grouped, combineFunction);

        // The result should be an empty list because the input is null
        assertEquals(new ArrayList<>(), result);
    }

    @Test
    void testUnzipWithUnevenLists() {
        List<List<Integer>> grouped = new ArrayList<>();
        grouped.add(new ArrayList<>(Arrays.asList(1, 2)));
        grouped.add(new ArrayList<>(Arrays.asList(3, 4, 5)));
        grouped.add(new ArrayList<>(Arrays.asList(6)));

        // Combine function: sum each group
        Function<List<Integer>, Integer> combineFunction = list -> list.stream().mapToInt(Integer::intValue).sum();

        List<Integer> result = unzipWith(grouped, combineFunction);

        // The result should contain the sum of each group:
        // [1 + 3 + 6, 2 + 4 + 0] => [10, 6]
        assertEquals(Arrays.asList(10, 6, 5), result);
    }

    @Test
    void testUnzipWithCustomFunction() {
        List<List<Integer>> grouped = new ArrayList<>();
        grouped.add(new ArrayList<>(Arrays.asList(1, 2, 3)));
        grouped.add(new ArrayList<>(Arrays.asList(4, 5, 6)));
        grouped.add(new ArrayList<>(Arrays.asList(7, 8, 9)));

        // Combine function: get the max value in each group
        Function<List<Integer>, Integer> combineFunction = list -> list.stream().max(Integer::compareTo).orElse(null);

        List<Integer> result = unzipWith(grouped, combineFunction);

        // The result should contain the maximum value of each group:
        assertEquals(Arrays.asList(7, 8, 9), result);
    }
}





