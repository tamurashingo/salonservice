package com.github.tamurashingo.salonservice.infrastructure.repository;

import com.github.tamurashingo.salonservice.domain.model.UserModel;
import com.github.tamurashingo.salonservice.domain.model.salon.SalonModel;
import com.github.tamurashingo.salonservice.domain.repository.SalonRepository;
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
public class SalonRepositoryTest {

    @Autowired
    private SalonRepository salonRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void registerSalon() {
        UserModel user = new UserModel("salon_register1@email.com", "salon register", "password", UserModel.UserStatus.TEMPORARY, LocalDateTime.now(), LocalDateTime.now());
        userRepository.register(user);

        SalonModel salon = new SalonModel("test salon", "これはテスト用のサロンです", SalonModel.SalonStatus.INVALID, user, LocalDateTime.now(), LocalDateTime.now());
        long result = salonRepository.register(salon);

        assertThat(result, is(1L));
        assertThat(salon.getSalonId(), is(notNullValue()));
    }


}
