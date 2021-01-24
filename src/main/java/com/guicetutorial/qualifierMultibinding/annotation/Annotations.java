package com.guicetutorial.qualifierMultibinding.annotation;

import javax.inject.Qualifier;
import java.lang.annotation.*;

public class Annotations {

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.METHOD, ElementType.FIELD})
    public @interface MyAnnotation {
    }

}
