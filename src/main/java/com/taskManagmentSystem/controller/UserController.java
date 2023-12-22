package com.taskManagmentSystem.controller;

import com.taskManagmentSystem.model.DTO.UserDTO;
import com.taskManagmentSystem.model.User;
import com.taskManagmentSystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO) {
        try {
            log.info("Enter into UserController - method: updateUser");
            userService.updateUser(userDTO);
            log.info("Finish - method: addUser");
            return ResponseEntity.ok("User modified correctly");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body("Error with parameters");
        }
    }

    @GetMapping()
    public ResponseEntity<?> getUser(@RequestParam String search) {
        try {
            log.info("Enter into UserController - method: getUser");
            List<User> listUser = userService.filterSearch(search);
            if (listUser.size() == 0) {
                log.info("No User with these values");
                return ResponseEntity.badRequest().body("No user with these values");
            } else {
                log.info("Finish - method: getUser");
                return ResponseEntity.ok().body(listUser);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body("Error with parameters");
        }
    }

    @GetMapping("/specificage")
    public ResponseEntity<?> numberUnderSpecificAge(@RequestParam String search) {
        try {
            log.info("Enter into UserController - method: numberSpecificAge");
            int number = userService.underSpecificAge(search);
            log.info("Finish - method: numberSpecificAge");
            return ResponseEntity.ok().body(number);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body("Error with parameters");
        }
    }

    @GetMapping("/betweenage")
    public ResponseEntity<?> numberBetweenAge(@RequestParam String firstValue, @RequestParam String secondValue) {
        try {
            log.info("Enter into UserController - method: numberBetweenAge");
            int number = userService.betweenSpecificAge(firstValue,secondValue);
            log.info("Finish - method: numberBetweenAge");
            return ResponseEntity.ok().body(number);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body("Error with parameters");
        }
    }


    @PostMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestParam String name, @RequestParam String lastName) {
        try {
            log.info("Enter into UserController - method: deleteUser");
            userService.deleteUserLogic(name,lastName);
            log.info("Finish - method: deleteUser");
            return ResponseEntity.ok("User deleted correctly");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().body("Error with parameters");
        }
    }



}
