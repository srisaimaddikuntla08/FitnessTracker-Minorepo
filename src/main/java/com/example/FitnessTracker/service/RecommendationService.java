package com.example.FitnessTracker.service;


import com.example.FitnessTracker.dto.RecommendationRequest;
import com.example.FitnessTracker.model.Activity;
import com.example.FitnessTracker.model.Recommendation;
import com.example.FitnessTracker.model.User;
import com.example.FitnessTracker.repositary.ActivityRepositary;
import com.example.FitnessTracker.repositary.RecommendationRepository;
import com.example.FitnessTracker.repositary.UserRepositary;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecommendationService {

    private final RecommendationRepository recommendationRepository;
    private  final UserRepositary userRepositary;
    private  final ActivityRepositary activityRepositary;



    public Recommendation generateRecommendation(RecommendationRequest request){
        User user = userRepositary.findById(request.getUserId()).orElseThrow(()->new RuntimeException("User not there" + request.getUserId()));
        Activity activity = activityRepositary.findById(request.getActivityId()).orElseThrow(()->new RuntimeException("Activity not there" + request.getActivityId()));

        Recommendation recommendation = new Recommendation();
        recommendation.setUser(user);
        recommendation.setActivity(activity);
        recommendation.setImprovements(request.getImprovements());
        recommendation.setSuggestions(request.getSuggestions());
        recommendation.setSafety(request.getSafety());

        return   recommendationRepository.save(recommendation);
    }

    public Recommendation getAllRecommendations(String id){
        return recommendationRepository.findById(id).orElseThrow(()->new RuntimeException("id not found" + id));
    }

    public List<Recommendation> getAllRecommendationsByActivityId(String id){
        Activity activity = activityRepositary.findById(id).orElseThrow(()->new RuntimeException("activity id not found" + id));
        return  recommendationRepository.findByActivity(activity);
    }


}
