package com.karmanno.itdepends.core.graph;

import java.util.Objects;

public class DependencyNode {
    private String id;
    private Class<?> cls;

    public DependencyNode(String id, Class<?> cls) {
    }

    public void setCls(Class<?> cls) {
        this.cls = cls;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Class<?> getCls() {
        return cls;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DependencyNode that = (DependencyNode) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
