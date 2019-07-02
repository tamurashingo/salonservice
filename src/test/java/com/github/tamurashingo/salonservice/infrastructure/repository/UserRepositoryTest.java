package com.github.tamurashingo.salonservice.infrastructure.repository;

import com.github.tamurashingo.salonservice.domain.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;

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
        User user =  new User(1L, "test@email.com", "test name", "password", User.UserStatus.TEMPORARY, Calendar.getInstance().getTime(), Calendar.getInstance().getTime());
        userRepository.register(user);
        User user2 = userRepository.findUserByEmail("test@email.com");
        System.out.println(user2.getUserId());
        System.out.println(user2.getUsername());
    }
}
