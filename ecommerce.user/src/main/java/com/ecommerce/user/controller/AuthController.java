package com.ecommerce.user.controller;

import com.ecommerce.user.config.JwUtils;
import com.ecommerce.user.dto.request.Login;
import com.ecommerce.user.dto.request.Register;
import com.ecommerce.user.dto.response.AuthResponse;
import com.ecommerce.user.exception.ApiResponse;
import com.ecommerce.user.service.Authservice;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private Authservice service;

    @Autowired
    private JwUtils jwUtils;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@Valid @RequestBody Register request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(HttpStatus.CREATED,"User Register successfully !",service.register(request)));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody Login request) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(HttpStatus.OK,"User Login Successfully !",service.login(request)));
    }


    @PostMapping("/refresh")
    public ResponseEntity<AuthResponse> refreshToken(@Valid @RequestBody String refreshToken){
        String email = jwUtils.extractEmail(refreshToken);
        String newToken = jwUtils.generateToken(refreshToken);
        AuthResponse response = new AuthResponse();
        response.setRefreshToken(refreshToken);
        response.setAccessToken(newToken);
        response.setEmail(email);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/forgot/{email}")
    public ResponseEntity<ApiResponse<String>> forgetPassword(@PathVariable String email){
        service.forgotPassword(email);
        return ResponseEntity.status(HttpStatus.OK)
                .body(ApiResponse.success(HttpStatus.OK,"Successfully password reset 1","Reset "));
    }
   //reset?token=&newPassword=
    @GetMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam  String token){
        service.resetPassword(token);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully Password reset !");
    }

}
