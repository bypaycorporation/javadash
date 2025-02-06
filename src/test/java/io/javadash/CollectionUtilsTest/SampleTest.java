package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.sample;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.junit.jupiter.api.Test;

public class SampleTest {

    @Test
    void testSampleWithStudents() {
        List<Student> students = Arrays.asList(
            new Student("Alice", 90),
            new Student("Bob", 80),
            new Student("Charlie", 70)
        );

        Optional<Student> sampledStudent = sample(students);
        assertTrue(sampledStudent.isPresent(), "Sampled student should be present");
        assertTrue(students.contains(sampledStudent.get()), "Sampled student should be in the original collection");
    }

    @Test
    void testSampleWithIntegers() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        Optional<Integer> sampledNumber = sample(numbers);
        assertTrue(sampledNumber.isPresent(), "Sampled number should be present");
        assertTrue(numbers.contains(sampledNumber.get()), "Sampled number should be in the original collection");
    }

    @Test
    void testSampleWithStrings() {
        List<String> strings = Arrays.asList("A", "B", "C");

        Optional<String> sampledString = sample(strings);
        assertTrue(sampledString.isPresent(), "Sampled string should be present");
        assertTrue(strings.contains(sampledString.get()), "Sampled string should be in the original collection");
    }

    @Test
    void testSampleWithEmptyCollection() {
        List<Object> emptyList = Collections.emptyList();

        Optional<Object> sampledEmpty = sample(emptyList);
        assertFalse(sampledEmpty.isPresent(), "Sampled result should be empty for an empty collection");
    }

    @Test
    void testSampleWithMixedTypes() {
        List<Object> mixedList = Arrays.asList(1, "Two", new Student("Charlie", 70), 4.0);

        Optional<Object> sampledMixed = sample(mixedList);
        assertTrue(sampledMixed.isPresent(), "Sampled object should be present");
        assertTrue(mixedList.contains(sampledMixed.get()), "Sampled object should be in the original collection");
    }

    @Test
    void testSampleWithNullCollection() {
        Collection<Object> nullCollection = null;

        Optional<Object> sampledNull = sample(nullCollection);
        assertFalse(sampledNull.isPresent(), "Sampled result should be empty for a null collection");
    }

    // Sample Student class
    static class Student {
        private final String name;
        private final int score;

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
