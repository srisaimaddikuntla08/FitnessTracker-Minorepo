package com.example.FitnessTracker.dto;

import com.example.FitnessTracker.model.ActivityType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ActivityRequest {

    private String userId;
    private ActivityType type;
    private Map<String, Object> additionalMetrics;
    private  Integer duration;
    private  Integer caloriesBurned;
    private LocalDateTime startTime;
}
