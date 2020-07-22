package com.karmanno.itdepends.core.scan;

import com.karmanno.itdepends.core.component.ContextComponent;
import com.karmanno.itdepends.core.component.DefaultContextComponentBuilder;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

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
                var component = new DefaultContextComponentBuilder(cls);
                var constructor = Arrays.stream(cls.getConstructors())
                        .filter(c -> c.isAnnotationPresent(ContextTarget.class))
                        .findFirst()
                        .orElseGet(() ->
                                Arrays.stream(cls.getConstructors())
                                        .findFirst()
                                        .orElseThrow(() ->
                                                new RuntimeException("Couldn't find an appropriate constructor")
                                        )
                        );
                var types = constructor.getParameterTypes();
                for (Class<?> type : types) {
                    component.arg(type);
                }
                result.add(component.build());
            }
        } catch (Exception e) {
            return Collections.emptyList();
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
