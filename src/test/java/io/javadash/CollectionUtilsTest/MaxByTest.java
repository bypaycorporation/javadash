package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.maxBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.Test;

public class MaxByTest {
    @Test
    void testMaxByWithNonEmptyCollection() {
        // Collection of Person objects, comparing by age
        Collection<Person> people = Arrays.asList(
            new Person("Alice", 30),
            new Person("Bob", 40),
            new Person("Charlie", 25)
        );

        Optional<Person> result = maxBy(people, Person::getAge);
        assertTrue(result.isPresent(), "The result should be present.");
        assertEquals("Bob", result.get().getName(), "The person with the maximum age should be Bob.");
    }

    @Test
    void testMaxByWithEmptyCollection() {
        Collection<Person> people = Collections.emptyList();
        Optional<Person> result = maxBy(people, Person::getAge);
        assertFalse(result.isPresent(), "The result should be empty for an empty collection.");
    }

    @Test
    void testMaxByWithSingleElementCollection() {
        Collection<Person> people = Collections.singletonList(new Person("Alice", 30));
        Optional<Person> result = maxBy(people, Person::getAge);
        System.out.println(result);
        assertTrue(result.isPresent(), "The result should be present for a single-element collection.");
        assertEquals("Alice", result.get().getName(), "The only person in the collection should be the result.");
    }

    @Test
    void testMaxByWithEqualValues() {
        Collection<Person> people = Arrays.asList(
            new Person("Alice", 30),
            new Person("Bob", 30),
            new Person("Charlie", 30)
        );

        Optional<Person> result = maxBy(people, person -> person.getAge());
        assertTrue(result.isPresent(), "The result should be present.");
        assertEquals("Alice", result.get().getName(),
            "If ages are equal, the first element in the stream should be selected.");
    }

    @Test
    void testMaxByWithNullCollection() {
        Collection<Person> people = null;
        Optional<Person> result = maxBy(people, Person::getAge);
        assertFalse(result.isPresent(), "The result should be empty for a null collection.");
    }

    // Helper class for testing
    static class Person {
        private final String name;
        private final int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}
