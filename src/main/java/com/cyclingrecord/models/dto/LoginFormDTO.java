package com.cyclingrecord.models.dto;

import com.sun.istack.NotNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class LoginFormDTO {

    @NotNull
    @NotBlank
    @Size(min = 3, max = 20, message = "Username must be between 3-20 characters")
    private String username;

    @NotNull
    @NotBlank
    @Size(min = 5, max = 20, message= "Password must be between 5-20 characters")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
