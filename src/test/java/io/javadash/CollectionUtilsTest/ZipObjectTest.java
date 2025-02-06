package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.zipObject;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class ZipObjectTest {

    @Test
    void testZipObjectWithEqualLengthLists() {
        List<String> keys = Arrays.asList("a", "b", "c");
        List<Integer> values = Arrays.asList(1, 2, 3);

        Map<String, Integer> result = zipObject(keys, values);

        Map<String, Integer> expected = new HashMap<>();
        expected.put("a", 1);
        expected.put("b", 2);
        expected.put("c", 3);

        assertEquals(expected, result);
    }

    @Test
    void testZipObjectWithExtraKeys() {
        List<String> keys = Arrays.asList("a", "b", "c", "d");
        List<Integer> values = Arrays.asList(1, 2);

        Map<String, Integer> result = zipObject(keys, values);

        Map<String, Integer> expected = new HashMap<>();
        expected.put("a", 1);
        expected.put("b", 2);
        expected.put("c", null);
        expected.put("d", null);

        assertEquals(expected, result);
    }

    @Test
    void testZipObjectWithExtraValues() {
        List<String> keys = Arrays.asList("a", "b");
        List<Integer> values = Arrays.asList(1, 2, 3, 4);

        Map<String, Integer> result = zipObject(keys, values);

        Map<String, Integer> expected = new HashMap<>();
        expected.put("a", 1);
        expected.put("b", 2);

        assertEquals(expected, result);
    }

    @Test
    void testZipObjectWithEmptyKeys() {
        List<String> keys = Arrays.asList();
        List<Integer> values = Arrays.asList(1, 2, 3);

        Map<String, Integer> result = zipObject(keys, values);

        assertTrue(result.isEmpty());
    }

    @Test
    void testZipObjectWithEmptyValues() {
        List<String> keys = Arrays.asList("a", "b", "c");
        List<Integer> values = Arrays.asList();

        Map<String, Integer> result = zipObject(keys, values);

        Map<String, Integer> expected = new HashMap<>();
        expected.put("a", null);
        expected.put("b", null);
        expected.put("c", null);

        assertEquals(expected, result);
    }

    @Test
    void testZipObjectWithNullKeys() {
        List<String> keys = null;
        List<Integer> values = Arrays.asList(1, 2, 3);

        Map<String, Integer> result = zipObject(keys, values);

        assertTrue(result.isEmpty());
    }

    @Test
    void testZipObjectWithNullValues() {
        List<String> keys = Arrays.asList("a", "b", "c");
        List<Integer> values = null;

        Map<String, Integer> result = zipObject(keys, values);

        assertTrue(result.isEmpty());
    }

    @Test
    void testZipObjectWithBothNull() {
        List<String> keys = null;
        List<Integer> values = null;

        Map<String, Integer> result = zipObject(keys, values);

        assertTrue(result.isEmpty());
    }

    @Test
    void testZipObjectWithDuplicateKeys() {
        List<String> keys = Arrays.asList("a", "b", "a");
        List<Integer> values = Arrays.asList(1, 2, 3);

        Map<String, Integer> result = zipObject(keys, values);

        Map<String, Integer> expected = new HashMap<>();
        expected.put("a", 3); // Last value for duplicate key is used
        expected.put("b", 2);

        assertEquals(expected, result);
    }

    @Test
    void testZipObjectWithSinglePair() {
        List<String> keys = Arrays.asList("a");
        List<Integer> values = Arrays.asList(1);

        Map<String, Integer> result = zipObject(keys, values);

        Map<String, Integer> expected = new HashMap<>();
        expected.put("a", 1);

        assertEquals(expected, result);
    }
}







