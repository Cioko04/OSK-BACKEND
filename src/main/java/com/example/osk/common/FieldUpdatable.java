package com.example.osk.common;

import java.util.Objects;
import java.util.function.Consumer;

public interface FieldUpdatable {

    default <T> void updateFieldIfChanged(T changedValue, T currentValue, Consumer<T> fieldUpdater) {
        if (shouldUpdate(changedValue, currentValue)) {
            fieldUpdater.accept(changedValue);
        }
    }

    default <T> boolean shouldUpdate(T changedValue, T currentValue) {
        return changedValue != null
                && !Objects.equals(currentValue, changedValue)
                && (!(changedValue instanceof String checkedChangedValue) || !checkedChangedValue.isEmpty());
    }
}
