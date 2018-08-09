package com.example;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention( RetentionPolicy.RUNTIME )
public @interface FieldAnnotation {
    boolean required() default false;
    String reportName();
    String regex();
    int capturingGroup() default 1;
}
