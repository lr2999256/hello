package com.test.annotation;

import java.lang.annotation.*;

/**
 * Created by Rui on 2017/7/23.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Fruit {

    String name() default "";

}
