package com.karmanno.itdepends.core.scan;

import com.karmanno.itdepends.core.component.ContextComponent;

import java.util.Collection;

public interface ComponentScanner {
    Collection<ContextComponent<?>> scanForComponents();
}
