package com.taskManagmentSystem.controller;

import com.taskManagmentSystem.model.DTO.request.AuthenticationRequest;
import com.taskManagmentSystem.model.DTO.request.UserDTO;
import com.taskManagmentSystem.model.DTO.response.AuthenticationResponse;
import com.taskManagmentSystem.service.AuthenticationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@Slf4j
public class AuthenticationController {
    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDTO request) {
        log.info("Enter into AuthenticationController - method: register");

        try {
            log.info("User registered");
            return ResponseEntity.ok(authenticationService.register(request));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequest request) {
        log.info("Enter into AuthenticationController - method: authenticate");
        try {
            AuthenticationResponse authorized = authenticationService.authenticate(request);
            return ResponseEntity.ok(authorized);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


}
