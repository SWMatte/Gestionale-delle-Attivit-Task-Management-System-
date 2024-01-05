package com.taskManagmentSystem.model.DTO.request;

import com.taskManagmentSystem.model.StatusActivity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HistoryDTO {

    private int idActivity;
    private StatusActivity statusActivity;
}
