package com.karmanno.itdepends.core.scanner;

import com.karmanno.itdepends.core.ComponentCandidate;

import java.util.stream.Stream;

/**
 * Interface for scanning external and internal sources for component candidates
 */
public interface Scanner {
    /**
     * Scans for component candidates
     * @return Stream with found component candidates
     */
    Stream<ComponentCandidate<?>> scan();
}
