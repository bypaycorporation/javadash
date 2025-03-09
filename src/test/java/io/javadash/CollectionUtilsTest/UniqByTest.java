package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.uniqBy;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import org.junit.jupiter.api.Test;

public class UniqByTest {

    @Test
    void testUniqBy() {
        List<Integer> collection = Arrays.asList(1, 2, 2, 3, 3, 3);
        List<Integer> result = uniqBy(collection, value -> value);

        assertEquals(Arrays.asList(1, 2, 3), result);
    }

    @Test
    void testUniqByWithIteratee() {
        List<Person> list = new ArrayList<>();
        list.add(new Person("Alice", 30));
        list.add(new Person("Bob", 25));
        list.add(new Person("Alice", 30));  // Duplicate based on name

        Function<Person, String> byName = Person::getName;  // Function to extract name for comparison

        List<Person> result = uniqBy(list, byName);

        // The result should contain only one instance of "Alice" and "Bob"
        assertEquals(new ArrayList<>(Arrays.asList(new Person("Alice", 30), new Person("Bob", 25))), result);
    }

    @Test
    void testUniqByWithEmptyList() {
        List<Person> list = new ArrayList<>();

        Function<Person, String> byName = Person::getName;  // Function to extract name for comparison

        List<Person> result = uniqBy(list, byName);

        assertEquals(new ArrayList<>(), result);  // Should return an empty list
    }

    @Test
    void testUniqByWithNullList() {
        List<Person> list = null;

        Function<Person, String> byName = Person::getName;  // Function to extract name for comparison

        List<Person> result = uniqBy(list, byName);

        assertEquals(new ArrayList<>(), result);  // Should return an empty list
    }

    // Custom class for testing with unique functionality
    static class Person {
        private final String name;
        private final int age;

        Person(String name, int age) {
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
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Person person = (Person) obj;
            return age == person.age && name.equals(person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name, age);
        }

        @Override
        public String toString() {
            return name + ", " + age;
        }
    }
}





