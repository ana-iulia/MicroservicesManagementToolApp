package com.example.userservice.mapper;


import com.example.userservice.domain.LoginRequestDTO;
import com.example.userservice.domain.User;
import com.example.userservice.domain.UserDTO;
import com.example.userservice.domain.UserRegisterDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface UserMapper {

    UserRegisterDTO toUserRegisterDTO(User user);

    @Mapping(target = "password", source = "password")
    User toUserEntity(UserRegisterDTO userDTO);

    @Mapping(target = "password", source = "password")
    User loginToUser(LoginRequestDTO loginRequest);

    UserDTO toUserDTO(User user);

}
