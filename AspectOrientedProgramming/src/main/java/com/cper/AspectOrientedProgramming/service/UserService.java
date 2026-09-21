package com.cper.AspectOrientedProgramming.service;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String createUser() {
//        throw new RuntimeException("Custom Exception Occurred");
        System.out.println("User Created");
        return "User Created : Ayush Raj";
    }

    public String deleteUser() {
//        throw new RuntimeException("Custom Exception Occurred");
        System.out.println("User Deleted");
        return "User Deleted : Ayush Raj";
    }
}
