package com.karmanno.itdepends.core;

import java.util.Collection;

public interface ContextModule {
    String getId();
    Collection<ContextComponent> getComponents();
}
