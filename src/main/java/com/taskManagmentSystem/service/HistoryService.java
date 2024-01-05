package com.taskManagmentSystem.service;

import com.taskManagmentSystem.model.StatusActivity;

public interface HistoryService {


    void changeStatus(int idActivity, StatusActivity statusActivity);

}
