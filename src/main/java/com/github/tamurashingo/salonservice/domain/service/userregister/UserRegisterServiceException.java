package com.github.tamurashingo.salonservice.domain.service.userregister;

import com.github.tamurashingo.salonservice.domain.service.SalonServiceException;

public class UserRegisterServiceException extends SalonServiceException {

    private static final long serialVersionUID = 1L;

    public UserRegisterServiceException(String message) {
        super(message);
    }

    public UserRegisterServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
