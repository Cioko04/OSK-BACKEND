package com.example.osk.common;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.function.Consumer;

public interface FieldUpdatable {

    default void updateFieldIfChanged(String changedValue, String currentValue, Consumer<String> fieldUpdater) {
        if (changedValue != null && !changedValue.isEmpty() && !Objects.equals(currentValue, changedValue)) {
            fieldUpdater.accept(changedValue);
        }
    }

    default void updateFieldIfChanged(LocalDate changedValue, LocalDate currentValue, Consumer<LocalDate> fieldUpdater) {
        if (changedValue != null && !Objects.equals(currentValue, changedValue)) {
            fieldUpdater.accept(changedValue);
        }
    }

    default void updateFieldIfChanged(BigDecimal changedValue, BigDecimal currentValue, Consumer<BigDecimal> fieldUpdater) {
        if (changedValue != null && !Objects.equals(currentValue, changedValue)) {
            fieldUpdater.accept(changedValue);
        }
    }
}
