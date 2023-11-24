package com.springbootrestapi.mapper;

import com.springbootrestapi.dto.UserDTO;
import com.springbootrestapi.entity.User;

public class UserMapper {

    // Convert user JPA into UserDTO
    public static UserDTO getUserDTO(User user){
        UserDTO userDTO = new UserDTO(
          user.getUserId(),
          user.getFirstName(),
          user.getLastName(),
          user.getEmail()
        );
        return userDTO;
    }

    // Convert UserDTO into user JPA
    public static User getUser(UserDTO userDTO){
        User user = new User(
                userDTO.getUserId(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getEmail()
        );
        return user;
    }
}
