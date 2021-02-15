package com.guicetutorial.qualifierMultibinding.annotation;

import javax.inject.Qualifier;
import java.lang.annotation.*;

public class Annotations {

//    @Qualifier
//    @Retention(RetentionPolicy.RUNTIME)
//    public @interface MyAnnotation {
//    }

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface QuanAnnotation{}
}

