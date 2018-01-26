package com.webber.demos.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by picher on 2018/1/22.
 * Describe：
 */

@Retention(RetentionPolicy.SOURCE)
public @interface BuildAnnotation {

    String value();
}
