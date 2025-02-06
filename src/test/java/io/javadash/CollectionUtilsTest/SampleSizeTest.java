package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.sampleSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;

class SampleSizeTest {
    @Test
    void testSampleSizeWithStudents() {
        List<Student> students = Arrays.asList(
            new Student("Alice", 90),
            new Student("Bob", 80),
            new Student("Charlie", 70),
            new Student("David", 60)
        );

        List<Student> sampled = sampleSize(students, 2);
        assertEquals(2, sampled.size(), "Sampled list should contain 2 students.");
        assertTrue(students.containsAll(sampled), "Sampled students should be from the original list.");
    }

    @Test
    void testSampleSizeWithIntegers() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> sampled = sampleSize(numbers, 3);
        assertEquals(3, sampled.size(), "Sampled list should contain 3 integers.");
        assertTrue(numbers.containsAll(sampled), "Sampled numbers should be from the original list.");
    }

    @Test
    void testSampleSizeWithMoreThanAvailable() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        List<Integer> sampled = sampleSize(numbers, 5);
        assertEquals(3, sampled.size(), "Sampled list should not exceed the original collection size.");
    }

    @Test
    void testSampleSizeWithNegativeSize() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        List<Integer> sampled = sampleSize(numbers, -1);
        assertEquals(1, sampled.size(), "Sample size should be at least 1.");
    }

    @Test
    void testSampleSizeWithEmptyCollection() {
        List<Object> emptyList = Collections.emptyList();

        List<Object> sampledEmpty = sampleSize(emptyList, 2);
        assertTrue(sampledEmpty.isEmpty(), "Sampled result should be empty for an empty collection.");
    }

    @Test
    void testSampleSizeWithNullCollection() {
        Collection<Object> nullCollection = null;

        List<Object> sampledNull = sampleSize(nullCollection, 2);
        assertTrue(sampledNull.isEmpty(), "Sampled result should be empty for a null collection.");
    }

    @Test
    void testSampleSizeWithSizeOne() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> sampled = sampleSize(numbers, 1);
        assertEquals(1, sampled.size(), "Sampled list should contain exactly 1 element.");
        assertTrue(numbers.contains(sampled.get(0)), "Sampled element should be from the original list.");
    }

    @Test
    void testSampleSizeWithZero() {
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        List<Integer> sampled = sampleSize(numbers, 0);
        assertEquals(1, sampled.size(), "Sample size should be at least 1.");
    }

    // Sample Student class
    static class Student {
        private String name;
        private int score;

        public Student(String name, int score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public int getScore() {
            return score;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Student student = (Student) o;
            return score == student.score && Objects.equals(name, student.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, score);
        }

        @Override
        public String toString() {
            return "Student{name='" + name + "', score=" + score + '}';
        }
    }
}

