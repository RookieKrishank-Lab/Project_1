package com.springbootrestapi.mapper;

import com.springbootrestapi.dto.UserDTO;
import com.springbootrestapi.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AutoUserMapper {

    AutoUserMapper MAPPER = Mappers.getMapper(AutoUserMapper.class);
    UserDTO getUserDTO(User user);

    User getUser(UserDTO userDTO);

}
