package com.karmanno.itdepends.core;

import com.karmanno.itdepends.core.component.DefaultContextComponentBuilder;
import com.karmanno.itdepends.core.context.ConfigurableContext;
import com.karmanno.itdepends.core.context.GenericConfigurableContext;
import com.karmanno.itdepends.core.context.GenericConfigurationBuilder;
import com.karmanno.itdepends.core.property.PropertySource;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.ArrayList;

public class GenericConfigurableContextTest {
    @Test
    public void shouldConfigurePropertySource() throws IOException {
        ConfigurableContext configurableContext = new GenericConfigurableContext();
        var configuration = configurableContext.configure(
                new GenericConfigurationBuilder()
                    .propertySource(new PropertySource("properties1.properties"))
                    .propertySource(new PropertySource("properties2.properties"))
        );

        var sources = new ArrayList<>(configuration.getPropertySources());
        Assert.assertEquals(2, sources.size());
        Assert.assertEquals("ert", sources.get(0).getProperties().get("someFirstUniqueProps"));
    }

    @Test
    public void shouldResolveComponentAfterAdd() {
        ConfigurableContext configurableContext = new GenericConfigurableContext();
        configurableContext.configure(
                new GenericConfigurationBuilder()
                        .component(
                                new DefaultContextComponentBuilder<>(BigDecimal.class)
                                    .arg(BigInteger.class)
                                    .arg(MathContext.class)
                        )
        );

        var bigDecimalComponent = configurableContext.getComponentByClass(BigDecimal.class).get();
        bigDecimalComponent.componentFactory().create(BigInteger.valueOf(10000), MathContext.DECIMAL32);
    }

    @Test
    public void shouldReturnEmptyOptionalWhenBeanDoesNotExists() {
        ConfigurableContext configurableContext = new GenericConfigurableContext();
        configurableContext.configure(
                new GenericConfigurationBuilder()
        );

        Assert.assertTrue(configurableContext.getComponentByClass(BigDecimal.class).isEmpty());
    }
}
