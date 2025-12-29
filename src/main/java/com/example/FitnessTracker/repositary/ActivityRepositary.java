package com.example.FitnessTracker.repositary;

import com.example.FitnessTracker.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepositary extends JpaRepository<Activity,String> {
    List<Activity> findByUserId(String userId);
}
