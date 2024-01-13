package com.taskManagmentSystem.service;

import com.taskManagmentSystem.model.DTO.response.HistoryResponseDTO;
import com.taskManagmentSystem.model.StatusActivity;

import java.util.List;

public interface HistoryService {


    void changeStatus(int idActivity, StatusActivity statusActivity);

    List<HistoryResponseDTO> findHistory (StatusActivity[] status, int idUser);

    List<HistoryResponseDTO> findHistoryForUser ( int idUser);

    int countActivity(StatusActivity[] status, int idUser);


}
