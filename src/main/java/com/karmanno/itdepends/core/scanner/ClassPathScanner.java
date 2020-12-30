package com.karmanno.itdepends.core.scanner;

import com.google.common.annotations.VisibleForTesting;
import com.karmanno.itdepends.core.ComponentCandidate;
import com.karmanno.itdepends.core.ContextComponent;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    private static final Logger logger = LoggerFactory.getLogger(ClassPathScanner.class);
    private final String packageName;

    public ClassPathScanner(String packageName) {
        if (packageName == null) {
            throw ClassPathScannerException.packageIsNull();
        }
        this.packageName = packageName;
    }

    @Override
    public Stream<ComponentCandidate<?>> scan() {
        return findClasses()
                .map(this::createCandidate);
    }

    @VisibleForTesting
    ComponentCandidate<?> createCandidate(Class<?> componentCandidateClass) {
        if (componentCandidateClass == null) {
            throw CandidateCreationException.componentCandidateClassIsNull();
        }
        return new ComponentCandidate<>(componentCandidateClass);
    }

    @VisibleForTesting
    Stream<Class<?>> findClasses() {
        var cl = Thread.currentThread().getContextClassLoader();
        var path = packageName.replace(".", "/");
        Enumeration<URL> resources;
        try {
            resources = cl.getResources(path);
        } catch (IOException e) {
            logger.debug("Could not get resources with ClassLoader from path {}", path, e);
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

    Stream<Path> extractPath(URL url) {
        try {
            return Stream.of(Paths.get(url.toURI()));
        } catch (URISyntaxException e) {
            logger.debug("Could not extract path from URL {}", url.toString(), e);
            return Stream.empty();
        }
    }

    Stream<Class<?>> extractClasses(Path path) {
        try {
            return findClasses(path);
        } catch (IOException e) {
            logger.debug("Could not extract classes from path {}", path.toString(), e);
            return Stream.empty();
        }
    }

    Stream<Class<?>> findClasses(Path directory) throws IOException {
        return Files.walk(directory)
                .filter(Files::exists)
                .flatMap(path -> {
                    if (isClass(path)) {
                        var packagePath = FilenameUtils.getBaseName(
                                StringUtils.difference(directory.toString(), path.toString()).replace("/", ".")
                        );
                        var className = packageName + packagePath;
                        try {
                            return Stream.of(Class.forName(className));
                        } catch (ClassNotFoundException e) {
                            logger.debug("Could not determine class for name {}", className, e);
                            return Stream.empty();
                        }
                    } else {
                        return Stream.empty();
                    }
                });
    }

    boolean isClass(Path path) {
        return FilenameUtils.getExtension(path.getFileName().toString()).equalsIgnoreCase("class");
    }
}
