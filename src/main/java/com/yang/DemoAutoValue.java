package com.yang;

import com.google.auto.value.AutoValue;

public class DemoAutoValue {
    public static void main(String[] args) {
        System.out.println("hello");
    }
}

@AutoValue
abstract class Element {
    public abstract int getRow();

    public abstract int getCol();

    public static Element create(int row, int col) {
        return new AutoValue_Element(row, col);
    }
}