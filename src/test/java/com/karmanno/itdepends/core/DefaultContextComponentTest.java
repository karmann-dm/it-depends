package com.karmanno.itdepends.core;

import com.karmanno.itdepends.core.component.ContextComponent;
import com.karmanno.itdepends.core.component.DefaultContextComponentBuilder;
import com.karmanno.itdepends.core.component.DefaultContextComponentFactory;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class DefaultContextComponentTest {
    @Test
    public void shouldConstructCorrectly() {
        // when
        ContextComponent<BigDecimal> contextComponent = new DefaultContextComponentBuilder<>(BigDecimal.class)
                .arg(BigInteger.class)
                .arg(MathContext.class)
                .build();
        var instance = contextComponent.componentFactory()
                .create(BigInteger.valueOf(10000), MathContext.DECIMAL32);

        // then
        Assert.assertEquals(BigDecimal.class, contextComponent.componentClass());
        Assert.assertEquals(DefaultContextComponentFactory.class, contextComponent.componentFactory().getClass());
        Assert.assertEquals(BigDecimal.class, instance.getClass());
        Assert.assertEquals(10000, instance.intValue());
    }
}
