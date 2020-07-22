package com.karmanno.itdepends.core.scan;

import com.karmanno.itdepends.core.Scope;

public @interface ContextScoped {
    Scope scope();
    String module() default "main";
}
