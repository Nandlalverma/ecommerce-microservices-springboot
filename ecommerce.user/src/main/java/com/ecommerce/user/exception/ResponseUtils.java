package com.ecommerce.user.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ResponseUtils {

   public static <T> ApiResponse<T> error(HttpStatus status,int statusCode,String message){
       ApiResponse response = new ApiResponse();
       response.setStatus(status);
       response.setMessage(message);
       response.setStatusCode(statusCode);
       response.setLocalDateTime(LocalDateTime.now());
     return response;
   }

}
