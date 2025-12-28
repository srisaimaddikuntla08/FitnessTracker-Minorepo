package com.example.FitnessTracker.controller;


import com.example.FitnessTracker.dto.ActivityRequest;
import com.example.FitnessTracker.dto.ActivityResponse;
import com.example.FitnessTracker.service.ActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

private final ActivityService activityService;

public ActivityController(ActivityService activityService){
    this.activityService = activityService;
}
    @PostMapping
    public ResponseEntity<ActivityResponse> trackerActivity(@RequestBody ActivityRequest activityRequest){
        return ResponseEntity.ok(activityService.trackActivity(activityRequest));
    }
//
//    @GetMapping
//    public ResponseEntity<<List<ActivityResponse>> trackerActivity(){
//        return " ";
//    }




}
