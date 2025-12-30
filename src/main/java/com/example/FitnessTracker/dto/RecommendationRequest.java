package com.example.FitnessTracker.dto;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.awt.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationRequest {
    private String userId;
    private String activityId;

    private java.util.List<String> improvements;

    private java.util.List<String> suggestions;

    private List<String> safety;

}
