package com.example.FitnessTracker.service;


import com.example.FitnessTracker.dto.AuthRequest;
import com.example.FitnessTracker.dto.AuthResponse;
import com.example.FitnessTracker.model.User;
import com.example.FitnessTracker.repositary.UserRepositary;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service
public class UserService {

    private final UserRepositary userRepositary;

    public UserService(UserRepositary userRepositary){
        this.userRepositary = userRepositary;
    }

    public User create(AuthRequest authRequest) {
//        User user = new User();
//        user.setEmail(authRequest.getEmail());
//        user.setPassword(authRequest.getPassword());
//        user.setLastName(authRequest.getLastName());
//        user.setFirstName(authRequest.getFirstName());
//
//        return userRepositary.save(user);

        User user = User.builder()
                .email(authRequest.getEmail())
                .password(authRequest.getPassword())
                .lastName(authRequest.getLastName())
                .firstName(authRequest.getFirstName())
                .build();

        return  userRepositary.save(user);
    }

    public List<AuthResponse> getALlUsers(){
        return  userRepositary.findAll().stream().map((user) -> mapToResponse(user)).toList();
    }

    private  AuthResponse mapToResponse(User saveResponse){
        AuthResponse response = new AuthResponse();

        response.setEmail(saveResponse.getEmail());
        response.setFirstName(saveResponse.getFirstName());
        response.setLastName(saveResponse.getLastName());
        return response;
    }
}
