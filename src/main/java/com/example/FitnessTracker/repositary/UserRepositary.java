package com.example.FitnessTracker.repositary;

import com.example.FitnessTracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public  interface UserRepositary extends JpaRepository<User,String> {
}
