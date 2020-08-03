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
        // given:
        GenericConfigurableContext context = new GenericConfigurableContext()
                .configure(new GenericConfigurationBuilder()
                        .component(new DefaultContextComponentBuilder<>("id1", FirstClass.class)
                                .arg(SecondClass.class)
                        )
                        .component(new DefaultContextComponentBuilder<>("id2", SecondClass.class)));

        // when:
        context.resolve();

        // then:
        var second = context.getInstanceByClass(SecondClass.class);
        var first = context.getInstanceByClass(FirstClass.class);

        assertTrue(first.isPresent());
        assertTrue(second.isPresent());
        assertEquals(SecondClass.class, second.get().getClass());
        assertEquals(FirstClass.class, first.get().getClass());
        assertEquals(1.0, second.get().val, 0.001);
        assertNotNull(first.get().secondClass);
        assertEquals(1.0, first.get().secondClass.val, 0.001);
    }

    @Test
    public void shouldResolveComplexDependencies() {
        // given:
        GenericConfigurableContext configurableContext = new GenericConfigurableContext()
                .configure(
                        new GenericConfigurationBuilder()
                            .component(
                                    new DefaultContextComponentBuilder<>("id1", ComplexTop.class)
                                        .arg(ComplexFirstService.class)
                                        .arg(ComplexSecondService.class)
                            )
                            .component(
                                    new DefaultContextComponentBuilder<>("id2", ComplexFirstService.class)
                                        .arg(ComplexProvider.class)
                                        .arg(ComplexGateway.class)
                                        .arg(ComplexFirstRepository.class)
                            )
                            .component(
                                    new DefaultContextComponentBuilder<>("id3", ComplexSecondService.class)
                                        .arg(ComplexFirstRepository.class)
                                        .arg(ComplexSecondRepository.class)
                            )
                            .component(new DefaultContextComponentBuilder<>("id4", ComplexProvider.class))
                            .component(
                                    new DefaultContextComponentBuilder<>("id5", ComplexGateway.class)
                                        .arg(ComplexHttpClient.class)
                            )
                            .component(new DefaultContextComponentBuilder<>("id6", ComplexFirstRepository.class))
                            .component(
                                    new DefaultContextComponentBuilder<>("id7", ComplexSecondRepository.class)
                                        .arg(ComplexDb.class)
                            )
                            .component(new DefaultContextComponentBuilder<>("id8", ComplexDb.class))
                            .component(new DefaultContextComponentBuilder<>("id9", ComplexHttpClient.class))
                );
        // when:
        configurableContext.resolve();

        // then:
        var topOptional = configurableContext.getInstanceByClass(ComplexTop.class);
        assertTrue(topOptional.isPresent());
    }

    static class FirstClass {
        private final SecondClass secondClass;

        public FirstClass(SecondClass secondClass) {
            this.secondClass = secondClass;
        }
    }

    static class SecondClass {
        private final double val = 1.0;

        public double getVal() {
            return val;
        }
    }

    static class ComplexTop {
        private final ComplexFirstService firstService;
        private final ComplexSecondService secondService;

        public ComplexTop(ComplexFirstService firstService, ComplexSecondService secondService) {
            this.firstService = firstService;
            this.secondService = secondService;
        }
    }

    static class ComplexFirstService {
        private final ComplexProvider complexProvider;
        private final ComplexGateway complexGateway;
        private final ComplexFirstRepository complexFirstRepository;

        public ComplexFirstService(ComplexProvider provider, ComplexGateway gateway, ComplexFirstRepository firstRepository) {
            this.complexProvider = provider;
            this.complexGateway = gateway;
            this.complexFirstRepository = firstRepository;
        }
    }

    static class ComplexSecondService {
        private final ComplexFirstRepository firstRepository;
        private final ComplexSecondRepository secondRepository;

        public ComplexSecondService(ComplexFirstRepository firstRepository, ComplexSecondRepository secondRepository) {
            this.firstRepository = firstRepository;
            this.secondRepository = secondRepository;
        }
    }

    static class ComplexProvider {
        public ComplexProvider() {
            System.out.println("Initialized ComplexProvider");
        }
    }

    static class ComplexFirstRepository {
        public ComplexFirstRepository() {
            System.out.println("Initialized ComplexFirstRepository");
        }
    }

    static class ComplexSecondRepository {
        private final ComplexDb complexDb;

        public ComplexSecondRepository(ComplexDb complexDb) {
            this.complexDb = complexDb;
            System.out.println("Initialized ComplexSecondRepository");
        }
    }

    static class ComplexGateway {
        private final ComplexHttpClient client;

        public ComplexGateway(ComplexHttpClient client) {
            this.client = client;
            System.out.println("Initialized ComplexGateway");
        }
    }

    static class ComplexHttpClient {
        public ComplexHttpClient() {
            System.out.println("Initialized ComplexHttpClient");
        }
    }

    static class ComplexDb {
        public ComplexDb() {
            System.out.println("Initialized DB");
        }
    }
}
