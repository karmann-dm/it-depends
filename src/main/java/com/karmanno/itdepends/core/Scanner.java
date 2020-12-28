package com.karmanno.itdepends.core;

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
