package com.github.tamurashingo.salonservice.domain.service;

public class SalonException extends Exception {
    private static final long serialVersionUID = 1L;

    public SalonException(String message) {
        super(message);
    }

    public SalonException(String message, Throwable cause) {
        super(message, cause);
    }
}
