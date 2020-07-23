package com.karmanno.itdepends.core.scan;

import com.karmanno.itdepends.core.annotated_correct_examples.SomeClass;
import com.karmanno.itdepends.core.component.ContextComponent;
import com.karmanno.itdepends.core.component.DefaultContextComponentFactory;
import com.karmanno.itdepends.core.examples.multiple_correct_examples.SomeFirstClass;
import com.karmanno.itdepends.core.examples.multiple_correct_examples.SomeSecondClass;
import com.karmanno.itdepends.core.examples.multiple_correct_examples.SomeThirdClass;
import com.karmanno.itdepends.core.examples.multiple_correct_examples.subpackage.SomeForthClass;
import com.karmanno.itdepends.core.exception.ComponentScanException;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.*;

public class PackageComponentScannerTest {
    @Test
    public void shouldScanPlainComponentsFromExamplePackage() {
        // given:
        PackageComponentScanner packageComponentScanner = new PackageComponentScanner("com.karmanno.itdepends.core.examples");

        // when:
        var components = packageComponentScanner.scanForComponents();

        // then:
        assertEquals(4, components.size());
        assertTrue(componentExists(components, SomeFirstClass.class));
        assertTrue(componentExists(components, SomeSecondClass.class));
        assertTrue(componentExists(components, SomeThirdClass.class));
        assertTrue(componentExists(components, SomeForthClass.class));
    }

    @Test
    public void shouldScanAnnotatedComponentsFromExamplePackage() {
        // given:
        PackageComponentScanner packageComponentScanner = new PackageComponentScanner("com.karmanno.itdepends.core.annotated_correct_examples");

        // when:
        var components = packageComponentScanner.scanForComponents();

        // then:
        assertEquals(1, components.size());
        var list = new ArrayList<>(components);
        assertEquals(list.get(0).componentClass(), SomeClass.class);
        assertEquals(list.get(0).componentFactory().getClass(), DefaultContextComponentFactory.class);
        assertEquals(2, ((DefaultContextComponentFactory<?>)list.get(0).componentFactory()).getArgumentClasses().length);
        assertEquals(BigInteger.class, ((DefaultContextComponentFactory<?>)list.get(0).componentFactory()).getArgumentClasses()[0]);
        assertEquals(BigDecimal.class, ((DefaultContextComponentFactory<?>)list.get(0).componentFactory()).getArgumentClasses()[1]);
    }

    @Test(expected = ComponentScanException.class)
    public void shouldFailOnMultipleNotEmptyConstructor() {
        // given:
        PackageComponentScanner packageComponentScanner = new PackageComponentScanner("com.karmanno.itdepends.core.plain_examples");

        // when:
        packageComponentScanner.scanForComponents();
    }

    @Test(expected = ComponentScanException.class)
    public void shouldFailOnMultipleAnnotatedConstructor() {
        // given:
        PackageComponentScanner packageComponentScanner = new PackageComponentScanner("com.karmanno.itdepends.core.annotated_examples");

        // when:
        packageComponentScanner.scanForComponents();
    }

    private boolean componentExists(Collection<ContextComponent<?>> components, Class<?> aClass) {
        return components.stream()
                .filter(component -> component.componentClass().equals(aClass))
                .count() == 1;
    }
}
