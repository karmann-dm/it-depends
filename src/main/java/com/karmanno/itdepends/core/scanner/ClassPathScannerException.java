package com.karmanno.itdepends.core.scanner;

public class ClassPathScannerException extends RuntimeException {
    public ClassPathScannerException(String message) {
        super(message);
    }

    public static ClassPathScannerException packageIsNull() {
        return new ClassPathScannerException("Could not create class path scanner with null package");
    }
}
