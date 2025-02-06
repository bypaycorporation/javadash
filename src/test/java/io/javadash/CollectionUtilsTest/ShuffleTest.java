package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.shuffle;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.junit.jupiter.api.Test;

class ShuffleTest {

    // Sample Student class for testing
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
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
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

    @Test
    void testShuffleWithStudents() {
        List<Student> students = Arrays.asList(
            new Student("Alice", 90),
            new Student("Bob", 80),
            new Student("Charlie", 70),
            new Student("David", 60)
        );

        List<Student> shuffledStudents = shuffle(students);

        assertEquals(students.size(), shuffledStudents.size(), "Shuffled list should have the same size as the original list.");
        assertTrue(students.containsAll(shuffledStudents), "Shuffled list should contain all original elements.");

        // Check if the order is different (not guaranteed but highly likely)
        assertNotEquals(students, shuffledStudents, "Shuffled list should not be in the same order as the original.");
    }

    @Test
    void testShuffleWithEmptyList() {
        List<Object> emptyList = Collections.emptyList();

        List<Object> shuffled = shuffle(emptyList);
        assertTrue(shuffled.isEmpty(), "Shuffled list should be empty for an empty collection.");
    }

    @Test
    void testShuffleWithSingleElement() {
        List<Integer> singleElementList = Collections.singletonList(5);

        List<Integer> shuffled = shuffle(singleElementList);
        assertEquals(1, shuffled.size(), "Shuffled list should contain 1 element.");
        assertEquals(singleElementList, shuffled, "Shuffling a single-element list should not change the list.");
    }

    @Test
    void testShuffleWithNullCollection() {
        List<Object> nullCollection = null;

        List<Object> shuffledNull = shuffle(nullCollection);
        assertTrue(shuffledNull.isEmpty(), "Shuffling a null collection should return an empty list.");
    }

    @Test
    void testShuffleWithListOfIntegers() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> shuffled = shuffle(numbers);

        assertEquals(numbers.size(), shuffled.size(), "Shuffled list should have the same size as the original list.");
        assertTrue(numbers.containsAll(shuffled), "Shuffled list should contain all original elements.");

        // Check if the order is different
        assertNotEquals(numbers, shuffled, "Shuffled list should not be in the same order as the original.");
    }

    @Test
    void testShuffleWithListContainingNulls() {
        List<Integer> numbersWithNulls = Arrays.asList(1, null, 2, null, 3);

        List<Integer> shuffled = shuffle(numbersWithNulls);

        assertEquals(numbersWithNulls.size(), shuffled.size(), "Shuffled list should have the same size as the original list.");
        assertTrue(numbersWithNulls.containsAll(shuffled), "Shuffled list should contain all original elements.");
    }
}

