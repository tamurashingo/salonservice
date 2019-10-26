package com.github.tamurashingo.salonservice.infrastructure.repository;

import com.github.tamurashingo.salonservice.domain.model.UserModel;
import com.github.tamurashingo.salonservice.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

@ExtendWith(SpringExtension.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void create() {
        UserModel user = new UserModel("test1@email.com", "test name", "password", UserModel.UserStatus.TEMPORARY, LocalDateTime.now(), LocalDateTime.now());
        long result = userRepository.register(user);
        assertAll("登録したUser",
                () -> assertEquals(1L, result),
                () -> assertNotNull(user.getUserId())
        );

        UserModel user2 = userRepository.findUserByEmail("test1@email.com");

        assertAll("登録した内容の確認",
                () -> assertNotNull(user2),
                () -> assertEquals("test1@email.com", user2.getUserEmail()),
                () -> assertEquals("test name", user2.getUserName()),
                () -> assertEquals("password", user.getPassword()),
                () -> assertEquals(UserModel.UserStatus.TEMPORARY, user2.getUserStatus())
        );

    }

    @Test
    @Transactional
    public void update() {
        UserModel user = new UserModel("test2@email.com", "test name", "password", UserModel.UserStatus.TEMPORARY, LocalDateTime.now(), LocalDateTime.now());
        userRepository.register(user);
        assertNotNull(user.getUserId());

        user.setUserStatus(UserModel.UserStatus.REGISTERED);

        long result = userRepository.save(user);

        UserModel user2 = userRepository.findUserByEmail("test2@email.com");

        assertAll("Userの更新の確認",
                () -> assertEquals(1L, result),
                () -> assertNotNull(user2),
                () -> assertEquals("test2@email.com", user.getUserEmail()),
                () -> assertEquals("test name", user.getUserName()),
                () -> assertEquals("password", user.getPassword()),
                () -> assertEquals(UserModel.UserStatus.REGISTERED, user.getUserStatus())
        );
    }
}
