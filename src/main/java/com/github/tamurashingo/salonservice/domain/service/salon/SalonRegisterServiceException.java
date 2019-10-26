package com.github.tamurashingo.salonservice.domain.service.salon;

import com.github.tamurashingo.salonservice.domain.service.SalonException;

public class SalonRegisterServiceException extends SalonException {
    private static final long serialVersionUID = 1L;

    public SalonRegisterServiceException(String message) {
        super(message);
    }

    public SalonRegisterServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
