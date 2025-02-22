package io.javadash;

import java.util.Objects;
import java.util.function.Consumer;

public class FunctionUtils {
    /**
     * Returns a {@code Consumer} that runs the provided {@code Runnable} after being called a specified number of times.
     * The {@code Runnable} will be executed when the {@code accept} method is invoked {@code n} times.
     *
     * <pre>{@code
     *      Consumer<Void> consumer = FunctionUtils.after(3, () -> System.out.println("Executed"));
     *      consumer.accept(null); // Nothing happens
     *      consumer.accept(null); // Nothing happens
     *      consumer.accept(null); // Output: Executed
     * }</pre>
     *
     * @param n    The number of times the {@code accept} method needs to be called before the {@code Runnable} is executed.
     * @param func The {@code Runnable} to execute after the specified number of calls.
     * @return A {@code Consumer} that tracks the number of calls and executes the {@code Runnable} when the threshold is reached.
     */

    public static Consumer<Void> after(int n, Runnable func) {
        Objects.requireNonNull(func);
        final int[] counter = {n};
        return new Consumer<Void>() {
            @Override
            public void accept(Void aVoid) {
                if (--counter[0] < 1) {
                    func.run();
                }
            }
        };
    }
}
