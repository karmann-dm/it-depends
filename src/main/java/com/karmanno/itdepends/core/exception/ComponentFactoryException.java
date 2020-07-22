package com.karmanno.itdepends.core.exception;

public class ComponentFactoryException extends RuntimeException {
    public ComponentFactoryException(String message) {
        super(message);
    }

    public ComponentFactoryException(String message, Exception clause) {
        super(message, clause);
    }
}
