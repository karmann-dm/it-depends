package com.karmanno.itdepends.core.examples.annotation_scope;

import com.karmanno.itdepends.core.component.Scope;
import com.karmanno.itdepends.core.scan.ContextScoped;

@ContextScoped(scope = Scope.PROTOTYPE)
public class ScopedClass {
}
