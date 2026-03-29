package com.ecommerce.user.service;

import com.ecommerce.user.config.JwUtils;
import com.ecommerce.user.dto.request.Login;
import com.ecommerce.user.dto.request.Register;
import com.ecommerce.user.dto.response.AuthResponse;
import com.ecommerce.user.entity.User;
import com.ecommerce.user.enums.Role;
import com.ecommerce.user.enums.Status;
import com.ecommerce.user.exception.BadRequestException;
import com.ecommerce.user.repository.UserRepository;
import com.ecommerce.user.utils.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class Authservice {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwUtils jwUtils;

    @Autowired
    private EmailService emailService;

    public AuthResponse register(Register register){
       try{
           if(!userRepository.findByEmail(register.getEmail()).isEmpty()){
               throw new BadRequestException("Email Id Already Exist !");
           }

           User user  = new User();
           user.setPassword(passwordEncoder.encode(register.getPassword()));
           user.setRole(Role.USER);
           user.setEmail(register.getEmail());
           user.setName(register.getName());
           user.setStatus(Status.ACTIVE);
           user.setCreatedAt(LocalDateTime.now());
           userRepository.save(user);

           String token = jwUtils.generateToken(user.getEmail());

           AuthResponse response = new AuthResponse();
           response.setEmail(user.getEmail());
           response.setRole(user.getRole().name());
           response.setAccessToken(token);
           return response;
       }catch (DataAccessException ex){
           throw new RuntimeException("Data access Exception"+ex.getMessage());
       }
    }

    public AuthResponse login(Login login){
       try{
           User user = userRepository.findByEmail(login.getEmail()).orElseThrow(() -> new BadRequestException("User not found"));
           if(!passwordEncoder.matches(login.getPassword(), user.getPassword())){
               throw new BadRequestException("Invalid Password");
           }
           String token = jwUtils.generateToken(user.getEmail());
           String refreshToken = jwUtils.generateRefreshToken(user.getEmail());

           AuthResponse response = new AuthResponse();
           response.setAccessToken(token);
           response.setRefreshToken(refreshToken);
           response.setEmail(login.getEmail());
           response.setRole(user.getRole().name());
           return response;
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }
   public void forgotPassword(String email){
      try{
          User user = userRepository.findByEmail(email).orElseThrow(() -> new BadRequestException("User not found !"));
          String token = UUID.randomUUID().toString();
          user.setResetToken(token);
          user.setTokenExpiry(LocalDateTime.now().plusMinutes(15));

          userRepository.save(user);
          String resetLink = "http://localhost:8081/reset-password?token="+token;

          emailService.sendEmail(email,"Reset password","Click the link to reset your password: " + resetLink);

      } catch (Exception e) {
          throw new RuntimeException(e);
      }
   }

   public void resetPassword(String token){
       User user = userRepository.findByResetToken(token).orElseThrow(() -> new BadRequestException("Invalid token"));

       if(user.getTokenExpiry().isBefore(LocalDateTime.now())){
           throw new BadRequestException("Token expired !");
       }
       user.setPassword(passwordEncoder.encode(user.getPassword()));
       user.setResetToken(null);
       user.setTokenExpiry(null);
       userRepository.save(user);

   }

}
