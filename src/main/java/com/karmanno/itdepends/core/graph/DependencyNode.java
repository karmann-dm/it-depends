package com.karmanno.itdepends.core.graph;

import com.karmanno.itdepends.core.component.ComponentInstance;
import com.karmanno.itdepends.core.component.ContextComponent;

import java.util.Objects;

public class DependencyNode {
    private String id;
    private Class<?> cls;
    private final ContextComponent<?> contextComponent;
    private ComponentInstance<?> componentInstance;
    private boolean visited = false;

    public DependencyNode(String id, Class<?> cls, ContextComponent<?> contextComponent) {
        this.id = id;
        this.cls = cls;
        this.contextComponent = contextComponent;
    }

    public void setCls(Class<?> cls) {
        this.cls = cls;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Class<?> getCls() {
        return cls;
    }

    public String getId() {
        return id;
    }

    public boolean isVisited() {
        return visited;
    }

    public ContextComponent<?> getContextComponent() {
        return contextComponent;
    }

    public ComponentInstance<?> getComponentInstance() {
        return componentInstance;
    }

    public void setComponentInstance(ComponentInstance<?> componentInstance) {
        this.componentInstance = componentInstance;
    }
}
