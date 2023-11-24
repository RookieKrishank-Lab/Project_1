package com.springbootrestapi.controller;

import com.springbootrestapi.dto.UserDTO;
import com.springbootrestapi.entity.User;
import com.springbootrestapi.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor                                             //we are using constructor base DI and only one constructor will be there so no need of @Autowire
@RequestMapping("/api/v1/user")
public class UserController {

    private UserService userService;

    @PostMapping("/addUser")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO createUser(@RequestBody UserDTO user){
        UserDTO savedUser = userService.createUser(user);
//        String newResourceLocation = "http://localhost:8080/api/v1/user/addUser";
//        URI uri = URI.create(newResourceLocation);
        return savedUser;
//        return ResponseEntity; //created(uri).body(user)
    }

    @GetMapping("/getUserById/{userId}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long userId){
        UserDTO userDTO = userService.getUserById(userId);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/getAllUser")
    public ResponseEntity<List<UserDTO>> getAllUser(){
        List<UserDTO> allUser = userService.getAllUser();
        return ResponseEntity.ok(allUser);
    }

    @PutMapping("/updateUserById/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable long userId, @RequestBody UserDTO userDTO){
        userDTO.setUserId(userId);
        UserDTO updateUser = userService.updateUser(userDTO);
        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("/deleteUserById/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable long userId){
        userService.deleteUser(userId);
        return ResponseEntity.ok("User ID "+userId+" deleted successfully");
    }
}
