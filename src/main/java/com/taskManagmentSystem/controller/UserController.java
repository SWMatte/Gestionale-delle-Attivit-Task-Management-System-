package com.taskManagmentSystem.controller;

import com.taskManagmentSystem.model.DTO.UserDTO;
import com.taskManagmentSystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping()
    public ResponseEntity<?> addUser(@RequestBody UserDTO userDTO) {
        try {
            log.info("Enter into UserController - method: addUser");
            userService.createUser(userDTO);
            log.info("Finish - method: addUser");
            return ResponseEntity.ok("User added correctly");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body("Error with parameters");
        }
    }
}
