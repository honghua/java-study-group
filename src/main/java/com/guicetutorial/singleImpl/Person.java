package com.guicetutorial.singleImpl;

import com.google.auto.value.AutoValue;

import java.util.regex.Pattern;

@AutoValue
public abstract class Person {
    abstract String name();
    static Person create(String name) {
        return new AutoValue_Person(name);
    }
}
