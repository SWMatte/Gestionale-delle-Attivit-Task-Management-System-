package com.taskManagmentSystem.model.DTO.request;

import com.taskManagmentSystem.model.Activity;

import com.taskManagmentSystem.model.History;
import com.taskManagmentSystem.model.StatusActivity;

import lombok.Data;

import java.time.LocalDate;


@Data
public class ActivityDTO extends Activity {

   private History history;
}
