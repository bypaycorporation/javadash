package io.javadash;

import io.javadash.core.Validate;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

public class ObjectUtils {
    /**
     * Tests if an Object is empty or null.
     * <p>
     * The following types are supported:
     * <ul>
     * <li>{@link CharSequence}: Considered empty if its length is zero.</li>
     * <li>{@link Array}: Considered empty if its length is zero.</li>
     * <li>{@link Collection}: Considered empty if it has zero elements.</li>
     * <li>{@link Map}: Considered empty if it has zero key-value mappings.</li>
     * <li>{@link Optional}: Considered empty if {@link Optional#isPresent} returns false, regardless of the "emptiness" of the contents.</li>
     * </ul>
     *
     * <pre>
     * ObjectUtils.isEmpty(null)             = true
     * ObjectUtils.isEmpty("")               = true
     * ObjectUtils.isEmpty("ab")             = false
     * ObjectUtils.isEmpty(new int[]{})      = true
     * ObjectUtils.isEmpty(new int[]{1,2,3}) = false
     * ObjectUtils.isEmpty(1234)             = false
     * ObjectUtils.isEmpty(1234)             = false
     * ObjectUtils.isEmpty(Optional.of(""))  = false
     * ObjectUtils.isEmpty(Optional.empty()) = true
     * </pre>
     *
     * @param object the {@link Object} to test, may be {@code null}
     * @return {@code true} if the object has a supported type and is empty or null,
     * {@code false} otherwise
     */
    public static boolean isEmpty(final Object object) {
        return Validate.isEmpty(object);
    }

    /**
     * Safely extracts a value from a nested object using a chain of getter functions.
     * If any part of the chain is {@code null}, the default value is returned.
     *
     * <p>This method allows you to safely traverse objects without throwing a {@link NullPointerException}.
     * It applies a getter function to the provided object and returns the result, or a default value (if applicable)
     * when the object is {@code null}.</p>
     *
     * <p>For example, if you have a nested object structure and want to safely retrieve a value
     * from a deeply nested object, this method can be used without worrying about {@code null} values
     * at any level of the chain.</p>
     *
     * <pre>{@code
     *
     *      Person person = new Person();
     *      String street = ObjectUtils.getOrDefault(person, p -> p.getAddress());
     *      System.out.println(street);
     *      // Output: null if the address is not exist
     *
     *      street = ObjectUtils.getOrDefault(person, p -> p.getAddress());
     *      System.out.println(street);
     *      // Output: "Main St"
     *
     * }</pre>
     *
     * @param <T>    the type of the root object
     * @param <V>    the type of the value to be extracted
     * @param object the root object from which to extract the value, may be {@code null}
     * @param getter the function to retrieve the value from the object
     * @return the extracted value or {@code null} if the object or any part of the chain is {@code null}
     */
    @SuppressWarnings("unchecked")
    public static <T, V> V getOrDefault(T object, Function<? super T, ? super V> getter) {
        if (object == null) {
            return null;
        }
        return (V) getter.apply(object);
    }

    /**
     * Safely extracts a value from a nested object using a getter function.
     * If the object or the extracted value is {@code null}, the specified default value is returned.
     *
     * <p>This method provides a safe way to retrieve a value from an object without risking a {@link NullPointerException}.
     * If the object is {@code null}, or if the extracted value is {@code null}, the {@code defaultValue} is returned instead.</p>
     *
     * <p>For example, if you have a nested object structure and want to safely retrieve a value,
     * this method allows you to specify a default value to be returned if any part of the chain is {@code null}.</p>
     *
     * <pre>{@code
     *
     *      Person person = new Person();
     *      String street = ObjectUtils.getOrDefault(person, "Unknown Street", p -> p.getAddress().getStreet());
     *      System.out.println(street);
     *      // Output: "Unknown Street" (because the address is null)
     *
     *      // Set the address and try again
     *      person.setAddress(new Address("Main St"));
     *      street = ObjectUtils.getOrDefault(person, "Unknown Street", p -> p.getAddress().getStreet());
     *      System.out.println(street);
     *      // Output: "Main St"
     *
     * }</pre>
     *
     * @param <T>          the type of the root object
     * @param <V>          the type of the value to be extracted
     * @param object       the root object from which to extract the value, may be {@code null}
     * @param defaultValue the value to return if the object or the extracted value is {@code null}
     * @param getter       the function to retrieve the value from the object
     * @return the extracted value, or the {@code defaultValue} if the object or the value is {@code null}
     */
    @SuppressWarnings("unchecked")
    public static <T, V> V getOrDefault(T object, V defaultValue, Function<? super T, ? super V> getter) {
        if (object == null) {
            return defaultValue;
        }
        V intermediate = (V) getter.apply(object);
        if (intermediate == null) {
            return defaultValue;
        }
        return intermediate;
    }


    /**
     * Safely extracts a value from a nested object using a chain of two getter functions.
     * If any part of the chain is {@code null}, the method returns {@code null} instead of throwing a {@link NullPointerException}.
     *
     * <p>This method allows for safe traversal of nested objects by applying a series of getter functions.
     * If any part of the object chain is {@code null}, it will return {@code null} rather than propagating the error.</p>
     *
     * <p>For example, if you have a nested object structure and want to safely retrieve a value from
     * a second-level property, this method can be used without worrying about {@code null} values at any level of the chain.</p>
     *
     * <pre>{@code
     *
     *      Person person = new Person();
     *      String street = ObjectUtils.getOrDefault(person, p -> p.getAddress(), Address::getStreet);
     *      System.out.println(street);
     *      // Output: null if the street is not exist
     *
     *      // Set the address and try again
     *      person.setAddress(new Address("Main St"));
     *      street = ObjectUtils.getOrDefault(person, p -> p.getAddress(), Address::getStreet);
     *      System.out.println(street);
     *      // Output: "Main St"
     *
     * }</pre>
     *
     * @param <T>     the type of the root object
     * @param <U>     the type of the intermediate value
     * @param <V>     the type of the value to be extracted
     * @param object  the root object from which to start the extraction, may be {@code null}
     * @param getter1 the first getter function to retrieve an intermediate value from the object
     * @param getter2 the second getter function to extract the final value from the intermediate value
     * @return the extracted value or {@code null} if any part of the chain is {@code null}
     */
    @SuppressWarnings("unchecked")
    public static <T, U, V> V getOrDefault(T object, Function<? super T, ? super U> getter1,
                                        Function<? super U, ? super V> getter2) {
        if (object == null) {
            return null;
        }
        U intermediate = (U) getter1.apply(object);
        if (intermediate == null) {
            return null;
        }
        return (V) getter2.apply(intermediate);
    }

    /**
     * Safely extracts a value from a nested object using a chain of two getter functions.
     * If any part of the chain is {@code null}, the specified default value is returned.
     *
     * <p>This method allows you to safely traverse a chain of getters. If the object or any intermediate
     * value is {@code null}, it returns the specified {@code defaultValue}, avoiding a {@link NullPointerException}.</p>
     *
     * <p>For example, if you have a nested object structure, this method can be used to safely retrieve
     * a value from the second-level property, with a fallback to a default value if any part of the chain is {@code null}.</p>
     *
     * <pre>{@code
     *
     *      Person person = new Person();
     *      String street = ObjectUtils.getOrDefault(person, "Unknown Street", p -> p.getAddress(), Address::getStreet);
     *      System.out.println(street);
     *      // Output: "Unknown Street" (because the address is null)
     *
     *      // Set the address and try again
     *      person.setAddress(new Address("Main St"));
     *      street = ObjectUtils.getOrDefault(person, "Unknown Street", p -> p.getAddress(), Address::getStreet);
     *      System.out.println(street);
     *      // Output: "Main St"
     *
     * }</pre>
     *
     * @param <T>          the type of the root object
     * @param <U>          the type of the intermediate value extracted from the root object
     * @param <V>          the type of the final value to be extracted
     * @param object       the root object to start the extraction from, may be {@code null}
     * @param defaultValue the value to return if the object or any intermediate value is {@code null}
     * @param getter1      the first getter function to retrieve an intermediate value from the root object
     * @param getter2      the second getter function to extract the final value from the intermediate value
     * @return the extracted value if the chain is non-null, or the {@code defaultValue} if any part of the chain is {@code null}
     */
    @SuppressWarnings("unchecked")
    public static <T, U, V> V getOrDefault(T object, V defaultValue, Function<? super T, ? super U> getter1,
                                        Function<? super U, ? super V> getter2) {
        if (object == null) {
            return defaultValue;
        }
        U intermediate = (U) getter1.apply(object);
        if (intermediate == null) {
            return defaultValue;
        }
        V result = (V) getter2.apply(intermediate);
        if (result == null) {
            return defaultValue;
        }
        return result;
    }

}
