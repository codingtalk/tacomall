

package com.tacomall.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface NoRepeatSubmit {

    long seconds() default 5;

    String location() default "NoRepeatSubmit";

    int argIndex() default 0;

    String name() default "";
}