package com.example.FitnessTracker.controller;


import com.example.FitnessTracker.dto.AuthRequest;
import com.example.FitnessTracker.dto.AuthResponse;
import com.example.FitnessTracker.dto.LoginRequest;
import com.example.FitnessTracker.dto.LoginResponse;
import com.example.FitnessTracker.model.User;
import com.example.FitnessTracker.repositary.UserRepositary;
import com.example.FitnessTracker.security.JwtUtils;
import com.example.FitnessTracker.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UseController {

    private final UserService userService;
    private final UserRepositary userRepositary;
    private final PasswordEncoder passwordEncoder;
    private  final JwtUtils jwtUtils;




    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody AuthRequest authRequest){
        return ResponseEntity.ok(userService.create(authRequest));

    }


    @GetMapping("/users")
    public ResponseEntity<List<AuthResponse>> getAllUsers(){

        return ResponseEntity.ok(userService.getALlUsers());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest){
        try{
        User user = userRepositary.findByEmail(loginRequest.getEmail());
        if (user == null) return ResponseEntity.status(401).build();

        if(!passwordEncoder.matches(loginRequest.getPassword(),user.getPassword())){
            return ResponseEntity.status(401).build();
        }
        log.info("USER ROLES: {}", user.getRole().name());

        String token = jwtUtils.generateToken(user.getId(),user.getRole().name());
        return  ResponseEntity.ok(new LoginResponse(token,userService.mapToResponse(user)));

        }catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(401).build();
        }
    }
}
