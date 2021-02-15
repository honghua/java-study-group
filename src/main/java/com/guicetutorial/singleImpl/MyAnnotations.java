package com.guicetutorial.singleImpl;

import javax.inject.Qualifier;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MyAnnotations {
    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    @interface BasicDinning {}

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    @interface LuxuryDinning {}
}

