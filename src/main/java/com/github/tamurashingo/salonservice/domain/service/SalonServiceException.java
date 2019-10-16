package com.github.tamurashingo.salonservice.domain.service;

public class SalonServiceException extends Exception {
    private static final long serialVersionUID = 1L;

    public SalonServiceException(String message) {
        super(message);
    }

    public SalonServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
