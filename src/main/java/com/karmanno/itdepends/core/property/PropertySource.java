package com.karmanno.itdepends.core.property;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class PropertySource {
    private Path propertyPath;
    private Properties properties;

    public PropertySource(String url) {
        try {
            // Try to create from absolute path
            URI uri = URI.create(url);
            this.propertyPath = Path.of(uri);
        } catch (Exception e) {
            try {
                // Try to create from resources
                URL resource = getClass().getClassLoader().getResource(url);
                if (resource == null)
                    throw new RuntimeException("Resource is empty");
                this.propertyPath = Path.of(resource.toURI());
            } catch (Exception ignored) {
            }
        }
    }

    public synchronized Properties getProperties() throws IOException {
        if (properties == null) {
            properties = new Properties();
            properties.load(Files.newInputStream(propertyPath));
        }
        return properties;
    }

    public Path getPath() {
        return propertyPath;
    }
}
