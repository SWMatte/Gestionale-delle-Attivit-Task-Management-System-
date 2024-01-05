package com.taskManagmentSystem.controller;

import com.taskManagmentSystem.model.DTO.request.HistoryDTO;
import com.taskManagmentSystem.model.DTO.request.UserDTO;
import com.taskManagmentSystem.model.User;
import com.taskManagmentSystem.service.HistoryService;
import com.taskManagmentSystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("history")
@Slf4j
public class HistoryController {
    @Autowired
    private HistoryService historyService;


    @PostMapping()
    public ResponseEntity<?> changeStatus(@RequestBody HistoryDTO historyDTO) {
        try {
            log.info("Enter into HistoryController - method: changeStatus");
            historyService.changeStatus(historyDTO.getIdActivity(),historyDTO.getStatusActivity());
            log.info("Finish - method: changeStatus");
            return ResponseEntity.ok("Status changed correctly");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body("Error with parameters");
        }
    }




}
