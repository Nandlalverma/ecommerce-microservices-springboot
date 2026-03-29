package com.ecommerce.user.utils;

import com.ecommerce.user.dto.request.Register;
import com.ecommerce.user.dto.response.AuthResponse;
import com.ecommerce.user.entity.User;
import org.mapstruct.Mapper;
import org.springframework.web.bind.annotation.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(Register register);
    AuthResponse toResponse(User user);
}
