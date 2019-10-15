package com.github.tamurashingo.salonservice.domain.service.impl;

import com.github.tamurashingo.salonservice.domain.model.User;
import com.github.tamurashingo.salonservice.domain.model.UserAccount;
import com.github.tamurashingo.salonservice.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserDetailsServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username == null || "".equals(username)) {
            throw new UsernameNotFoundException("Username is empty");
        }

        User user = userRepository.findUserByEmail(username);
        if (user == null) {
            System.out.println("User not found:" + username);
            throw new UsernameNotFoundException("User not found:" + username);
        }
        if (!user.isEnabled()) {
            System.out.println("User not enabled:" + username);
            throw new UsernameNotFoundException("User not found:" + username);
        }

        UserAccount account = new UserAccount(user, getAuthorities(user));

        return account;
    }

    private Collection<GrantedAuthority> getAuthorities(User user) {
        return AuthorityUtils.createAuthorityList("ROLE_USER");
    }

    @Transactional
    public void registerUser(String username, String password) {
        User user = new User(username, username, password, User.UserStatus.TEMPORARY, LocalDateTime.now(), LocalDateTime.now());
        userRepository.register(user);
    }

}
