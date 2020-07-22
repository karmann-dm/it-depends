package com.karmanno.itdepends.core.component;

import com.karmanno.itdepends.core.Scope;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

import static org.junit.Assert.*;

public class DefaultContextComponentBuilderTest {
    @Test
    public void shouldCreateComponent() {
        // when:
        var component = new DefaultContextComponentBuilder<>(BigDecimal.class)
                .arg(BigInteger.class)
                .arg(MathContext.class)
                .scope(Scope.PROTOTYPE)
                .factory(new DefaultContextComponentFactory<>(BigDecimal.class, BigInteger.class, MathContext.class))
                .build();

        // then:
        assertEquals(BigDecimal.class, component.componentClass());
        assertEquals(DefaultContextComponentFactory.class, component.componentFactory().getClass());
        assertEquals(2, ((DefaultContextComponentFactory<?>)component.componentFactory()).getArgumentClasses().length);
        assertEquals(BigInteger.class, ((DefaultContextComponentFactory<?>)component.componentFactory()).getArgumentClasses()[0]);
        assertEquals(MathContext.class, ((DefaultContextComponentFactory<?>)component.componentFactory()).getArgumentClasses()[1]);
        assertEquals(BigDecimal.class, ((DefaultContextComponentFactory<?>)component.componentFactory()).getComponentClass());
        assertEquals(Scope.PROTOTYPE, component.scope());
    }

    @Test
    public void shouldSubstituteScopeByDefault() {
        // when:
        var component = new DefaultContextComponentBuilder<>(BigDecimal.class)
                .arg(BigInteger.class)
                .arg(MathContext.class)
                .factory(new DefaultContextComponentFactory<>(BigDecimal.class, BigInteger.class, MathContext.class))
                .build();

        // then:
        assertEquals(Scope.SINGLETON, component.scope());
    }

    @Test
    public void shouldSubstituteComponentFactory() {
        // when:
        var component = new DefaultContextComponentBuilder<>(BigDecimal.class)
                .arg(BigInteger.class)
                .arg(MathContext.class)
                .build();

        // then:
        assertEquals(DefaultContextComponentFactory.class, component.componentFactory().getClass());
    }

    @Test(expected = AssertionError.class)
    public void shouldFailOnNullComponentClass() {
        // when:
        var component = new DefaultContextComponentBuilder<>(null)
                .arg(BigInteger.class)
                .arg(MathContext.class)
                .build();
    }
}
