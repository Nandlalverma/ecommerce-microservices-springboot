package com.ecommerce.user.exception;

import org.springframework.boot.autoconfigure.graphql.GraphQlProperties;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ApiResponse<T> {

    private HttpStatus status;
    private Integer statusCode;
    private String message;
    private LocalDateTime localDateTime;
    private T data;
    public ApiResponse(){

    }
    public static <T> ApiResponse<T> success(HttpStatus status, String message, T data){
       ApiResponse response = new ApiResponse();
       response.setData(data);
       response.setMessage(message);
       response.setStatusCode(status.value());
       response.setLocalDateTime(LocalDateTime.now());
       response.setStatus(HttpStatus.OK);
       return response;

    }

    public ApiResponse(HttpStatus status, Integer statusCode, String message, LocalDateTime localDateTime, T data) {
        this.status = status;
        this.statusCode = statusCode;
        this.message = message;
        this.localDateTime = localDateTime;
        this.data = data;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
