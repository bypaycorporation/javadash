package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.countBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import org.junit.jupiter.api.Test;

public class CountByTest {

    @Test
    public void testCountBy_withValidInput() {
        List<String> input = Arrays.asList("apple", "banana", "apple", "orange", "banana", "apple");
        Function<String, String> keyMapper = Function.identity();

        Map<String, Long> result = countBy(input, keyMapper);

        Map<String, Long> expected = new HashMap<>();
        expected.put("apple", 3L);
        expected.put("banana", 2L);
        expected.put("orange", 1L);

        assertEquals(expected, result);
    }

    @Test
    public void testCountBy_withEmptyList() {
        List<String> input = Collections.emptyList();
        Function<String, String> keyMapper = Function.identity();

        Map<String, Long> result = countBy(input, keyMapper);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testCountBy_withNullList() {
        List<String> input = null;
        Function<String, String> keyMapper = Function.identity();

        Map<String, Long> result = countBy(input, keyMapper);

        assertTrue(result.isEmpty());
    }

    @Test
    public void testCountBy_withCustomKeyMapper() {
        List<String> input = Arrays.asList("apple", "APPLE", "banana", "Banana", "ORANGE");
        Function<String, String> keyMapper = String::toLowerCase;

        Map<String, Long> result = countBy(input, keyMapper);

        Map<String, Long> expected = new HashMap<>();
        expected.put("apple", 2L);
        expected.put("banana", 2L);
        expected.put("orange", 1L);

        assertEquals(expected, result);
    }

    @Test
    public void testCountBy_withNullKeyMapper() {
        List<String> input = Arrays.asList("apple", "banana");

        assertThrows(NullPointerException.class, () -> countBy(input, null));
    }

    @Test
    public void testCountBy_withComplexObjects() {
        class Person {
            final String name;
            final int age;

            Person(String name, int age) {
                this.name = name;
                this.age = age;
            }
        }

        List<Person> input = Arrays.asList(
            new Person("Alice", 30),
            new Person("Bob", 25),
            new Person("Alice", 30),
            new Person("Charlie", 35)
        );

        Function<Person, String> keyMapper = person -> person.name;

        Map<String, Long> result = countBy(input, keyMapper);

        Map<String, Long> expected = new HashMap<>();
        expected.put("Alice", 2L);
        expected.put("Bob", 1L);
        expected.put("Charlie", 1L);

        assertEquals(expected, result);
    }
}

