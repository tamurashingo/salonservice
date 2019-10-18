package com.github.tamurashingo.salonservice.domain.service.salon;

import com.github.tamurashingo.salonservice.domain.service.SalonException;

public class SalonServiceException extends SalonException {
    private static final long serialVersionUID = 1L;

    public SalonServiceException(String message) {
        super(message);
    }

    public SalonServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
