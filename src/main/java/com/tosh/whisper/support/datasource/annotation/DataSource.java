package com.tosh.whisper.support.datasource.annotation;

import java.lang.annotation.*;

@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)  
public @interface DataSource
{
    String value();
}
