package com.github.tamurashingo.salonservice.app.signup;


import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
public class SignUpForm implements java.io.Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 1, max = 128)
    private String userEmail;
    @NotNull
    @Size(min = 1, max = 128)
    private String userName;
    @NotNull
    @Size(min = 8, max = 128)
    private String password;

    public SignUpForm() {
    }
}
