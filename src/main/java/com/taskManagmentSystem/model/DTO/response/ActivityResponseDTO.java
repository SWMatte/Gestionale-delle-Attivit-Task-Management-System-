package com.taskManagmentSystem.model.DTO.response;

import com.taskManagmentSystem.model.Category;
import com.taskManagmentSystem.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ActivityResponseDTO {


    private String activityName;

    private String description;

    private LocalDate creationDate;

    private Category activityCategory;

    private String name;

    private String lastName;




}
