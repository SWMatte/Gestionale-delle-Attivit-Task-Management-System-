package com.taskManagmentSystem.controller;

import com.taskManagmentSystem.model.DTO.request.HistoryDTO;
import com.taskManagmentSystem.model.DTO.request.HistoryFilterDTO;
import com.taskManagmentSystem.model.DTO.request.UserDTO;
import com.taskManagmentSystem.model.DTO.response.HistoryResponseDTO;
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
            historyService.changeStatus(historyDTO.getIdActivity(), historyDTO.getStatusActivity());
            log.info("Finish - method: changeStatus");
            return ResponseEntity.ok("Status changed correctly");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body("Error with parameters");
        }
    }


    @PostMapping("/information")
    public ResponseEntity<?> findHistoryInformation(@RequestBody HistoryFilterDTO historyFilterDTO) {
        try {
            log.info("Enter into HistoryController - method: findHistoryInformation");
            List<HistoryResponseDTO> historyResponseDTO = historyService.findHistory(historyFilterDTO.getStatus(), historyFilterDTO.getIdUser());
            return ResponseEntity.ok(historyResponseDTO);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body("Error with parameters");
        }
    }

    @GetMapping("/informationUser")
    public ResponseEntity<?> findHistoryForUser(@RequestParam int idUser) {
        try {
            log.info("Enter into HistoryController - method: findHistoryForUser");
            List<HistoryResponseDTO> historyResponseDTO = historyService.findHistoryForUser(idUser);
            return ResponseEntity.ok(historyResponseDTO);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body("Error with parameters");
        }
    }

    @GetMapping("/dueDateForUser")
    public ResponseEntity<?> findActivityDueDate(@RequestParam int idUser) {
        try {
            log.info("Enter into HistoryController - method: findActivityDueDate");
            List<HistoryResponseDTO> historyResponseDTO = historyService.findActivityDueDate(idUser);
            return ResponseEntity.ok(historyResponseDTO);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body("Error with parameters");
        }
    }


    @PostMapping("/count")
    public ResponseEntity<?> countActivity(@RequestBody HistoryFilterDTO historyFilterDTO) {
        try {
            log.info("Enter into HistoryController - method: countActivity");
            int count = historyService.countActivity(historyFilterDTO.getStatus(), historyFilterDTO.getIdUser());
            return ResponseEntity.ok(count);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body("Error with parameters");
        }
    }


}
