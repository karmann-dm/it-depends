package com.karmanno.itdepends.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 *
 */
public class GenericContext implements Context {
    private final List<Scanner> scanners = new ArrayList<>();

    private GenericContext() {
    }

    @Override
    public <T> Stream<T> pull(Class<T> componentClass) {
        return null;
    }

    @Override
    public <T> T resolve(T resolvable) {
        return null;
    }

    public List<Scanner> getScanners() {
        return scanners;
    }

    class Builder {
        public Builder withScanner(Scanner scanner) {
            scanners.add(scanner);
            return this;
        }

        public GenericContext build() {
            return GenericContext.this;
        }
    }
}
