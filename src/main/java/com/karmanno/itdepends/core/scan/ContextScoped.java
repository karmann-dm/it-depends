package com.karmanno.itdepends.core.scan;

import com.karmanno.itdepends.core.Scope;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ContextScoped {
    Scope scope();
    String module() default "main";
}
