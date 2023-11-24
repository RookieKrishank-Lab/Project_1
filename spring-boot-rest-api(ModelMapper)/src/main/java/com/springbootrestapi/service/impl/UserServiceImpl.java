package com.springbootrestapi.service.impl;

import com.springbootrestapi.dto.UserDTO;
import com.springbootrestapi.entity.User;
import com.springbootrestapi.mapper.UserMapper;
import com.springbootrestapi.repository.UserRepository;
import com.springbootrestapi.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    @Override
    public UserDTO createUser(UserDTO userDTO) {

        //Convert DTO to Entity
        //User user = UserMapper.getUser(userDTO);
        User user = modelMapper.map(userDTO, User.class);

        User savedUser = userRepository.save(user);

        //Convert Entity to DTO
        //UserDTO savedUserDTO = UserMapper.getUserDTO(savedUser);
        UserDTO savedUserDTO = modelMapper.map(savedUser, UserDTO.class);

        return savedUserDTO;

    }

    @Override
    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId).get();
        //return UserMapper.getUserDTO(user);
        return modelMapper.map(user, UserDTO.class);

    }

    @Override
    public List<UserDTO> getAllUser() {
        List<User> users = userRepository.findAll();
//        return user.stream().map(UserMapper::getUserDTO)
//                .collect(Collectors.toList());
        return users.stream().map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        //User user = UserMapper.getUser(userDTO);
        User user = modelMapper.map(userDTO,User.class);

        User searchedUser = userRepository.findById(user.getUserId()).get();
//       Optional<User> searchedUser = userRepository.findById(user.getUserId());           //If we use like this in return type we get an error as our method return type is not Optional
        searchedUser.setFirstName(user.getFirstName());
        searchedUser.setLastName(user.getLastName());
        searchedUser.setEmail(user.getEmail());
        User updatedUser =  userRepository.save(searchedUser);

        //return UserMapper.getUserDTO(updatedUser);
        return modelMapper.map(updatedUser,UserDTO.class);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
