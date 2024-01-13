package com.taskManagmentSystem.model.DTO.request;

import com.taskManagmentSystem.model.StatusActivity;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HistoryFilterDTO {

    StatusActivity[] status;
    int idUser;
}
