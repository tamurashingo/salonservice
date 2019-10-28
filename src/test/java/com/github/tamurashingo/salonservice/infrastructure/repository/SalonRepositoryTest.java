package com.github.tamurashingo.salonservice.infrastructure.repository;

import com.github.tamurashingo.salonservice.domain.model.UserModel;
import com.github.tamurashingo.salonservice.domain.model.salon.SalonModel;
import com.github.tamurashingo.salonservice.domain.repository.SalonRepository;
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
public class SalonRepositoryTest {

    @Autowired
    private SalonRepository salonRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void registerSalon() {
        //UserModel user = new UserModel("salon_register1@email.com", "salon register", "password", UserModel.UserStatus.TEMPORARY, LocalDateTime.now(), LocalDateTime.now());
        UserModel user = UserModel.builder()
                .userEmail("salon_register1@email.com")
                .userName("salon register")
                .password("password")
                .userStatus(UserModel.UserStatus.TEMPORARY)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .build();

        userRepository.register(user);

        SalonModel salon = new SalonModel("test salon", "これはテスト用のサロンです", SalonModel.SalonStatus.INVALID, user, LocalDateTime.now(), LocalDateTime.now());
        long result = salonRepository.register(salon);

        assertAll("登録したSalon",
                () -> assertEquals(1L, result),
                () -> assertNotNull(salon.getSalonId())
        );
    }
}
