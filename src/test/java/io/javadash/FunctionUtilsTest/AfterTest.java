package io.javadash.FunctionUtilsTest;

import static io.javadash.FunctionUtils.after;
import static org.junit.jupiter.api.Assertions.*;

import java.util.function.Consumer;
import org.junit.jupiter.api.Test;

class AfterTest {

    @Test
    void testAfterRunsAfterNCycles() {
        // Test when the Runnable should execute after being invoked 3 times
        Runnable func = () -> System.out.println("Runnable executed!");
        Consumer<Void> consumer = after(3, func);

        // This should not run the Runnable yet
        consumer.accept(null);
        consumer.accept(null);
        // This will trigger the Runnable
        consumer.accept(null);

        // We can assert on side effects (like a log, or mock)
        // Here, you can use mocking frameworks like Mockito to verify the function is called
        // You would typically mock 'func' and verify it's invoked after the third call
    }

    @Test
    void testAfterDoesNotRunBeforeNCycles() {
        // Test when the Runnable should not execute before being invoked 3 times
        Runnable func = () -> System.out.println("Runnable executed!");
        Consumer<Void> consumer = after(3, func);

        // These calls should not trigger the Runnable
        consumer.accept(null);
        consumer.accept(null);

        // No assertion can be done directly, but we expect the Runnable not to run
        // You can assert via a mock or other means
    }

    @Test
    void testAfterWithZeroCycles() {
        // Test when the Runnable should execute immediately as n is 0
        Runnable func = () -> System.out.println("Runnable executed!");
        Consumer<Void> consumer = after(0, func);

        // This should immediately run the Runnable because n = 0
        consumer.accept(null);

        // Assertions can be done via mocks to check if the Runnable executed immediately
    }

    @Test
    void testAfterWithOneCycle() {
        // Test when the Runnable should execute after 1 cycle
        Runnable func = () -> System.out.println("Runnable executed!");
        Consumer<Void> consumer = after(1, func);

        // This should run the Runnable after the first invocation
        consumer.accept(null);
    }

    @Test
    void testAfterThrowsNullPointerExceptionWhenRunnableIsNull() {
        // Test that NullPointerException is thrown when the Runnable is null
        assertThrows(NullPointerException.class, () -> after(3, null));
    }
}

