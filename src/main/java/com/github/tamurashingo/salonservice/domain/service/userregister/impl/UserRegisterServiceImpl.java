package com.github.tamurashingo.salonservice.domain.service.userregister.impl;

import com.github.tamurashingo.salonservice.domain.model.UserModel;
import com.github.tamurashingo.salonservice.domain.model.userregister.UserRegisterModel;
import com.github.tamurashingo.salonservice.domain.repository.UserRepository;
import com.github.tamurashingo.salonservice.domain.service.userregister.UserRegisterService;
import com.github.tamurashingo.salonservice.domain.service.userregister.UserRegisterException;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class UserRegisterServiceImpl implements UserRegisterService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Lazy
    public UserRegisterServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public boolean register(UserRegisterModel model) throws UserRegisterException {
        String encodedPassword = passwordEncoder.encode(model.getPassword());
        UserModel user = new UserModel(model.getUserEmail(), model.getUserName(), encodedPassword, UserModel.UserStatus.TEMPORARY, LocalDateTime.now(), LocalDateTime.now());
        userRepository.register(user);
        return true;
    }

    @Override
    public boolean authetication(String passphrase) throws UserRegisterException {
        return false;
    }
}
