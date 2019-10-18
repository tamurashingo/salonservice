package com.github.tamurashingo.salonservice.domain.service.userregister;

import com.github.tamurashingo.salonservice.domain.service.SalonException;

public class UserRegisterException extends SalonException {

    private static final long serialVersionUID = 1L;

    public UserRegisterException(String message) {
        super(message);
    }

    public UserRegisterException(String message, Throwable cause) {
        super(message, cause);
    }
}
