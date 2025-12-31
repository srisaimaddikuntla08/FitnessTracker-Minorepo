package com.example.FitnessTracker.dto;


import com.example.FitnessTracker.model.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {

    String email;
    String password;
    String firstName;
    String lastName;
    UserRole  role;
}
