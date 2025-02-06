package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.partition;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import org.junit.jupiter.api.Test;

public class PartitionTest {

    @Test
    void testPartition_validCollection() {
        List<Integer> collection = Arrays.asList(1, 2, 3, 4, 5, 6);

        // Predicate: Even numbers
        Predicate<Integer> isEven = number -> number % 2 == 0;

        List<List<Integer>> result = partition(collection, isEven);

        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Arrays.asList(2, 4, 6));  // Even numbers
        expected.add(Arrays.asList(1, 3, 5));  // Odd numbers

        assertEquals(expected, result);
    }

    @Test
    void testPartition_emptyCollection() {
        List<Integer> collection = Collections.emptyList();

        // Predicate: Even numbers
        Predicate<Integer> isEven = number -> number % 2 == 0;

        List<List<Integer>> result = partition(collection, isEven);

        assertEquals(Collections.emptyList(), result);  // Should return an empty list
    }

    @Test
    void testPartition_nullCollection() {
        List<Integer> collection = null;

        // Predicate: Even numbers
        Predicate<Integer> isEven = number -> number % 2 == 0;

        List<List<Integer>> result = partition(collection, isEven);

        assertEquals(Collections.emptyList(), result);  // Should return an empty list
    }

    @Test
    void testPartition_customPredicate() {
        List<String> collection = Arrays.asList("apple", "banana", "cherry", "date");

        // Predicate: Strings starting with 'b'
        Predicate<String> startsWithB = s -> s.startsWith("b");

        List<List<String>> result = partition(collection, startsWithB);

        List<List<String>> expected = new ArrayList<>();
        expected.add(Arrays.asList("banana"));  // Starts with 'b'
        expected.add(Arrays.asList("apple", "cherry", "date"));  // Does not start with 'b'

        assertEquals(expected, result);
    }

    @Test
    void testPartition_withNullElements() {
        List<String> collection = Arrays.asList("apple", null, "banana", null, "cherry");

        // Predicate: Non-null strings
        Predicate<String> isNonNull = Objects::nonNull;

        List<List<String>> result = partition(collection, isNonNull);

        List<List<String>> expected = new ArrayList<>();
        expected.add(Arrays.asList("apple", "banana", "cherry"));  // Non-null values
        expected.add(Arrays.asList());
        assertEquals(expected, result);
    }

    @Test
    void testPartition_singleElementCollection() {
        List<Integer> collection = Collections.singletonList(5);

        // Predicate: Even numbers
        Predicate<Integer> isEven = number -> number % 2 == 0;

        List<List<Integer>> result = partition(collection, isEven);

        List<List<Integer>> expected = new ArrayList<>();
        expected.add(Collections.emptyList());  // No even numbers
        expected.add(Collections.singletonList(5));  // 5 is odd

        assertEquals(expected, result);
    }

    @Test
    void testPartition_nullPredicate() {
        List<Integer> collection = Arrays.asList(1, 2, 3);

        assertThrows(NullPointerException.class, () -> {
            partition(collection, null);  // Null predicate should throw exception
        });
    }

    @Test
    void testPartition_withStudentClass() {
        // Create some student objects
        Student student1 = new Student("Alice", 20);
        Student student2 = new Student("Bob", 25);
        Student student3 = new Student("Charlie", 18);
        Student student4 = new Student("David", 30);

        // Create a list of students
        List<Student> students = Arrays.asList(student1, student2, student3, student4);

        // Predicate: Students with age >= 21
        Predicate<Student> isAdult = student -> student.getAge() >= 21;

        // Partition the students based on the predicate
        List<List<Student>> result = partition(students, isAdult);

        // Expected result
        List<List<Student>> expected = new ArrayList<>();
        expected.add(Arrays.asList(student2, student4));  // Students aged 21 or older
        expected.add(Arrays.asList(student1, student3));  // Students younger than 21

        // Assert that the partitioning works as expected
        assertEquals(expected, result);
    }

    static class Student {
        private String name;
        private int age;

        public Student(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return name + " (" + age + ")";
        }
    }
}

