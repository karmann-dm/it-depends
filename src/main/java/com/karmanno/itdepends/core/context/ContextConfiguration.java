package com.karmanno.itdepends.core.context;

import com.karmanno.itdepends.core.component.ComponentInstance;
import com.karmanno.itdepends.core.component.ContextComponent;
import com.karmanno.itdepends.core.property.PropertySourceComponent;

import java.util.Collection;
import java.util.Map;

public interface ContextConfiguration {
    ContextComponent<?> registerComponent(ContextComponent<?> contextComponent);

    void registerPropertySource(String path);

    Map<String, ContextComponent<?>> getComponents();

    Map<String, ComponentInstance<?>> getInstances();
}
