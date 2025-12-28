package com.example.FitnessTracker.repositary;

import com.example.FitnessTracker.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepositary extends JpaRepository<Activity,String> {
}
