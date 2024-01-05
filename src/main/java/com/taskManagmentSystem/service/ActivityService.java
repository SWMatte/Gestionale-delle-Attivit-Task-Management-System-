package com.taskManagmentSystem.service;

import com.taskManagmentSystem.model.Activity;
import com.taskManagmentSystem.model.Category;
import com.taskManagmentSystem.model.DTO.request.ActivityDTO;
import com.taskManagmentSystem.model.DTO.response.ActivityResponseDTO;

import java.util.List;

public interface ActivityService {

    void addElement(ActivityDTO activityDTO) throws Exception;

    List<ActivityResponseDTO> allActivity(String params,int idUser) throws Exception;

    void modifyActivity(ActivityDTO updateActivityDTO) throws Exception;

    void deleteActivity(int id) throws Exception;

}
