package com.ecommerce.user.dto.request;

import jakarta.validation.constraints.NotBlank;

public class Login {
    @NotBlank(message = "EmailId Required ")
    private String email;
    @NotBlank(message = "Password Required ")
    private String password;

    public Login(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
