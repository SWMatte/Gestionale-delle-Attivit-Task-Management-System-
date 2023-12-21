package com.taskManagmentSystem.service;

import com.taskManagmentSystem.model.Activity;
import com.taskManagmentSystem.model.DTO.ActivityDTO;

import java.util.List;

public interface ActivityService {

    void addElement(ActivityDTO activityDTO);

    List<Activity> allActivity(String[] params);

    void modifyActivity(ActivityDTO updateActivityDTO);

    void deleteActivity(int id);

}
