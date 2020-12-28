package com.karmanno.itdepends.core;

import org.apache.commons.io.FilenameUtils;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Enumeration;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class ClassPathScanner implements Scanner {
    private final String packageName;

    public ClassPathScanner(@Nonnull String packageName) {
        this.packageName = packageName;
    }

    @Override
    public Stream<ComponentCandidate<?>> scan() {
        return findClasses()
                .map(this::createCandidate);
    }

    private ComponentCandidate<?> createCandidate(Class<?> componentCandidateClass) {
        return new ComponentCandidate<>(componentCandidateClass);
    }

    private Stream<Class<?>> findClasses() {
        var cl = Thread.currentThread().getContextClassLoader();
        var path = packageName.replace(".", "/");
        Enumeration<URL> resources;
        try {
            resources = cl.getResources(path);
        } catch (IOException e) {
            return Stream.empty();
        }

        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(
                        resources.asIterator(),
                        Spliterator.ORDERED),
                true
        ).flatMap(this::extractPath)
         .flatMap(this::extractClasses)
         .filter(cls -> cls.isAnnotationPresent(ContextComponent.class));
    }

    private Stream<Path> extractPath(URL url) {
        try {
            return Stream.of(Paths.get(url.toURI()));
        } catch (URISyntaxException e) {
            return Stream.empty();
        }
    }

    private Stream<Class<?>> extractClasses(Path path) {
        try {
            return findClasses(path);
        } catch (ClassNotFoundException | IOException e) {
            return Stream.empty();
        }
    }

    private Stream<Class<?>> findClasses(Path directory) throws ClassNotFoundException, IOException {
        return Files.walk(directory)
                .filter(Files::exists)
                .flatMap(path -> {
                    if (isClass(path)) {
                        try {
                            return Stream.of(Class.forName(packageName + "." + FilenameUtils.getBaseName(path.getFileName().toString())));
                        } catch (ClassNotFoundException e) {
                            return Stream.empty();
                        }
                    } else {
                        return Stream.empty();
                    }
                });
    }

    private boolean isClass(Path path) {
        return FilenameUtils.getExtension(path.getFileName().toString()).equalsIgnoreCase("class");
    }
}
