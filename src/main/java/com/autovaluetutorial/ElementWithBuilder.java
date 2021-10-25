package com.autovaluetutorial;

import com.google.auto.value.AutoValue;

@AutoValue
abstract class ElementWithBuilder {
    public abstract int getRow();
    public abstract int getCol();

    static Builder builder() {
        return new AutoValue_ElementWithBuilder.Builder();
    }

    /** Creates a new {@code ElementWithBuilder} instance with only {@code rowVal} set. */
    static ElementWithBuilder createWithRow(int rowVal) {
        return builder().setRow(rowVal).build();
    }

    @AutoValue.Builder
    abstract static class Builder {
        abstract Builder setRow(int val);
        abstract Builder setCol(int val);
        abstract ElementWithBuilder build();
    }
}