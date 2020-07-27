package com.karmanno.itdepends.core.context;

import com.karmanno.itdepends.core.component.DefaultContextComponentBuilder;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.Assert.*;

public class GenericConfigurableContextTest {

    @Test
    @Ignore
    public void shouldResolveZeroArgComponents() {
        // when:
        GenericConfigurableContext context = new GenericConfigurableContext()
                .configure(new GenericConfigurationBuilder()
                        .component(new DefaultContextComponentBuilder<>(ArrayList.class))
                        .component(new DefaultContextComponentBuilder<>(HashSet.class)));
        context.resolve();

        // then:
        var listOpt = context.getInstanceByClass(ArrayList.class);
        var setOpt = context.getInstanceByClass(HashSet.class);

        assertFalse(listOpt.isEmpty());
        assertFalse(setOpt.isEmpty());

        var list = listOpt.get();
        var set = setOpt.get();

        assertEquals(ArrayList.class, list.getClass());
        assertEquals(HashSet.class, set.getClass());
    }
}
