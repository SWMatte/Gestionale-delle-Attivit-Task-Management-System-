package com.taskManagmentSystem.model.DTO.response;

import com.taskManagmentSystem.model.Category;
import com.taskManagmentSystem.model.History;
import com.taskManagmentSystem.model.StatusActivity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class HistoryResponseDTO {

    private LocalDate creationHistory;

    private LocalDate presumeEndDateActivity;

    private LocalDate endDateActivity;

    private StatusActivity statusActivity;

    private String note;

    private String name;

    private String lastName;

    private String activityName;

    private String description;

    private Category activityCategory;
}
