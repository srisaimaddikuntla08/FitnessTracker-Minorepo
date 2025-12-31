package com.example.FitnessTracker.service;


import com.example.FitnessTracker.dto.AuthRequest;
import com.example.FitnessTracker.dto.AuthResponse;
import com.example.FitnessTracker.model.User;
import com.example.FitnessTracker.model.UserRole;
import com.example.FitnessTracker.repositary.UserRepositary;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepositary userRepositary;
    private  final PasswordEncoder passwordEncoder;

    public User create(AuthRequest authRequest) {

        UserRole role = authRequest.getRole() !=null ? authRequest.getRole() : UserRole.USER;

        User user = User.builder()
                .email(authRequest.getEmail())
                .role(role)
                .password(passwordEncoder.encode(authRequest.getPassword()))
                .lastName(authRequest.getLastName())
                .firstName(authRequest.getFirstName())
                .build();

        return  userRepositary.save(user);
    }

    public List<AuthResponse> getALlUsers(){
        return  userRepositary.findAll().stream().map((user) -> mapToResponse(user)).toList();
    }

    public AuthResponse mapToResponse(User saveResponse){
        AuthResponse response = new AuthResponse();

        response.setEmail(saveResponse.getEmail());
        response.setFirstName(saveResponse.getFirstName());
        response.setLastName(saveResponse.getLastName());
        return response;
    }
}
