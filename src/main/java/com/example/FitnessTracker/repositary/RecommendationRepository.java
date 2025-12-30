package com.example.FitnessTracker.repositary;

import com.example.FitnessTracker.model.Activity;
import com.example.FitnessTracker.model.Recommendation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.ScopedValue;
import java.util.List;


public interface RecommendationRepository extends JpaRepository<Recommendation,String> {

    List<Recommendation> findByActivity(Activity activity);
}