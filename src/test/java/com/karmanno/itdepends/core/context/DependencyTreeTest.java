package com.karmanno.itdepends.core.context;

import com.karmanno.itdepends.core.candidate.ComponentCandidate;
import com.karmanno.itdepends.core.context.DependencyTreeTestFixtures.SomeAnotherClass;
import com.karmanno.itdepends.core.context.DependencyTreeTestFixtures.SomeClass;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DependencyTreeTest {
    @Test
    public void addDependency_whenThereIsNoFactorLevel_thenShouldAddNewOne() {
        // given:
        var candidate = new ComponentCandidate<>(SomeClass.class);
        var tree = new DependencyTree();

        // when:
        tree.addDependency(candidate);

        // then:
        assertThat(tree.getFactorLevels().firstKey()).isEqualTo(3);
        assertThat(tree.getFactorLevels().get(3).getUnresolved()).hasSize(1);
        assertThat(tree.getFactorLevels().get(3).getUnresolved().get(0).getCandidateClass()).isEqualTo(SomeClass.class);
        assertThat(tree.getReferences()).hasSize(1);
        assertThat(tree.getReferences().values().stream().findFirst().get()).isNotNull();
    }

    @Test
    public void addDependency_whenThereIsAlreadyFactorLevel_thenShouldAddOneMore() {
        // given:
        var candidate = new ComponentCandidate<>(SomeClass.class);
        var tree = new DependencyTree();
        tree.addDependency(candidate);
        var another = new ComponentCandidate<>(SomeAnotherClass.class);

        // when:
        tree.addDependency(another);

        // then:
        assertThat(tree.getFactorLevels().firstKey()).isEqualTo(3);
        assertThat(tree.getFactorLevels().get(3).getUnresolved()).hasSize(2);
        assertThat(tree.getFactorLevels().get(3).getUnresolved().get(1).getCandidateClass()).isEqualTo(SomeAnotherClass.class);
        assertThat(tree.getReferences()).hasSize(2);
    }
}
