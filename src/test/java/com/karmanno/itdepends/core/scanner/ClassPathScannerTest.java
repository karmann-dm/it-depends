package com.karmanno.itdepends.core.scanner;

import com.karmanno.itdepends.core.candidate.ComponentCandidate;
import com.karmanno.itdepends.core.scanner.ClassPathScannerTestFixtures.SomeAnotherClass;
import com.karmanno.itdepends.core.scanner.ClassPathScannerTestFixtures.SomeClass;
import com.karmanno.itdepends.core.scanner.ClassPathScannerTestFixtures.SomeClassToIgnore;
import com.karmanno.itdepends.core.scanner.ClassPathScannerTestFixtures.subpackage.SomeThirdClass;
import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ClassPathScannerTest {
    private static final String PACKAGE = "com.karmanno.itdepends.core.scanner.ClassPathScannerTestFixtures";

    @Test
    public void _constructor_whenPackageNameIsNull_then() {
        // given/when/then:
        assertThatThrownBy(() -> new ClassPathScanner(null))
            .isExactlyInstanceOf(ClassPathScannerException.class)
            .hasMessage("Could not create class path scanner with null package");
    }

    @Test
    public void createCandidate_whenComponentCandidateIsNull_thenNoCandidateIsCreated() {
        // given:
        var scanner = new ClassPathScanner(PACKAGE);

        // when/then:
        assertThatThrownBy(() -> scanner.createCandidate(null))
                .isExactlyInstanceOf(CandidateCreationException.class)
                .hasMessage("Couldn't create component candidate with null class");
    }

    @Test
    public void createCandidate_whenComponentCandidateIsNotNull_thenCandidateIsCreated() {
        // given:
        var scanner = new ClassPathScanner(PACKAGE);

        // when:
        var candidate = scanner.createCandidate(SomeClass.class);

        // then:
        assertThat(candidate.getCandidateClass()).isEqualTo(SomeClass.class);
    }

    @Test
    public void extractPath_whenUrlIsValid_thenPathIsCreated() throws Exception {
        // given:
        var scanner = new ClassPathScanner(PACKAGE);
        var cl = Thread.currentThread().getContextClassLoader();
        var resource = cl.getResources(PACKAGE.replace(".", "/")).nextElement();

        // when:
        var path = scanner.extractPath(resource).findFirst().get();

        // then:
        assertThat(path.getFileName().toString()).isEqualTo("ClassPathScannerTestFixtures");
    }

    @Test
    public void isClass_whenPathIsClass_thenReturnTrue() throws Exception {
        // given:
        var cl = Thread.currentThread().getContextClassLoader();
        var resource = cl.getResources(PACKAGE.replace(".", "/")).nextElement();
        var path = Paths.get(resource.toURI());
        var scanner = new ClassPathScanner(PACKAGE);
        var file = Files.find(path, 2,
                (path1, basicFileAttributes) -> FilenameUtils
                        .getExtension(path1.getFileName().toString()).equalsIgnoreCase("class")
        ).findFirst().get();

        // when:
        var isClass = scanner.isClass(file);

        // then:
        assertThat(isClass).isTrue();
    }

    @Test
    public void isClass_whenPathIsNotClass_thenReturnFalse() throws Exception {
        // given:
        var cl = Thread.currentThread().getContextClassLoader();
        var resource = cl.getResources(PACKAGE.replace(".", "/")).nextElement();
        var path = Paths.get(resource.toURI());
        var scanner = new ClassPathScanner(PACKAGE);
        var file = Files.find(path, 2,
                (path1, basicFileAttributes) -> basicFileAttributes.isDirectory()
        ).findFirst().get();

        // when:
        var isClass = scanner.isClass(file);

        // then:
        assertThat(isClass).isFalse();
    }

    @Test
    public void findClasses_directory_whenSearchInFixtures_thenReturnEveryClass() throws Exception {
        // given:
        var cl = Thread.currentThread().getContextClassLoader();
        var resource = cl.getResources(PACKAGE.replace(".", "/")).nextElement();
        var path = Paths.get(resource.toURI());
        var scanner = new ClassPathScanner(PACKAGE);

        // when:
        var classes = scanner.findClasses(path).collect(Collectors.toList());

        // then:
        assertThat(classes).contains(
                SomeAnotherClass.class,
                SomeClass.class,
                SomeClassToIgnore.class,
                SomeThirdClass.class
        );
    }

    @Test
    public void findClasses_whenSearchInFixtures_thenReturnOnlyAnnotatedClass() {
        // given:
        var scanner = new ClassPathScanner(PACKAGE);

        // when:
        var classes = scanner.findClasses().collect(Collectors.toList());

        // then:
        assertThat(classes).contains(
                SomeClass.class,
                SomeAnotherClass.class,
                SomeThirdClass.class
        );
    }

    @Test
    public void scan_whenSearchInFixtures_thenReturnCandidatesOnlyForAnnotatedClass() {
        // given:
        var scanner = new ClassPathScanner(PACKAGE);

        // when:
        var candidates = scanner.scan();

        // then:
        assertThat(candidates).contains(
                new ComponentCandidate<>(SomeClass.class),
                new ComponentCandidate<>(SomeAnotherClass.class),
                new ComponentCandidate<>(SomeThirdClass.class)
        );
    }
}
