package com.taskManagmentSystem.controller;

import com.taskManagmentSystem.aspect.Authorized;
import com.taskManagmentSystem.model.DTO.request.UserDTO;
import com.taskManagmentSystem.model.Role;
import com.taskManagmentSystem.model.User;
import com.taskManagmentSystem.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
@Tag(name = "UserController", description = "Controller per la gestione dell'user")
public class UserController {
    @Autowired
    private UserService userService;


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
    @Authorized(roles = Role.ADMIN)
    public ResponseEntity<?> getInformationsWithParams(@RequestParam String search) {
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
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = User.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
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
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = User.class), mediaType = "application/json") }),
     })
    public ResponseEntity<?> numberBetweenAge(
            @Parameter(description = "First value range age") @RequestParam(required = true) String firstValue,
            @Parameter(description = "Second value range age", required = true) @RequestParam(defaultValue = "50") String secondValue){

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
