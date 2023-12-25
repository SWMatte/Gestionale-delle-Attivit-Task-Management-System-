package com.taskManagmentSystem.controller;

import com.taskManagmentSystem.model.Category;
import com.taskManagmentSystem.model.DTO.request.ActivityDTO;
import com.taskManagmentSystem.model.DTO.response.ActivityResponseDTO;
import com.taskManagmentSystem.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("activity")
@Slf4j
public class ActivityController {
    @Autowired
    private ActivityService activityService;

    @PostMapping()
    public ResponseEntity<?> addActivity(@RequestBody ActivityDTO activityDTO) {
        try {
            log.info("Enter into ActivityController - method: addActivity");
            activityService.addElement(activityDTO);
            log.info("Finish - method: addActivity and History");
            return ResponseEntity.ok("Activity and History added correctly");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body("Error with parameters");
        }
    }

    @PostMapping("/modify")
    public ResponseEntity<?> modifyActivity(@RequestBody ActivityDTO activityDTO) {
        try {
            log.info("Enter into ActivityController - method: modifyActivity");
            activityService.modifyActivity(activityDTO);
            return ResponseEntity.ok("Activity modified correctly");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body("Error with parameters");
        }
    }


    @GetMapping()
    public ResponseEntity<?> allActivity(@RequestParam String params) {
        try {
//            Category category = Category.valueOf(params.toUpperCase());
            log.info("Enter into ActivityController - method: allActivity");
            List<ActivityResponseDTO> response = activityService.allActivity(params);
            return ResponseEntity.ok().body(response);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body("Error with parameters");
        }
    }
}
