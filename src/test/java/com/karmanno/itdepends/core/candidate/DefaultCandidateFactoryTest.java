package com.karmanno.itdepends.core.candidate;

import com.karmanno.itdepends.core.candidate.DefaultCandidateFactoryTestFixtures.SomeClass;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultCandidateFactoryTest {
    @Test
    void create_whenArgumentsAreCorrect_thenObjectIsCreated() {
        // given:
        var candidate = new ComponentCandidate<>(SomeClass.class);
        var candidateFactory = new DefaultCandidateFactory<>(
                candidate,
                List.of(
                        String.class,
                        List.class,
                        Map.class
                ).toArray(Class[]::new)
        );

        // when:
        var someClassInstance = candidateFactory.create(null,
                "string",
                List.of(1, 2, 3),
                Map.of(1.1, (short)1, 2.2, (short)2)
        );

        // then:
        assertThat(someClassInstance.getString()).isEqualTo("string");
        assertThat(someClassInstance.getIntegers()).containsExactlyInAnyOrder(1, 2, 3);
        assertThat(someClassInstance.getMap()).containsOnlyKeys(1.1, 2.2);
        assertThat(someClassInstance.getMap()).containsEntry(1.1, (short)1);
        assertThat(someClassInstance.getMap()).containsEntry(2.2, (short)2);
    }
}
