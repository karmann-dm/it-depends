package com.karmanno.itdepends.core.scan;

import com.karmanno.itdepends.core.component.ContextComponent;
import com.karmanno.itdepends.core.component.DefaultContextComponentBuilder;
import com.karmanno.itdepends.core.exception.ComponentScanException;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class PackageComponentScanner implements ComponentScanner {
    private final String basePackage;

    public PackageComponentScanner(String basePackage) {
        this.basePackage = basePackage;
    }

    @Override
    public Collection<ContextComponent<?>> scanForComponents() {
        List<ContextComponent<?>> result = new ArrayList<>();
        try {
            Class<?>[] classes = getClasses(basePackage);
            for (Class<?> cls : classes) {
                if (cls.isAnnotationPresent(ContextScoped.class)) {
                    var annotation = cls.getAnnotation(ContextScoped.class);
                    var component = new DefaultContextComponentBuilder(cls);
                    var constructors = Arrays.asList(cls.getConstructors());
                    Constructor<?> constructor = null;

                    if (constructors.size() == 1) {
                        constructor = constructors.get(0);
                    } else {
                        var annotatedConstructors = constructors.stream()
                                .filter(c -> c.isAnnotationPresent(ContextTarget.class))
                                .collect(Collectors.toList());
                        if (annotatedConstructors.size() == 0) {
                            var nonEmptyConstructors = constructors.stream()
                                    .filter(c -> c.getParameterCount() > 0)
                                    .collect(Collectors.toList());
                            if (nonEmptyConstructors.size() == 1) {
                                constructor = nonEmptyConstructors.get(0);
                            } else if (nonEmptyConstructors.size() > 1) {
                                throw new ComponentScanException("Impossible to choose constructor for injection. There is more than 1 non-empty constructor");
                            } else {
                                constructor = constructors
                                        .stream()
                                        .filter(c -> c.getParameterCount() == 0)
                                        .findFirst()
                                        .orElseThrow(() -> new ComponentScanException("No constructors present"));
                            }
                        } else if (annotatedConstructors.size() > 1) {
                            throw new ComponentScanException("Impossible to choose constructor for injection. There is more than 1 constructor with ContextTarget annotation");
                        } else {
                            constructor = annotatedConstructors.get(0);
                        }
                    }

                    var types = constructor.getParameterTypes();
                    for (Class<?> type : types) {
                        component.arg(type);
                    }
                    component.scope(annotation.scope());
                    component.instantiationPolicy(annotation.instantiationPolicy());

                    result.add(component.build());
                }
            }
        } catch (Exception e) {
            throw new ComponentScanException("Could not scan package " + basePackage, e);
        }
        return result;
    }

    private static Class<?>[] getClasses(String packageName)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        assert classLoader != null;
        String path = packageName.replace('.', '/');
        Enumeration<URL> resources = classLoader.getResources(path);
        List<File> dirs = new ArrayList<>();
        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            dirs.add(new File(resource.getFile()));
        }
        ArrayList<Class<?>> classes = new ArrayList<>();
        for (File directory : dirs) {
            classes.addAll(findClasses(directory, packageName));
        }
        return classes.toArray(new Class[0]);
    }

    private static List<Class<?>> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class<?>> classes = new ArrayList<>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    assert !file.getName().contains(".");
                    classes.addAll(findClasses(file, packageName + "." + file.getName()));
                } else if (file.getName().endsWith(".class")) {
                    classes.add(Class.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
                }
            }
        }
        return classes;
    }
}
