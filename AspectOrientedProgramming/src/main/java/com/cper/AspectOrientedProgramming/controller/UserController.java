package com.cper.AspectOrientedProgramming.controller;

import com.cper.AspectOrientedProgramming.dto.UserDTO;
import com.cper.AspectOrientedProgramming.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        userService.createUser();
        return ResponseEntity.status(HttpStatus.CREATED).body(userDTO);
    }

    @DeleteMapping
    public ResponseEntity<UserDTO> deleteUser() {
        userService.deleteUser();
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
    }
}
