package com.github.tamurashingo.salonservice.domain.service.userregister;

import com.github.tamurashingo.salonservice.domain.model.userregister.UserRegisterModel;

public interface UserRegisterService {

    boolean register(UserRegisterModel model) throws UserRegisterServiceException;

    boolean authetication(String passphrase) throws UserRegisterServiceException;

}
