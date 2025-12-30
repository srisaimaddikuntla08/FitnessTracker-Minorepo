package com.example.FitnessTracker.controller;


import com.example.FitnessTracker.dto.RecommendationRequest;
import com.example.FitnessTracker.model.Recommendation;
import com.example.FitnessTracker.service.RecommendationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendation")
public class RecommendationController {

    private  final RecommendationService recommendationService;

    public RecommendationController(RecommendationService recommendationService){
        this.recommendationService = recommendationService;
    }


    @PostMapping("/generate")
    public ResponseEntity<Recommendation> generateRecommendations(@RequestBody RecommendationRequest request){

        Recommendation recommendation = recommendationService.generateRecommendation(request);
                return ResponseEntity.ok(recommendation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recommendation> getAllRecommendations( @PathVariable  String id){
        return ResponseEntity.ok(recommendationService.getAllRecommendations(id));
    }

    @GetMapping("/activity/{id}")
    public ResponseEntity<List<Recommendation>> getAllRecommendationsByActivityId(@PathVariable  String id){
        return ResponseEntity.ok(recommendationService.getAllRecommendationsByActivityId(id));
    }






}
