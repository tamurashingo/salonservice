package com.github.tamurashingo.salonservice.infrastructure.repository;

import com.github.tamurashingo.salonservice.domain.model.UserModel;
import com.github.tamurashingo.salonservice.domain.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
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

        assertThat(result, is(1L));
        assertThat(user.getUserId(), is(notNullValue()));

        UserModel user2 = userRepository.findUserByEmail("test1@email.com");

        assertThat(user2, notNullValue());
        assertThat(user2.getUserEmail(), is("test1@email.com"));
        assertThat(user2.getUserName(), is("test name"));
        assertThat(user2.getPassword(), is("password"));
        assertThat(user2.getUserStatus(), is(UserModel.UserStatus.TEMPORARY));
    }

    @Test
    @Transactional
    public void update() {
        UserModel user = new UserModel("test2@email.com", "test name", "password", UserModel.UserStatus.TEMPORARY, LocalDateTime.now(), LocalDateTime.now());
        userRepository.register(user);

        assertThat(user.getUserId(), is(notNullValue()));

        user.setUserStatus(UserModel.UserStatus.REGISTERED);

        long result = userRepository.save(user);

        assertThat(result, is(1L));

        UserModel user2 = userRepository.findUserByEmail("test2@email.com");
        assertThat(user2, notNullValue());
        assertThat(user2.getUserEmail(), is("test2@email.com"));
        assertThat(user2.getUserName(), is("test name"));
        assertThat(user2.getPassword(), is("password"));
        assertThat(user2.getUserStatus(), is(UserModel.UserStatus.REGISTERED));
    }
}
