package com.taskManagmentSystem.controller;

import com.taskManagmentSystem.model.DTO.ActivityDTO;
import com.taskManagmentSystem.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
