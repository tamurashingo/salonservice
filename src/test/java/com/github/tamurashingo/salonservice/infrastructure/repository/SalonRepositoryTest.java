package com.github.tamurashingo.salonservice.infrastructure.repository;

import com.github.tamurashingo.salonservice.domain.repository.SalonRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SalonRepositoryTest {

    @Autowired
    private SalonRepository salonRepository;

    @Test
    @Transactional
    public void test() {

    }


}
