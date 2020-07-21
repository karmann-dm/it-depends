package com.karmanno.itdepends.core;

public @interface ContextScoped {
    Scope scope();
    String module() default "main";
}
