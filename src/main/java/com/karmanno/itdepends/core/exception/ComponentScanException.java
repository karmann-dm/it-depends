package com.karmanno.itdepends.core.exception;

public class ComponentScanException extends RuntimeException {
    public ComponentScanException(String message) {
        super(message);
    }

    public ComponentScanException(String message, Exception clause) {
        super(message, clause);
    }
}
