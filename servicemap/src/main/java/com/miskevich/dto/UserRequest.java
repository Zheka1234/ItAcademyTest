package com.miskevich.dto;

import com.miskevich.entity.SystemRoles;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class UserRequest {

    @Size(max = 40)
    @Pattern(regexp = "^[a-zA-Z]*$")
    private String surname;

    @Size(max = 20)
    @Pattern(regexp = "^[a-zA-Z]*$")
    private String firstname;

    @Size(max = 40)
    @Pattern(regexp = "^[a-zA-Z]*$")
    private String lastname;

    @Size(max = 50)
    @Email
    private String email;

    @NotNull
    private SystemRoles role;
}
