package com.titlebarui.lijie.titlebarui.serialization;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by lijie on 2017/08/27.
 */
@Retention(value = RetentionPolicy.RUNTIME)
public @interface JsonField {
    String name();
    Class<?> type() default Void.class;
}
