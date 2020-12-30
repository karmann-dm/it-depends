package com.karmanno.itdepends.core.scanner;

public class CandidateCreationException extends RuntimeException {
    public CandidateCreationException(String message) {
        super(message);
    }

    public static CandidateCreationException componentCandidateClassIsNull() {
        return new CandidateCreationException("Couldn't create component candidate with null class");
    }
}
