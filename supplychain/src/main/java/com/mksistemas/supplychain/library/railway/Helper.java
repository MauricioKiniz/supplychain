package com.mksistemas.supplychain.library.railway;

import java.util.function.Consumer;
import java.util.function.Function;

public final class Helper {
    private static final Unit unit = new Unit();

    public static Unit getUnit() {
        return unit;
    }

    public static <T> Function<T, Unit> toFunc(Consumer<T> action, T value) {
        action.accept(value);
        return new Function<T, Unit>() {
            @Override
            public Unit apply(T t) {
                return new Unit();
            }
        };
    }
}
