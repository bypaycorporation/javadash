package io.javadash.ObjectUtilsTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import io.javadash.ObjectUtils;
import java.util.function.Function;
import org.junit.jupiter.api.Test;

public class GetOrDefaultTest {
    // Test: Object is not null, and getter works fine
    @Test
    void testgetOrDefaultWithNonNullObject() {
        // Create a person with a valid address
        Person person = new Person();
        person.address = new Address("Main St");

        Function<Person, String> getAddressStreet = p -> p.getAddress().getStreet();
        String street = ObjectUtils.getOrDefault(person, getAddressStreet);

        assertNotNull(street, "Street should not be null");
        assertEquals("Main St", street, "Street should be 'Main St'");
    }

    // Test: Root object is null
    @Test
    void testgetOrDefaultWithNullObject() {
        Person person = null;
        Function<Person, String> getAddressStreet = p -> p.getAddress().getStreet();

        String street = ObjectUtils.getOrDefault(person, getAddressStreet);

        assertNull(street, "Street should be null when the person object is null");
    }

    // Test: Getter function returns null
    @Test
    void testgetOrDefaultWithNullReturnFromGetter() {
        Person person = new Person();
        person.address = null;  // Address is null

        Function<Person, String> getAddressStreet = p -> p.getAddress() != null ? p.getAddress().getStreet() : null;
        String street = ObjectUtils.getOrDefault(person, getAddressStreet);

        assertNull(street, "Street should be null when the address is null");
    }

    // Test: Object is non-null but getter function returns null due to missing value
    @Test
    void testgetOrDefaultWithNullInNestedGetter() {
        Person person = new Person(); // Address is still null, which will be safely handled
        Function<Person, String> getStreet = p -> p.getAddress() == null ? null : p.getAddress().getStreet();

        String street = ObjectUtils.getOrDefault(person, getStreet);

        assertNull(street, "Street should be null because the address is null");
    }

    // Test: Using a chain of getter functions (i.e., a getter for the getter)
    @Test
    void testgetOrDefaultWithChainOfGetters() {
        // Nested test: Get the street via two functions
        Person person = new Person();
        person.address = new Address("123 Elm St");

        Function<Person, Address> getAddress = Person::getAddress;
        Function<Address, String> getStreet = Address::getStreet;

        // Chain the two functions
        Function<Person, String> getStreetFromPerson = getAddress.andThen(getStreet);

        String street = ObjectUtils.getOrDefault(person, getStreetFromPerson);

        assertNotNull(street, "Street should not be null");
        assertEquals("123 Elm St", street, "Street should be '123 Elm St'");
    }

    // Test: Object is not null, and getter works fine
    @Test
    void testgetOrDefaultWithNonNullObjectWithDefaultValue() {
        // Create a person with a valid address
        Person person = new Person();
        person.address = new Address("Main St");

        Function<Person, String> getAddressStreet = p -> p.getAddress().getStreet();
        String street = ObjectUtils.getOrDefault(person, "Default Street", getAddressStreet);

        assertNotNull(street, "Street should not be null");
        assertEquals("Main St", street, "Street should be 'Main St'");
    }

    // Test: Root object is null
    @Test
    void testgetOrDefaultWithNullObjectWithDefaultValue() {
        Person person = null;
        Function<Person, String> getAddressStreet = p -> p.getAddress().getStreet();

        String street = ObjectUtils.getOrDefault(person, "Default Street", getAddressStreet);

        assertEquals("Default Street", street, "Street should be 'Default Street' when the person object is null");
    }

    // Test: Getter function returns null
    @Test
    void testgetOrDefaultWithNullReturnFromGetterWithDefaultValue() {
        Person person = new Person();
        person.address = null;  // Address is null

        Function<Person, String> getAddressStreet = p -> p.getAddress() != null ? p.getAddress().getStreet() : null;
        String street = ObjectUtils.getOrDefault(person, "Default Street", getAddressStreet);

        assertEquals("Default Street", street, "Street should be 'Default Street' when the getter returns null");
    }

    // Test: Object is non-null but getter function returns null due to missing value
    @Test
    void testgetOrDefaultWithNullInNestedGetterWithDefaultValue() {
        Person person = new Person(); // Address is still null, which will be safely handled
        Function<Person, String> getStreet = p -> p.getAddress() == null ? null : p.getAddress().getStreet();

        String street = ObjectUtils.getOrDefault(person, "Default Street", getStreet);

        assertEquals("Default Street", street, "Street should be 'Default Street' because the address is null");
    }

    // Test: Using a chain of getter functions (i.e., a getter for the getter)
    @Test
    void testgetOrDefaultWithChainOfGettersWithDefaultValue() {
        // Nested test: Get the street via two functions
        Person person = new Person();
        person.address = new Address("123 Elm St");

        Function<Person, Address> getAddress = Person::getAddress;
        Function<Address, String> getStreet = Address::getStreet;

        // Chain the two functions
        Function<Person, String> getStreetFromPerson = getAddress.andThen(getStreet);

        String street = ObjectUtils.getOrDefault(person, "Default Street", getStreetFromPerson);

        assertNotNull(street, "Street should not be null");
        assertEquals("123 Elm St", street, "Street should be '123 Elm St'");
    }

    // Test: Both getter functions work correctly
    @Test
    void testgetOrDefaultWithTwoGetterFunctions() {
        // Create a person with a valid address
        Person person = new Person();
        person.address = new Address("Main St");

        Function<Person, Address> getAddress = Person::getAddress;
        Function<Address, String> getStreet = Address::getStreet;

        String street = ObjectUtils.getOrDefault(person, getAddress, getStreet);

        assertNotNull(street, "Street should not be null");
        assertEquals("Main St", street, "Street should be 'Main St'");
    }

    // Test: First getter returns null
    @Test
    void testgetOrDefaultWithTwoGetterFunctions_FirstGetterReturnsNull() {
        Person person = new Person();
        person.address = null;  // Address is null

        Function<Person, Address> getAddress = Person::getAddress;
        Function<Address, String> getStreet = Address::getStreet;

        String street = ObjectUtils.getOrDefault(person, getAddress, getStreet);

        assertNull(street, "Street should be null when the first getter returns null");
    }

    // Test: Second getter returns null
    @Test
    void testgetOrDefaultWithTwoGetterFunctions_SecondGetterReturnsNull() {
        Person person = new Person();
        person.address = new Address(null);  // Address has a null street

        Function<Person, Address> getAddress = Person::getAddress;
        Function<Address, String> getStreet = Address::getStreet;

        String street = ObjectUtils.getOrDefault(person, getAddress, getStreet);

        assertNull(street, "Street should be null when the second getter returns null");
    }

    // Test: Root object is null
    @Test
    void testgetOrDefaultWithTwoGetterFunctions_ObjectIsNull() {
        Person person = null;

        Function<Person, Address> getAddress = Person::getAddress;
        Function<Address, String> getStreet = Address::getStreet;

        String street = ObjectUtils.getOrDefault(person, getAddress, getStreet);

        assertNull(street, "Street should be null when the root object is null");
    }

    // Test: Both getters are non-null
    @Test
    void testgetOrDefaultWithTwoGetterFunctions_BothGettersNonNull() {
        Person person = new Person();
        person.address = new Address("Elm St");

        Function<Person, Address> getAddress = Person::getAddress;
        Function<Address, String> getStreet = Address::getStreet;

        String street = ObjectUtils.getOrDefault(person, getAddress, getStreet);

        assertNotNull(street, "Street should not be null");
        assertEquals("Elm St", street, "Street should be 'Elm St'");
    }

    // Test: Both getter functions work correctly, and no nulls are encountered
    @Test
    void testgetOrDefaultWithTwoGetterFunctionsAndDefaultValue() {
        // Create a person with a valid address
        Person person = new Person();
        person.address = new Address("Main St");

        Function<Person, Address> getAddress = Person::getAddress;
        Function<Address, String> getStreet = Address::getStreet;
        String defaultStreet = "Unknown";

        String street = ObjectUtils.getOrDefault(person, defaultStreet, getAddress, getStreet);

        assertNotNull(street, "Street should not be null");
        assertEquals("Main St", street, "Street should be 'Main St'");
    }

    // Test: First getter returns null, default value should be returned
    @Test
    void testgetOrDefaultWithTwoGetterFunctions_FirstGetterReturnsNullWithDefaultValue() {
        Person person = new Person();
        person.address = null;  // Address is null
        String defaultStreet = "Unknown";

        Function<Person, Address> getAddress = Person::getAddress;
        Function<Address, String> getStreet = Address::getStreet;

        String street = ObjectUtils.getOrDefault(person, defaultStreet, getAddress, getStreet);

        assertEquals(defaultStreet, street, "Street should be the default value when the first getter returns null");
    }

    // Test: Second getter returns null, default value should be returned
    @Test
    void testgetOrDefaultWithTwoGetterFunctions_SecondGetterReturnsNullWithDefaultValue() {
        Person person = new Person();
        person.address = new Address(null);  // Address has a null street
        String defaultStreet = "Unknown";

        Function<Person, Address> getAddress = Person::getAddress;
        Function<Address, String> getStreet = Address::getStreet;

        String street = ObjectUtils.getOrDefault(person, defaultStreet, getAddress, getStreet);

        assertEquals(defaultStreet, street, "Street should be the default value when the second getter returns null");
    }

    // Test: Root object is null, should return the default value
    @Test
    void testgetOrDefaultWithTwoGetterFunctions_ObjectIsNullWithDefaultValue() {
        Person person = null;
        String defaultStreet = "Unknown";

        Function<Person, Address> getAddress = Person::getAddress;
        Function<Address, String> getStreet = Address::getStreet;

        String street = ObjectUtils.getOrDefault(person, defaultStreet, getAddress, getStreet);

        assertEquals(defaultStreet, street, "Street should be the default value when the root object is null");
    }

    // Test: Both getters return non-null values
    @Test
    void testgetOrDefaultWithTwoGetterFunctions_BothGettersReturnNonNullWithDefaultValue() {
        Person person = new Person();
        person.address = new Address("Elm St");
        String defaultStreet = "Unknown";

        Function<Person, Address> getAddress = Person::getAddress;
        Function<Address, String> getStreet = Address::getStreet;

        String street = ObjectUtils.getOrDefault(person, defaultStreet, getAddress, getStreet);

        assertNotNull(street, "Street should not be null");
        assertEquals("Elm St", street, "Street should be 'Elm St'");
    }

    // Sample Person class with nested Address class
    static class Address {
        String street;

        Address(String street) {
            this.street = street;
        }

        public String getStreet() {
            return street;
        }
    }

    static class Person {
        Address address;

        public Address getAddress() {
            return address;
        }
    }
}
