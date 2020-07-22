package com.karmanno.itdepends.core.component;

import com.karmanno.itdepends.core.Scope;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class DefaultContextComponentTest {
    @Test
    public void shouldSaveComponentFieldsByPackagePrivateConstructor() {
        // when
        ContextComponent<BigDecimal> contextComponent = new DefaultContextComponent<>(
                BigDecimal.class,
                Scope.PROTOTYPE,
                new DefaultContextComponentFactory<>(BigDecimal.class)
        );

        // then
        assertEquals(BigDecimal.class, contextComponent.componentClass());
        assertEquals(DefaultContextComponentFactory.class, contextComponent.componentFactory().getClass());
        assertEquals(Scope.PROTOTYPE, contextComponent.scope());
    }

    @Test(expected = AssertionError.class)
    public void shouldFailOnNullFields() {
        // when
        ContextComponent<Void> contextComponent = new DefaultContextComponent<>(
                null,
                null,
                null
        );
    }
}
