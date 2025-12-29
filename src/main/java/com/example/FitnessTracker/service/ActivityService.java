package com.example.FitnessTracker.service;


import com.example.FitnessTracker.dto.ActivityRequest;
import com.example.FitnessTracker.dto.ActivityResponse;
import com.example.FitnessTracker.model.Activity;
import com.example.FitnessTracker.model.User;
import com.example.FitnessTracker.repositary.ActivityRepositary;
import com.example.FitnessTracker.repositary.UserRepositary;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private  final ActivityRepositary activityRepositary;
    private  final UserRepositary userRepositary;





    public ActivityResponse trackActivity(ActivityRequest activityRequest){

        User user = userRepositary.findById(activityRequest.getUserId()).orElseThrow(() ->new RuntimeException("Invalid user :" + activityRequest.getUserId()));

        Activity activity = new Activity();
        activity.setType(activityRequest.getType());
        activity.setUser(user);
        activity.setDuration(activityRequest.getDuration());
        activity.setStartTime(activityRequest.getStartTime());
        activity.setAdditionalMetrics(activityRequest.getAdditionalMetrics());
        activity.setCaloriesBurned(activityRequest.getCaloriesBurned());

       Activity savedActivity =  activityRepositary.save(activity);
       return mapToResponse(savedActivity);
    }

    private ActivityResponse mapToResponse(Activity activity){
            ActivityResponse response = new ActivityResponse();
            response.setId(activity.getId());
            response.setUserId(activity.getUser().getId());
            response.setType(activity.getType());
            response.setDuration(activity.getDuration());
            response.setCaloriesBurned(activity.getCaloriesBurned());
            response.setStartTime(activity.getStartTime());
            response.setAdditionalMetrics(activity.getAdditionalMetrics());
            response.setUpdatedAt(activity.getUpdatedAt());
            response.setCreatedAt(activity.getCreatedAt());
            return  response;
    }

    public List<ActivityResponse> getUserActivities(String userId){
            List<Activity> activityList = activityRepositary.findByUserId(userId);
            return  activityList.stream().map(this::mapToResponse).collect(Collectors.toList());

    }



}
