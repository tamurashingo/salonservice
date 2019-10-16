package com.github.tamurashingo.salonservice.infrastructure.repository;

import com.github.tamurashingo.salonservice.domain.repository.UserRepository;
import com.github.tamurashingo.salonservice.domain.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NamedParameterJdbcOperations jdbcOperations;


    @Test
    @Transactional
    public void create() {
        User user =  new User("test1@email.com", "test name", "password", User.UserStatus.TEMPORARY, LocalDateTime.now(), LocalDateTime.now());
        userRepository.register(user);
        User user2 = userRepository.findUserByEmail("test1@email.com");
        System.out.println(user2.getUserId());
        System.out.println(user2.getUserName());
    }

    @Test
    @Transactional
    public void update() {
        User user = new User("test2@email.com", "test name", "password", User.UserStatus.TEMPORARY, LocalDateTime.now(), LocalDateTime.now());
        userRepository.register(user);
        user.setUserStatus(User.UserStatus.REGISTERED);

        userRepository.save(user);

        User user2 = userRepository.findUserByEmail("test2@email.com");
        System.out.println(user2.getUserId());
        System.out.println(user2.getUserStatus());
    }
}
