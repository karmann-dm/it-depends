package com.karmanno.itdepends.core.context;

import com.karmanno.itdepends.core.component.DefaultContextComponentBuilder;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.Assert.*;

public class GenericConfigurableContextTest {

    @Test
    public void shouldResolveZeroArgComponents() {
        // when:
        GenericConfigurableContext context = new GenericConfigurableContext()
                .configure(new GenericConfigurationBuilder()
                        .component(new DefaultContextComponentBuilder<>("id1", ArrayList.class))
                        .component(new DefaultContextComponentBuilder<>("id2", HashSet.class)));
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

    @Test
    public void shouldResolveTwoSimpleComponents() {
        // when:
        GenericConfigurableContext context = new GenericConfigurableContext()
                .configure(new GenericConfigurationBuilder()
                        .component(new DefaultContextComponentBuilder<>("id1", FirstClass.class)
                                .arg(SecondClass.class)
                        )
                        .component(new DefaultContextComponentBuilder<>("id2", SecondClass.class)));
        context.resolve();

        // then:
        int a = 0;
    }

    static class FirstClass {
        private final SecondClass secondClass;

        public FirstClass(SecondClass secondClass) {
            this.secondClass = secondClass;
        }
    }

    static class SecondClass {
    }
}
