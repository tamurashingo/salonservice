package com.github.tamurashingo.salonservice.domain.model.userregister;

import com.github.tamurashingo.salonservice.domain.shared.ValueObject;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@AllArgsConstructor
public class UserRegisterModel implements ValueObject<UserRegisterModel> {

    @Getter
    private final String userEmail;
    @Getter
    private final String userName;
    @Getter
    private final String password;

    @Override
    public boolean sameValueAs(UserRegisterModel other) {
        return Objects.equals(userEmail, other.userEmail);
    }
}
