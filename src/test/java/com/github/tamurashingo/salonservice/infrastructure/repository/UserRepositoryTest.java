package com.github.tamurashingo.salonservice.infrastructure.repository;

import com.github.tamurashingo.salonservice.domain.model.User;
import org.junit.Test;

import java.util.Calendar;

public class UserRepositoryTest {

    @Test
    public void create() {
        User user =  new User(1L, "test@email.com", "test name", "password", User.UserStatus.TEMPORARY, Calendar.getInstance().getTime(), Calendar.getInstance().getTime());
    }


}
