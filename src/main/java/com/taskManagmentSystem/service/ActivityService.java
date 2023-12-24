package com.taskManagmentSystem.service;

import com.taskManagmentSystem.model.Activity;
import com.taskManagmentSystem.model.DTO.ActivityDTO;

import java.util.List;

public interface ActivityService {

    void addElement(ActivityDTO activityDTO) throws Exception;

    List<Activity> allActivity(String[] params) throws Exception;

    void modifyActivity(ActivityDTO updateActivityDTO) throws Exception;

    void deleteActivity(int id) throws Exception;

}
