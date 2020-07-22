package com.karmanno.itdepends.core.component;

import com.karmanno.itdepends.core.exception.ComponentFactoryException;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

import static org.junit.Assert.*;

public class DefaultContextComponentFactoryTest {

    @Test
    public void shouldCreateComponentFactory() {
        // when:
        var componentFactory = new DefaultContextComponentFactory<>(
                BigDecimal.class,
                BigInteger.class,
                MathContext.class
        );

        // then:
        assertEquals(2, componentFactory.getArgumentClasses().length);
        assertEquals(BigInteger.class, componentFactory.getArgumentClasses()[0]);
        assertEquals(MathContext.class, componentFactory.getArgumentClasses()[1]);
        assertEquals(BigDecimal.class, componentFactory.getComponentClass());
    }

    @Test
    public void shouldCreateObjectCorrectly() {
        // given:
        var componentFactory = new DefaultContextComponentFactory<>(
                BigDecimal.class,
                BigInteger.class,
                MathContext.class
        );

        // when:
        BigDecimal created = componentFactory.create(BigInteger.valueOf(1234), MathContext.DECIMAL32);

        // then:
        assertEquals("1234", created.toString());
        assertEquals(1234, created.intValue());
    }

    @Test(expected = ComponentFactoryException.class)
    public void  shouldFailOnNullArguments() {
        // when:
        var componentFactory = new DefaultContextComponentFactory<>(
                BigDecimal.class,
                BigInteger.class,
                MathContext.class
        );

        // then:
        componentFactory.checkTypeMismatch(null);
    }

    @Test(expected = ComponentFactoryException.class)
    public void shouldFailOnArgumentsCountMismatch() {
        // when:
        var componentFactory = new DefaultContextComponentFactory<>(
                BigDecimal.class,
                BigInteger.class,
                MathContext.class
        );

        // then:
        componentFactory.checkTypeMismatch(123, new StringBuilder(), "abc");
    }

    @Test(expected = ComponentFactoryException.class)
    public void shouldFailOnArgumentTypeMismatch() {
        // when:
        var componentFactory = new DefaultContextComponentFactory<>(
                BigDecimal.class,
                BigInteger.class,
                MathContext.class
        );

        // then:
        componentFactory.checkTypeMismatch(BigInteger.valueOf(1234), new StringBuffer());
    }
}
