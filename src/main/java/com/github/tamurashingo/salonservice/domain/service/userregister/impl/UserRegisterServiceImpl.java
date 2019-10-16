package com.github.tamurashingo.salonservice.domain.service.userregister.impl;

import com.github.tamurashingo.salonservice.domain.model.User;
import com.github.tamurashingo.salonservice.domain.model.UserAccount;
import com.github.tamurashingo.salonservice.domain.model.userregister.UserRegisterModel;
import com.github.tamurashingo.salonservice.domain.repository.UserRepository;
import com.github.tamurashingo.salonservice.domain.service.userregister.UserRegisterService;
import com.github.tamurashingo.salonservice.domain.service.userregister.UserRegisterServiceException;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Collection;

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
    public boolean register(UserRegisterModel model) throws UserRegisterServiceException {
        String encodedPassword = passwordEncoder.encode(model.getPassword());
        User user = new User(model.getUserEmail(), model.getUserName(), encodedPassword, User.UserStatus.TEMPORARY, LocalDateTime.now(), LocalDateTime.now());
        userRepository.register(user);
        return true;
    }

    @Override
    public boolean authetication(String passphrase) throws UserRegisterServiceException {
        return false;
    }
}
