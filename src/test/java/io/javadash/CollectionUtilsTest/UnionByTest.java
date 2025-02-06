package io.javadash.CollectionUtilsTest;

import static io.javadash.CollectionUtils.unionBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import org.junit.jupiter.api.Test;

public class UnionByTest {
    @Test
    void testUnionBy_SingleCollection() {
        List<String> list1 = Arrays.asList("apple", "banana", "cherry");
        Function<String, Integer> iteratee = String::length; // Group by string length
        List<String> result = unionBy(iteratee, list1);
        assertEquals(Arrays.asList("apple", "banana"), result);
    }

    @Test
    void testUnionBy_MultipleCollections() {
        List<String> list1 = Arrays.asList("apple", "banana");
        List<String> list2 = Arrays.asList("cherry", "date");
        List<String> list3 = Arrays.asList("elderberry", "fig");
        Function<String, Integer> iteratee = String::length; // Group by string length
        List<String> result = unionBy(iteratee, list1, list2, list3);
        assertEquals(Arrays.asList("apple", "banana", "date", "elderberry", "fig"), result);
    }

    @Test
    void testUnionBy_DuplicateElements() {
        List<String> list1 = Arrays.asList("apple", "banana");
        List<String> list2 = Arrays.asList("banana", "cherry");
        Function<String, Integer> iteratee = s -> s.length(); // Group by string length
        List<String> result = unionBy(iteratee, list1, list2);
        assertEquals(Arrays.asList("apple", "banana"), result);
    }

    @Test
    void testUnionBy_EmptyCollections() {
        List<String> list1 = Collections.emptyList();
        List<String> list2 = Collections.emptyList();
        Function<String, Integer> iteratee = String::length; // Group by string length
        List<String> result = unionBy(iteratee, list1, list2);
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testUnionBy_WithNullCollection() {
        List<String> list1 = Arrays.asList("apple", "banana");
        List<String> list2 = null;
        Function<String, Integer> iteratee = String::length; // Group by string length
        List<String> result = unionBy(iteratee, list1, list2);
        assertEquals(Arrays.asList("apple", "banana"), result);
    }

    @Test
    void testUnionBy_AllNullCollections() {
        Function<String, Integer> iteratee = String::length; // Group by string length
        List<String> result = unionBy(iteratee, null, null);
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testUnionBy_InvalidCollections() {
        // Assuming isValidRestList returns false for invalid collections
        Function<String, Integer> iteratee = String::length; // Group by string length
        List<String> result = unionBy(iteratee, null);
        assertEquals(Collections.emptyList(), result);
    }

    @Test
    void testUnionBy_CustomIteratee() {
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(2, 3, 4);
        Function<Integer, Integer> iteratee = x -> x % 2; // Group by even/odd
        List<Integer> result = unionBy(iteratee, list1, list2);
        assertEquals(Arrays.asList(1, 2), result); // 1 (odd), 2 (even)
    }

    @Test
    void testUnionBy_NullIteratee() {
        List<String> list1 = Arrays.asList("apple", "banana");
        assertThrows(NullPointerException.class, () -> unionBy(null, list1));
    }

    @Test
    void testUnionByWithIteratee() {
        List<Person> list1 = new ArrayList<>();
        list1.add(new Person("Alice", 30));
        list1.add(new Person("Bob", 25));

        List<Person> list2 = new ArrayList<>();
        list2.add(new Person("Alice", 30));  // Duplicate based on name
        list2.add(new Person("Charlie", 35));

        Function<Person, String> byName = Person::getName;  // Use name as the iteratee for uniqueness

        List<Person> result = unionBy(byName, list1, list2);

        // The result should contain only one "Alice", one "Bob", and one "Charlie" based on the name
        assertEquals(new ArrayList<>(Arrays.asList(
            new Person("Alice", 30),
            new Person("Bob", 25),
            new Person("Charlie", 35))), result);
    }

    @Test
    void testUnionByWithEmptyList() {
        List<Person> list1 = new ArrayList<>();
        List<Person> list2 = new ArrayList<>();

        Function<Person, String> byName = Person::getName;

        List<Person> result = unionBy(byName, list1, list2);

        // The result should be an empty list
        assertEquals(new ArrayList<>(), result);
    }

    @Test
    void testUnionByWithNullList() {
        List<Person> list1 = null;
        List<Person> list2 = new ArrayList<>();
        list2.add(new Person("Alice", 30));

        Function<Person, String> byName = Person::getName;

        List<Person> result = unionBy(byName, list1, list2);

        // The result should be equal to list2 as list1 is null
        assertEquals(new ArrayList<>(Arrays.asList(new Person("Alice", 30))), result);
    }

    @Test
    void testUnionByWithComplexIteratee() {
        List<Person> list1 = new ArrayList<>();
        list1.add(new Person("Alice", 30));
        list1.add(new Person("Bob", 25));

        List<Person> list2 = new ArrayList<>();
        list2.add(new Person("Charlie", 35));
        list2.add(new Person("Alice", 30)); // Duplicate based on name

        Function<Person, Integer> byAge = Person::getAge;  // Use age as the iteratee for uniqueness

        List<Person> result = unionBy(byAge, list1, list2);

        // The result should contain only one "Alice" (age 30) and "Bob" (age 25)
        assertEquals(new ArrayList<>(Arrays.asList(
            new Person("Alice", 30),
            new Person("Bob", 25),
            new Person("Charlie", 35))), result);
    }

    // Custom class for testing with union functionality
    static class Person {
        private String name;
        private int age;

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
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
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




