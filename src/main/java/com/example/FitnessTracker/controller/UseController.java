package com.example.FitnessTracker.controller;


import com.example.FitnessTracker.dto.AuthRequest;
import com.example.FitnessTracker.dto.AuthResponse;
import com.example.FitnessTracker.model.User;
import com.example.FitnessTracker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UseController {

    private final UserService userService;
    public UseController(UserService userService){
        this.userService = userService;
    }


    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody AuthRequest authRequest){
        return ResponseEntity.ok(userService.create(authRequest));

    }


    @GetMapping("/users")
    public ResponseEntity<List<AuthResponse>> getAllUsers(){

        return ResponseEntity.ok(userService.getALlUsers());
    }
}
