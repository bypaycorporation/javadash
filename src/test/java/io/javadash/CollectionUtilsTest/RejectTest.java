package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.reject;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;
import org.junit.jupiter.api.Test;
public class RejectTest {
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
    void testRejectWithStudents() {
        List<Student> students = Arrays.asList(
            new Student("Alice", 90),
            new Student("Bob", 70),
            new Student("Charlie", 85),
            new Student("David", 60)
        );

        // Reject students with a score < 80
        Predicate<Student> lowScorePredicate = student -> student.getScore() < 80;

        List<Student> result = reject(students, lowScorePredicate);

        // Expected: Students with scores >= 80
        List<Student> expected = Arrays.asList(
            new Student("Alice", 90),
            new Student("Charlie", 85)
        );

        assertEquals(expected, result);
    }

    @Test
    void testRejectWithEmptyCollection() {
        List<Student> students = Collections.emptyList();

        // Reject any student
        Predicate<Student> anyPredicate = student -> student.getScore() < 80;

        List<Student> result = reject(students, anyPredicate);

        // Expect an empty list
        assertTrue(result.isEmpty());
    }

    @Test
    void testRejectWithNullValues() {
        List<Student> students = Arrays.asList(
            new Student("Alice", 90),
            null,
            new Student("Charlie", 85),
            null
        );

        // Reject null values
        Predicate<Student> alwaysTruePredicate = student -> true;

        List<Student> result = reject(students, alwaysTruePredicate);

        // Expect only non-null students to remain
        assertTrue(result.isEmpty());
    }

    @Test
    void testRejectWithAllValues() {
        List<Student> students = Arrays.asList(
            new Student("Alice", 90),
            new Student("Bob", 70)
        );

        // Reject all students
        Predicate<Student> alwaysTruePredicate = student -> true;

        List<Student> result = reject(students, alwaysTruePredicate);

        // Expect an empty list
        assertTrue(result.isEmpty());
    }

    @Test
    void testRejectWithSet() {
        Set<Student> students = new LinkedHashSet<>(Arrays.asList(
            new Student("Alice", 90),
            new Student("Bob", 70),
            new Student("Charlie", 85)
        ));

        // Reject students with scores < 85
        Predicate<Student> lowScorePredicate = student -> student.getScore() < 85;

        List<Student> result = reject(students, lowScorePredicate);

        // Expected: Students with scores >= 85
        List<Student> expected = Arrays.asList(
            new Student("Alice", 90),
            new Student("Charlie", 85)
        );

        assertEquals(expected, result);
    }
}
